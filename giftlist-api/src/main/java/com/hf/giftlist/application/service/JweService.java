package com.hf.giftlist.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hf.giftlist.application.exception.InvalidSessionException;
import com.hf.giftlist.domain.model.login.Session;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import static com.nimbusds.jose.EncryptionMethod.A128CBC_HS256;
import static com.nimbusds.jose.JWEAlgorithm.DIR;

@Service
public class JweService {

    private final String keyaes;
    private final String keyhmac;
    private final int sessionExp;

    public JweService(@Value("${app.session.keyaes128base64}") final String keyaes,
                      @Value("${app.session.keyhmacsha256base64}") final String keyhmac,
                      @Value("${app.session.exp.seconds}") final int sessionExp) {
        this.keyaes = keyaes;
        this.keyhmac = keyhmac;
        this.sessionExp = sessionExp;
    }

    public String createSession(final Session session) {
        try {
            final String payload = new ObjectMapper().writeValueAsString(session);

            final JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                    .subject(payload)
                    .expirationTime(Date.from(Instant.now().plus(Duration.ofSeconds(sessionExp))))
                    .build();

            final JWSObject jws = this.createJws(jwtClaims);
            final JWEObject jwe = this.createJwe(jws);

            return jwe.serialize();
        } catch (final Exception ex) {
            throw new InvalidSessionException(ex);
        }
    }

    public Session decryptSession(final String encryptedJwe) {
        try {
            final String token = encryptedJwe.replace("Bearer ", "");
            final JWEObject jwe = this.decryptJwe(token);
            final SignedJWT jws = this.decodeJws(jwe);
            final JWTClaimsSet claims = jws.getJWTClaimsSet();
            if (claims.getExpirationTime().before(Date.from(Instant.now()))) {
                throw new InvalidSessionException("Session expired.");
            }
            return new ObjectMapper().readValue(claims.getSubject(), Session.class);
        } catch (final Exception ex) {
            throw new InvalidSessionException(ex);
        }
    }

    private JWSObject createJws(final JWTClaimsSet claims) throws Exception {
        final JWSSigner signer = new MACSigner(getSignKey());
        final JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).build();
        final Payload payload = new Payload(claims.toJSONObject());
        final JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(signer);
        return jwsObject;
    }

    private JWEObject createJwe(final JWSObject jws) throws Exception {
        final JWEEncrypter encrypter = new DirectEncrypter(getCryptoKey());
        final JWEHeader header = new JWEHeader.Builder(DIR, A128CBC_HS256).contentType("jwt").build();
        final Payload payload = new Payload(jws.serialize());
        JWEObject jweObject = new JWEObject(header, payload);
        jweObject.encrypt(encrypter);
        return jweObject;
    }

    private JWEObject decryptJwe(final String encryptedJwe) throws Exception {
        final JWEDecrypter decrypter = new DirectDecrypter(getCryptoKey());
        final JWEObject jweObject = JWEObject.parse(encryptedJwe);
        jweObject.decrypt(decrypter);
        return jweObject;
    }

    private SignedJWT decodeJws(JWEObject jwe) throws Exception {
        final JWSVerifier signatureVerifier = new MACVerifier(getSignKey());
        final String payload = jwe.getPayload().toString();
        final SignedJWT signedJWT = SignedJWT.parse(payload);
        if (!signedJWT.verify(signatureVerifier)) {
            throw new InvalidSessionException("Invalid session signature.");
        }
        return signedJWT;
    }

    private SecretKey getSignKey() {
        return getSecretKey(keyhmac, "HmacSHA256");
    }

    private SecretKey getCryptoKey() {
        return getSecretKey(keyaes, "AES");
    }

    private SecretKey getSecretKey(String keyAes128Base64, String AES) {
        final byte[] decodedAesKey = Base64.getDecoder().decode(keyAes128Base64);
        return new SecretKeySpec(decodedAesKey, 0, decodedAesKey.length, AES);
    }
}
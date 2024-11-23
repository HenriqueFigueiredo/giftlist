package com.hf.giftlist.domain.model.login;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String userId;
    private String userEmail;
    private String otpCode;
    private String name;
    private String lastname;
    private boolean newUser;
    private boolean authenticated;
}

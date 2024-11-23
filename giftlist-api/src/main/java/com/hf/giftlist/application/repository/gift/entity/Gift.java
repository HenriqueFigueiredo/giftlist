package com.hf.giftlist.application.repository.gift.entity;

import com.hf.giftlist.application.repository.giftlist.entity.GiftList;
import com.hf.giftlist.application.repository.login.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "gift")
public class Gift {

    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private final String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private GiftList giftList;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "url", length = 255)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = true)
    private User guest;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private final LocalDateTime creationDate;

    public Gift() {
        this.id = UUID.randomUUID().toString();
        this.creationDate = LocalDateTime.now();
    }

    public Gift(final GiftList giftList, final String name, final String url) {
        this();
        this.giftList = giftList;
        this.name = name;
        this.url = url;
    }

    public Gift(final GiftList giftList, final User guest, final String name, final String url) {
        this(giftList, name, url);
        this.guest = guest;
    }
}
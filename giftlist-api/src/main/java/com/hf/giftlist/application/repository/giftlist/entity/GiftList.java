package com.hf.giftlist.application.repository.giftlist.entity;


import com.hf.giftlist.application.repository.gift.entity.Gift;
import com.hf.giftlist.application.repository.login.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "gift_list")
public class GiftList {

    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private final String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(mappedBy = "giftList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Gift> gifts;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private final LocalDateTime creationDate;

    public GiftList() {
        this.id = UUID.randomUUID().toString();
        this.creationDate = LocalDateTime.now();
    }

    public GiftList(final User user, final String name, final String description) {
        this();
        this.user = user;
        this.name = name;
        this.description = description;
    }
}

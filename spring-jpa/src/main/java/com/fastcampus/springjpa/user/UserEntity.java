package com.fastcampus.springjpa.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;

    @OneToOne(fetch = FetchType.LAZY)
    private Subscription subscription;

}

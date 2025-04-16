package com.fastcampus.springjpa.order;

import com.fastcampus.springjpa.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class OrderEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(nullable = false)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

}

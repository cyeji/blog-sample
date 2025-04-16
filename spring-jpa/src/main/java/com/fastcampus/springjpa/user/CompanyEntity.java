package com.fastcampus.springjpa.user;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class CompanyEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private Long id;

}

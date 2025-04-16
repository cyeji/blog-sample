package com.fastcampus.springjpa.product;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ProductEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private Long id;

    
}

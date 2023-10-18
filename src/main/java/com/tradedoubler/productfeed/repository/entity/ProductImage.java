package com.tradedoubler.productfeed.repository.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private int height;
    private int width;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    @JsonBackReference
    private Product product;
}

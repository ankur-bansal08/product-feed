package com.tradedoubler.productfeed.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    @JsonBackReference
    private Product product;

    private String name;
    private String tdCategoryId; // This should be unique identifier for the category
    private String tdCategoryName;
}


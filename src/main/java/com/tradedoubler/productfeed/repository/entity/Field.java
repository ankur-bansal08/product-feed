package com.tradedoubler.productfeed.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "field")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    @JsonBackReference
    private Product product;

    private String name;
    private String value;
}


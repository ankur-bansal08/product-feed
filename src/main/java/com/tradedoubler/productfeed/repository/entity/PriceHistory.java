package com.tradedoubler.productfeed.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "price_history")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id",nullable = false)
    @JsonBackReference
    private Offer offer;

    private String currency;
    private Long date;
    private String dateFormat;
    private BigDecimal price;
}


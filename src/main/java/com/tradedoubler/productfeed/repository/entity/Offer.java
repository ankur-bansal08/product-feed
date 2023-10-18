package com.tradedoubler.productfeed.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "offer")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String offerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    @JsonBackReference
    private Product product;

    private String sourceProductId;
    private Long modifiedDate;
    private String dateFormat;
    private String feedId;
    private String productUrl;
    private String programName;
    private String programLogo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "offer")
    @JsonIgnore
    @JsonManagedReference
    private PriceHistory priceHistory;

    private String warranty;
    private Integer inStock;
    private String availability;
    private String deliveryTime;
    @Column(name = "offer_condition")
    private String condition;
    private String shippingCost;
}


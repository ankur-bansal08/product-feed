package com.tradedoubler.productfeed.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "grouping_id")
    private String groupingId;
    private String language;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonManagedReference
    private List<Category> categories;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonManagedReference
    private List<Field> fields;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonManagedReference
    private List<Offer> offers;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonManagedReference
    private ProductImage productImage;
    private String weight;
    private String size;
    private String model;
    private String brand;
    private String manufacturer;
    private String techSpecs;
    private String shortDescription;
    private String promoText;
    private String ean;
    private String upc;
    private String isbn;
    private String mpn;
    private String sku;
}


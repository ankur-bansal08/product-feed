package com.tradedoubler.productfeed.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO implements Serializable {
    private String groupingId;
    private String language;
    private String name;
    private String description;
    private List<CategoryDTO> categories;
    private List<FieldDTO> fields;
    private List<OfferDTO> offers;
    private ProductImageDTO productImage;
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


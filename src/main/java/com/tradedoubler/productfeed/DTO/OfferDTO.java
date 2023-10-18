package com.tradedoubler.productfeed.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tradedoubler.productfeed.repository.entity.PriceHistory;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDTO implements Serializable {

    private String offerId;

    private String sourceProductId;
    private Long modifiedDate;
    private String dateFormat;
    private String feedId;
    private String productUrl;
    private String programName;
    private String programLogo;
    private PriceHistory priceHistory;

    private String warranty;
    private Integer inStock;
    private String availability;
    private String deliveryTime;
    private String condition;
    private String shippingCost;
}

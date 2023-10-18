package com.tradedoubler.productfeed.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceHistoryDTO implements Serializable {

    private String currency;
    private Long date;
    private String dateFormat;
    private BigDecimal price;
}

package com.tradedoubler.productfeed.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductImageDTO implements Serializable {

    private String value;
    private int height;
    private int width;
}

package com.tradedoubler.productfeed.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO implements Serializable {

    private String name;
    private String tdCategoryId; // This should be unique identifier for the category
    private String tdCategoryName;
}

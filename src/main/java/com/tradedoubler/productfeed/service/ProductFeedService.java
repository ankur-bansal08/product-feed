package com.tradedoubler.productfeed.service;

import com.tradedoubler.productfeed.DTO.ProductDTO;
import com.tradedoubler.productfeed.Exception.ProductNotFoundException;

public interface ProductFeedService {
    ProductDTO getProductDetailsByFeedId(String feedId) throws ProductNotFoundException;
}

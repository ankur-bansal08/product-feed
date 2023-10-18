package com.tradedoubler.productfeed.service;

import com.tradedoubler.productfeed.DTO.ProductDTO;
import com.tradedoubler.productfeed.Exception.ProductNotFoundException;
import com.tradedoubler.productfeed.mapper.CustomObjectMapper;
import com.tradedoubler.productfeed.repository.ProductRepository;
import com.tradedoubler.productfeed.repository.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductFeedServiceImpl implements ProductFeedService {

    private final ProductRepository productRepository;
    private final CustomObjectMapper customObjectMapper;

    /**
     *
     * @param feedId
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public ProductDTO getProductDetailsByFeedId(String feedId) throws ProductNotFoundException {
        Product product = getProductDetails(feedId);
        return customObjectMapper.objectMapper().convertValue(product, ProductDTO.class);
    }

    private Product getProductDetails(String feedId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(Long.valueOf(feedId));
        return productOptional.orElseThrow(() -> new ProductNotFoundException("No product Found"));
    }
}

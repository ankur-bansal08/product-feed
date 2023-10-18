package com.tradedoubler.productfeed.controller;


import com.tradedoubler.productfeed.DTO.ProductDTO;
import com.tradedoubler.productfeed.Exception.ProductNotFoundException;
import com.tradedoubler.productfeed.service.ProductFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class ProductFeedController {

    private final ProductFeedService productFeedService;

    @GetMapping("/{feedId}")
    public ResponseEntity<Object> getProductsByFeedId(@PathVariable String feedId) throws ProductNotFoundException {
        ProductDTO product = productFeedService.getProductDetailsByFeedId(feedId);
        return ResponseEntity.ok(product);
    }
}

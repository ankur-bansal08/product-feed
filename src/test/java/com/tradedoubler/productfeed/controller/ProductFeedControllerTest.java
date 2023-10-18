package com.tradedoubler.productfeed.controller;

import com.tradedoubler.productfeed.DTO.ProductDTO;
import com.tradedoubler.productfeed.Exception.ProductNotFoundException;
import com.tradedoubler.productfeed.service.ProductFeedService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductFeedControllerTest {

    @Mock
    private ProductFeedService productFeedService;

    @InjectMocks
    private ProductFeedController productFeedController;

    @Test
    public void testGetProductsByFeedId_ValidFeedId() throws ProductNotFoundException {
        String feedId = "123";
        ProductDTO productDTO = new ProductDTO();
        when(productFeedService.getProductDetailsByFeedId(feedId)).thenReturn(productDTO);

        ResponseEntity<Object> response = productFeedController.getProductsByFeedId(feedId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void testGetProductsByFeedId_ProductNotFoundException() throws ProductNotFoundException {
        String feedId = "123";
        when(productFeedService.getProductDetailsByFeedId(feedId)).thenThrow(new ProductNotFoundException("Product not found."));

        ProductNotFoundException thrown = assertThrows(ProductNotFoundException.class, ()-> productFeedController.getProductsByFeedId(feedId));
        assertEquals("Product not found.", thrown.getMessage());
    }
}


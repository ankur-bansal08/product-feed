package com.tradedoubler.productfeed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradedoubler.productfeed.DTO.ProductDTO;
import com.tradedoubler.productfeed.Exception.ProductNotFoundException;
import com.tradedoubler.productfeed.mapper.CustomObjectMapper;
import com.tradedoubler.productfeed.repository.ProductRepository;
import com.tradedoubler.productfeed.repository.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class ProductFeedServiceImplTest {

    @InjectMocks
    private ProductFeedServiceImpl productFeedService;

    @Mock
    private ProductRepository productRepository;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private CustomObjectMapper customObjectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductDetailsByFeedId() throws ProductNotFoundException {
        String feedId = "1";
        Product product = Product.builder()
                .description("Test").build();
        ProductDTO productDTO = ProductDTO.builder()
                .description("Test").build();
        ObjectMapper objectMapper = new ObjectMapper();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doReturn(objectMapper).when(customObjectMapper).objectMapper();

        ProductDTO result = productFeedService.getProductDetailsByFeedId(feedId);

        // Assert
        assertNotNull(result);
        assertEquals(productDTO.getDescription(), result.getDescription());
    }

    @Test
    public void testGetProductDetailsByFeedId_ProductNotFoundException() {
        // Arrange
        String feedId = "999"; // Assuming this ID won't exist in the repository
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productFeedService.getProductDetailsByFeedId(feedId));
    }
}


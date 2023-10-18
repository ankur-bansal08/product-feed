package com.tradedoubler.productfeed.repository;

import com.tradedoubler.productfeed.repository.ProductRepository;
import com.tradedoubler.productfeed.repository.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Test
    public void testRepositorySaveAndFind(){
        String brand = "test_brand";
        Product savedProduct = productRepository.save(Product.builder().brand(brand).build());

        assertNotNull(savedProduct);
        assertEquals(savedProduct.getBrand(), brand);
        Optional<Product> optionalProduct = productRepository.findById(savedProduct.getId());
        assertTrue(optionalProduct.isPresent());
        Product result = optionalProduct.get();
        assertEquals(result.getBrand(), savedProduct.getBrand());
    }
}

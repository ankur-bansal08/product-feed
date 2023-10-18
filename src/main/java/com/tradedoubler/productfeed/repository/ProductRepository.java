package com.tradedoubler.productfeed.repository;

import com.tradedoubler.productfeed.repository.entity.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {

    List<Product> findAllByIdIn(List<Long> idList);
}

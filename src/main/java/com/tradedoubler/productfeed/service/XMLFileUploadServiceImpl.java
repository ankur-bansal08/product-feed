package com.tradedoubler.productfeed.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tradedoubler.pf.model.xml.common.Price;
import com.tradedoubler.pf.model.xml.output.Result;
import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import com.tradedoubler.productfeed.mapper.CustomObjectMapper;
import com.tradedoubler.productfeed.repository.ProductRepository;
import com.tradedoubler.productfeed.repository.entity.*;
import com.tradedoubler.productfeed.validator.FileUploadValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class XMLFileUploadServiceImpl implements FileUploadService {

    private final ProductRepository productRepository;
    private final FileUploadValidator fileUploadValidator;
    private final CustomObjectMapper customObjectMapper;


    @Override
    @Transactional
    public String processAndSaveData(MultipartFile file) throws ValidationException, EmptyFileException {
        Result xmlData = fileUploadValidator.validateXml(file);
        List<Product> productEntityList = convert(xmlData);
        productRepository.saveAll(productEntityList);
        return "File uploaded and validated successfully.";
    }

    private List<Product> convert(Result xmlData) {
        return xmlData.getProducts().getProducts().stream()
                .map(product -> {
                    Product p = customObjectMapper.objectMapper().convertValue(product, Product.class);
                    if (Objects.nonNull(p.getProductImage())) {
                        p.getProductImage().setProduct(p);
                    }
                    updateCategories(product, p);
                    updateFields(product, p);
                    updateOffers(product, p);
                    return p;
                })
                .collect(Collectors.toList());
    }

    private void updateCategories(Result.Products.Product product, Product p) {
        if (Objects.nonNull(product.getCategories())) {
            List<Category> categoryList = customObjectMapper.objectMapper()
                    .convertValue(product.getCategories().getCategories(), new TypeReference<List<Category>>() {
                    });
            categoryList.forEach(category -> category.setProduct(p));
            p.setCategories(categoryList);
        }
    }

    private void updateFields(Result.Products.Product product, Product p) {
        if (Objects.nonNull(product.getFields())) {
            List<Field> fieldList = customObjectMapper.objectMapper()
                    .convertValue(product.getFields().getFields(), new TypeReference<List<Field>>() {
                    });
            fieldList.forEach(field -> field.setProduct(p));
            p.setFields(fieldList);
        }
    }

    private void updateOffers(Result.Products.Product product, Product p) {
        if (Objects.nonNull(product.getOffers())) {
            List<Offer> offerList = product.getOffers().getOffers().stream()
                    .filter(offer -> Objects.nonNull(offer.getPriceHistory()))
                    .map(offer -> {
                        Offer offerTemp = customObjectMapper.objectMapper().convertValue(offer, Offer.class);
                        Price price = offer.getPriceHistory().getPrice();
                        if (Objects.nonNull(price)) {
                            PriceHistory priceHistory = customObjectMapper.objectMapper()
                                    .convertValue(price, PriceHistory.class);
                            priceHistory.setOffer(offerTemp);
                            offerTemp.setPriceHistory(priceHistory);
                        }
                        offerTemp.setProduct(p);
                        return offerTemp;
                    })
                    .collect(Collectors.toList());
            p.setOffers(offerList);
        }

    }
}

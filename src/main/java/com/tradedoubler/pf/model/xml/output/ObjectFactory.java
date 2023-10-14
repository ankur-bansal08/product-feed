//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.10.14 at 05:16:47 PM CEST 
//


package com.tradedoubler.pf.model.xml.output;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tradedoubler.pf.model.xml.output package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tradedoubler.pf.model.xml.output
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link Result.Products }
     * 
     */
    public Result.Products createResultProducts() {
        return new Result.Products();
    }

    /**
     * Create an instance of {@link Result.Products.Product }
     * 
     */
    public Result.Products.Product createResultProductsProduct() {
        return new Result.Products.Product();
    }

    /**
     * Create an instance of {@link Result.Products.Product.Offers }
     * 
     */
    public Result.Products.Product.Offers createResultProductsProductOffers() {
        return new Result.Products.Product.Offers();
    }

    /**
     * Create an instance of {@link Result.Products.Product.Offers.Offer }
     * 
     */
    public Result.Products.Product.Offers.Offer createResultProductsProductOffersOffer() {
        return new Result.Products.Product.Offers.Offer();
    }

    /**
     * Create an instance of {@link Result.Products.Product.Offers.Offer.PriceHistory }
     * 
     */
    public Result.Products.Product.Offers.Offer.PriceHistory createResultProductsProductOffersOfferPriceHistory() {
        return new Result.Products.Product.Offers.Offer.PriceHistory();
    }

}
package com.maha.catalogue.service.impl;


import com.maha.catalogue.dao.CatalogueRepository;
import com.maha.catalogue.entity.WatchCatalogue;
import com.maha.catalogue.service.CatalogueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service class that is managed by Spring, contains the business logic
 * Created by durga on 8/18/2020.
 */
@Service
public class CatalogueServiceImpl implements CatalogueService {
    Logger logger = LoggerFactory.getLogger(CatalogueServiceImpl.class);

    /**
     * Refers to the catalogue repository
     */
    private CatalogueRepository repository;

    /**
     * Constructor class the allows repository to be injected
     * @param repository
     */
    public CatalogueServiceImpl(@Autowired CatalogueRepository repository){
        this.repository = repository;
    }

    /**
     * Returns all the Catalogues
     * */
    @Override
    public List<WatchCatalogue> getAll() {
        return repository.findAll();
    }

    /***
     * Methods returns the specific catalogue
     * @param key
     * @return WatchCatalogue
     */
    @Override
    public WatchCatalogue findByBusinessKey(String key) {
        return repository.findByBusinessKey(key);
    }

    /**
     * Takes all the products chosen by the customer.
     * The method groups the catalogues to give count for each catalogue. For each catalogue
     * Checks if discount quantity is greater than zero. If greater than zero
     * get the quotient and reminder by dividing count of catalogue chosen by the customer with discount quantity
     * multiply the quotient with discount value and multiply the reminder with unit price
     * add both the value to get the price.
     * Repeat for all the catalogue and add all the price
     * @param businessKeys
     * @return double . The price after calculating the full discount
     */
    @Override
    public double getPriceForSelectedItems(List<String> businessKeys) {
        Map<String, Long> catReqCounts = businessKeys.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        catReqCounts.forEach( (K,V) -> logger.debug("key ="+K+" value = "+V));

        List<WatchCatalogue> catalogues = repository.findAll();

        logger.debug(" Total Catalogues  = " + catalogues.size());
        return  catalogues.stream().mapToDouble( P -> {
            Long countFound =  catReqCounts.getOrDefault(P.getBusinessKey(), 0L);
             logger.debug(" businessKey ="+P.getBusinessKey()+" count Found = "+countFound +
            " discount number = "+P.getMinDiscount() +" discount value ="+P.getDiscountValue());
            return  P.getMinDiscount() > 0 ? ( ( (countFound / P.getMinDiscount())* P.getDiscountValue()) +
                    (( countFound % P.getMinDiscount()) * P.getUnitPrice()) ) : countFound * P.getUnitPrice();

        }).sum();
    }

}

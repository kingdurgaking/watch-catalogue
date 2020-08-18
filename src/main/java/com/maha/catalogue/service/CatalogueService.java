package com.maha.catalogue.service;


import com.maha.catalogue.entity.WatchCatalogue;

import java.util.List;

/**
 * Interface for Catalogue service
 * Created by durga on 8/18/2020.
 */
public interface CatalogueService {

    /**
     * @return List of {@link WatchCatalogue}
     */
    public List<WatchCatalogue> getAll();

    /**
     * Returns the catalogue for the particular business key
     * @param key
     * @return WatchCatalogue
     */
    public WatchCatalogue findByBusinessKey(String key);


    /**
     * @param businessKeys
     * @return the total price considering the discount for the products requested
     */
    public double getPriceForSelectedItems(List<String> businessKeys);
}

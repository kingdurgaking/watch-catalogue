package com.maha.catalogue.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This is the entity class which refers to the watch catalogue data
 * Created by durga on 8/18/2020.
 */
@Entity
public class WatchCatalogue {

    /**
     * Primary key for the table. It is of type Long
     */
    @Id
    public Long id;

    /**
     * Business primary key. This could be a primary key in some other application.
     * Hence dangerous to use this is as primary key. It is of type String
     */
    public String businessKey;

    /**
     *Column holds the product name of type String
     */
    public String watchName;

    /**
     * Column holds unit price of the product of type double
     */
    public double unitPrice;

    /**
     * Column holds discount quantity of type integer
     */
    public int minDiscount;

    /**
     * Holds discount value for the discount quanity. Of type double
     */
    public double discountValue;

    public WatchCatalogue(Long id, String businessKey, String watchName, double unitPrice, int minDiscount, double discountValue) {
        this.id = id;
        this.businessKey = businessKey;
        this.watchName = watchName;
        this.unitPrice = unitPrice;
        this.minDiscount = minDiscount;
        this.discountValue = discountValue;
    }

    public WatchCatalogue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getWatchName() {
        return watchName;
    }

    public void setWatchName(String watchName) {
        this.watchName = watchName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getMinDiscount() {
        return minDiscount;
    }

    public void setMinDiscount(int minDiscount) {
        this.minDiscount = minDiscount;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }
}

package com.maha.catalogue.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This is the entity class which refers to the watch catalogue data
 * Created by durga on 8/18/2020.
 */
@Entity
@Getter
@Setter
public class WatchCatalogue {

    /**
     * Primary key for the table. It is of type Long
     */
    @Id
    @Schema(description = "primary key and unique identifer", example = "1")
    public Long id;

    /**
     * Business primary key. This could be a primary key in some other application.
     * Hence dangerous to use this is as primary key. It is of type String
     */
    @Schema(description = "business key primary key for tbe business data", example = "001")
    public String businessKey;

    /**
     *Column holds the product name of type String
     */
    @Schema(description = "name of the product", example = "Casio")
    public String watchName;

    /**
     * Column holds unit price of the product of type double
     */
    @Schema(description = "unit price of the product", example = "30")
    public double unitPrice;

    /**
     * Column holds discount quantity of type integer
     */
    @Schema(description = "minimum discout of the product", example = "2")
    public int minDiscount;

    /**
     * Holds discount value for the discount quanity. Of type double
     */
    @Schema(description = "minimum discout value of the product", example = "200")
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

/*    public Long getId() {
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
    }*/
}

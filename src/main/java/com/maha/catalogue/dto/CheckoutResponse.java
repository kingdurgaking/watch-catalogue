package com.maha.catalogue.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used in checkOut method of {@link com.maha.catalogue.controller.CatalogueController}
 * Used to return the price of the items requested
 * Created by durga on 8/18/2020.
 */
public class CheckoutResponse {

    @Schema(description = "Price of the items reqquested", example = "360")
    private double price;

    public CheckoutResponse(double price) {
        this.price = price;
    }

    public CheckoutResponse() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

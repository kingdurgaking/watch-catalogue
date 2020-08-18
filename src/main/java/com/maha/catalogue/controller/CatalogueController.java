package com.maha.catalogue.controller;

import com.maha.catalogue.entity.WatchCatalogue;
import com.maha.catalogue.service.CatalogueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by durga on 8/18/2020.
 */
@RestController
public class CatalogueController {

    private CatalogueService catalogueService;

    public CatalogueController(@Autowired CatalogueService catalogueService){
        this.catalogueService = catalogueService;
    }


    @Operation(summary = "get product by key " , description = " helps in geting the product by business key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404" , description = "No Product catalogue found"),
            @ApiResponse(responseCode = "200" , description = "Return product catalogue",
            content = @Content( mediaType = "application/json" , schema=@Schema(implementation = WatchCatalogue.class)))
    })
    @RequestMapping(method = RequestMethod.GET,produces = {"application/json"} , value = "/product/{id}")
    public ResponseEntity getCatalogue(@PathVariable String id){
        WatchCatalogue catalogue = catalogueService.findByBusinessKey(id);
        if(catalogue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product found");
        }else
            return ResponseEntity.status(HttpStatus.OK).body(catalogue);
    }

    @Operation(summary = "check out the price" , description = " Check out the price of the selection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "406" , description = "Invalid input or no matching of catalogues"),
            @ApiResponse(responseCode = "200" , description = "price",
                    content = @Content(mediaType = "application/json" , examples = @ExampleObject(name = "price" , value = "60")))
    })
    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"} , value = "/checkout")
    public ResponseEntity checkOut(@RequestBody List<String> businessKeys){
        double price = catalogueService.getPriceForSelectedItems(businessKeys);
        if(price > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new JSONObject().appendField("price" , price));
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error in request parameter");
    }
}

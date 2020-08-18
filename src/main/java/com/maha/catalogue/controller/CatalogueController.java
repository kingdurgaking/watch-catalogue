package com.maha.catalogue.controller;

import com.maha.catalogue.dto.CheckoutResponse;
import com.maha.catalogue.dto.GenericErrorResponse;
import com.maha.catalogue.entity.WatchCatalogue;
import com.maha.catalogue.service.CatalogueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            @ApiResponse(responseCode = "404" , description = "No Product catalogue found",
                    content = @Content( mediaType = "application/json" , schema=@Schema(implementation = GenericErrorResponse.class))),
            @ApiResponse(responseCode = "200" , description = "Return product catalogue",
            content = @Content( mediaType = "application/json" , schema=@Schema(implementation = WatchCatalogue.class)))
    })
    @RequestMapping(method = RequestMethod.GET,produces = {"application/json"} , value = "/product/{id}")
    public ResponseEntity getCatalogue(@PathVariable String id){
        WatchCatalogue catalogue = catalogueService.findByBusinessKey(id);
        if(catalogue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericErrorResponse("ERR1002", "No Product found"));
        }else
            return ResponseEntity.status(HttpStatus.OK).body(catalogue);
    }

    @Operation(summary = "get product by key " , description = " helps in geting the product by business key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404" , description = "No Product catalogue found",
                    content = @Content( mediaType = "application/json" , schema=@Schema(implementation = GenericErrorResponse.class))),
            @ApiResponse(responseCode = "200" , description = "Return product catalogue",
                    content = @Content( mediaType = "application/json" , array=@ArraySchema(schema = @Schema(implementation = WatchCatalogue.class ) )))
    })
    @RequestMapping(method = RequestMethod.GET,produces = {"application/json"} , value = "/products")
    public ResponseEntity getAllCatalogues(){
        List<WatchCatalogue> catalogue = catalogueService.getAll();
        if(catalogue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericErrorResponse("ERR1002", "No Product found"));
        }else
            return ResponseEntity.status(HttpStatus.OK).body(catalogue);
    }

    @Operation(summary = "List all catalogues" , description = " List all catalogues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404" , description = "No Product catalogue found",
                    content = @Content( mediaType = "application/json" , schema=@Schema(implementation = GenericErrorResponse.class))),
            @ApiResponse(responseCode = "200" , description = "price",
                    content = @Content(mediaType = "application/json" , schema=@Schema(implementation = CheckoutResponse.class)))
    })
    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"} , value = "/checkout")
    public ResponseEntity checkOut(@RequestBody List<String> businessKeys){
        double price = catalogueService.getPriceForSelectedItems(businessKeys);
        if(price > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new CheckoutResponse(price));
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericErrorResponse("ERR1001", "invalid data"));
    }
}

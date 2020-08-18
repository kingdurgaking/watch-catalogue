package com.maha.catalogue.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Generic Error Response holder
 * Created by durga on 8/18/2020.
 */

public class GenericErrorResponse {

    @Schema(description = "Error Code", example = "ERR101")
    private String errorCode;
    @Schema(description = "Error Description", example = "Invalid data")
    private String erroDesc;


    public GenericErrorResponse(String errorCode, String erroDesc) {
        this.errorCode = errorCode;
        this.erroDesc = erroDesc;
    }


    public GenericErrorResponse() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErroDesc() {
        return erroDesc;
    }

    public void setErroDesc(String erroDesc) {
        this.erroDesc = erroDesc;
    }
}

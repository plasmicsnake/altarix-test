/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Vladislav
 */
@ApiModel(description = "Dismiss request.")
public class DismissParams {
    @ApiModelProperty(notes = "Dismiss date")
    private String dismissDate;

    /**
     * @return the dismissDate
     */
    public String getDismissDate() {
        return dismissDate;
    }

    /**
     * @param dismissDate the dismissDate to set
     */
    public void setDismissDate(String dismissDate) {
        this.dismissDate = dismissDate;
    }
}

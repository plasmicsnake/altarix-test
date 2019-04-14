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
@ApiModel(description = "Parents structure.")
public class DepartmentParentTree {
    @ApiModelProperty(notes = "Department")
    private String departmentName;
    @ApiModelProperty(notes = "Department children")
    private DepartmentParentTree parent;
    

    /**
     * @return the parent
     */
    public DepartmentParentTree getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(DepartmentParentTree parent) {
        this.parent = parent;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

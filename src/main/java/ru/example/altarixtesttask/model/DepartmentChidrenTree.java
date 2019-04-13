/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 *
 * @author Vladislav
 */
@ApiModel(description = "Subdepartments structure.")
public class DepartmentChidrenTree {

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the children
     */
    public List<DepartmentChidrenTree> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<DepartmentChidrenTree> children) {
        this.children = children;
    }
    @ApiModelProperty(notes = "Department")
    private String department;
    @ApiModelProperty(notes = "Department children")
    private List<DepartmentChidrenTree> children;
}

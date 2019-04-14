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
@ApiModel(description = "Department info.")
public class BalanceResult {
    @ApiModelProperty(notes = "Department")
    private Department department;
    @ApiModelProperty(notes = "Department balance")
    private Integer balance;
    
    public BalanceResult(Department d) {
        department = d;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @return the balance
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}

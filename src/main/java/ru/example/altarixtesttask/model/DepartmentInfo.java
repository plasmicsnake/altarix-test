/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 *
 * @author Vladislav
 */
@ApiModel(description = "Department info.")
public class DepartmentInfo {
    @ApiModelProperty(notes = "Department name")
    private String departmentName;
    @ApiModelProperty(notes = "Department creation date")
    private Date createdAt;
    @ApiModelProperty(notes = "Department team leader")
    private String teamLeader;
    @ApiModelProperty(notes = "Employees count in department")
    private Integer employCount;


    /**
     * @return the employCount
     */
    public Integer getEmployCount() {
        return employCount;
    }

    /**
     * @param employCount the employCount to set
     */
    public void setEmployCount(Integer employCount) {
        this.employCount = employCount;
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

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the teamLeader
     */
    public String getTeamLeader() {
        return teamLeader;
    }

    /**
     * @param teamLeader the teamLeader to set
     */
    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }
}

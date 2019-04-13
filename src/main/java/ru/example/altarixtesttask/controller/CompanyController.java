/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ru.example.altarixtesttask.exception.ResourceNotFoundException;
import ru.example.altarixtesttask.exception.ValidateException;
import ru.example.altarixtesttask.model.Department;
import ru.example.altarixtesttask.repository.DepartmentRepository;
import ru.example.altarixtesttask.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.altarixtesttask.repository.EmployeePositionRepository;
import ru.example.altarixtesttask.validation.DepartmentValidation;
import ru.example.altarixtesttask.repository.DepartmentBalanceRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Vladislav
 */
@Api(value="MyCompany Management System", description = "Operations for managment of company")
@RestController
public class CompanyController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentBalanceRepository departmentBalanceRepositpry;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeePositionRepository employeePositionRepository;

    //D1 - tested
    @ApiOperation(value = "Add new department")
    @ExceptionHandler({ResourceNotFoundException.class, ValidateException.class})
    @PostMapping("/department")
    public @ResponseBody Department addDepartment(
            @ApiParam(value = "Department object store in database table", required = true) Department d) {
        DepartmentValidation.dublicateDepartment(departmentRepository, d);
        return departmentRepository.save(d);
    }

}

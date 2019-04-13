/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.validation;

import ru.example.altarixtesttask.exception.ValidateException;
import ru.example.altarixtesttask.model.Department;
import ru.example.altarixtesttask.model.Employee;
import ru.example.altarixtesttask.repository.DepartmentRepository;
import ru.example.altarixtesttask.repository.EmployeeRepository;
import java.util.List;

/**
 *
 * @author Vladislav
 */
public class DepartmentValidation {
    
    public static void dublicateDepartment(DepartmentRepository rep, Department d) throws ValidateException{
        Department dublicate = rep.findByName(d.getName()).orElse(null);
        if (dublicate != null) {
            throw new ValidateException("Already has department with name " + d.getName());
        }
    }
    
    public static void hasSubdepartments(DepartmentRepository rep, Department dept) throws ValidateException{
        List<Department> subDeparments = rep.findByParentDepartmentId(dept.getId());
        if (subDeparments.size() > 0) {
            throw new ValidateException("Depapartment with id " + dept.getId() + " has subdepartments");
        }
    }
    
    public static void hasEmployees(EmployeeRepository rep, Department dept) throws ValidateException{
        List<Employee> employees = rep.findByDepartmentId(dept.getId());
        if (employees.size() > 0) {
            throw new ValidateException("Depapartment with id " + dept.getId() + " has employees");
        }
    }
}

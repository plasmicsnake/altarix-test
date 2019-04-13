/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.repository;

import ru.example.altarixtesttask.model.Department;
import ru.example.altarixtesttask.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladislav
 */
@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
    List<Employee> findByDepartmentId(Integer DepartmentId);
}

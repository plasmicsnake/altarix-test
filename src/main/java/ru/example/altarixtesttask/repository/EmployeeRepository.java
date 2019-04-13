/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.repository;

import ru.example.altarixtesttask.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladislav
 */
@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
    List<Employee> findByDepartmentId(Integer DepartmentId);
    List<Employee> findByDepartmentIdAndChief(Integer DepartmentId, Boolean chief);
    Integer countByDepartmentId(Integer DepartmentId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.scheduling;

import ru.example.altarixtesttask.model.Department;
import ru.example.altarixtesttask.model.DepartmentBalance;
import ru.example.altarixtesttask.model.Employee;
import ru.example.altarixtesttask.repository.DepartmentRepository;
import ru.example.altarixtesttask.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.example.altarixtesttask.repository.DepartmentBalanceRepository;

/**
 *
 * @author Vladislav
 */
@Component
public class DepartmentSchedulingTasks {
    
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    DepartmentBalanceRepository departmentBalanceRepositpry;
    
    @Scheduled(fixedDelay = 300000, initialDelay = 300000)
    public void balanceTask() {
        List<Department> departments = departmentRepository.findAll();
        departments.stream().forEach(dep -> {
            DepartmentBalance depBalance = departmentBalanceRepositpry.findByDepartmentId(dep.getId())
                    .orElse(new DepartmentBalance());
            depBalance.setDepartment(dep);
            List<Employee> empList = employeeRepository.findByDepartmentId(dep.getId());
            depBalance.setBalance(0);
            empList.stream().forEach(emp -> {
                depBalance.setBalance(depBalance.getBalance() + emp.getSalary());
            });
            departmentBalanceRepositpry.save(depBalance);
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import ru.example.altarixtesttask.exception.ResourceNotFoundException;
import ru.example.altarixtesttask.exception.ValidateException;
import ru.example.altarixtesttask.model.Department;
import ru.example.altarixtesttask.model.Employee;
import ru.example.altarixtesttask.repository.DepartmentRepository;
import ru.example.altarixtesttask.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.altarixtesttask.repository.EmployeePositionRepository;
import ru.example.altarixtesttask.validation.DepartmentValidation;
import ru.example.altarixtesttask.repository.DepartmentBalanceRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.example.altarixtesttask.model.BalanceResult;
import ru.example.altarixtesttask.model.DepartmentBalance;
import ru.example.altarixtesttask.model.DepartmentChidrenTree;
import ru.example.altarixtesttask.model.DepartmentInfo;
import ru.example.altarixtesttask.model.DepartmentParentTree;

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
    
    //D2 - tested
    @ApiOperation(value = "Edit department")
    @ExceptionHandler({ResourceNotFoundException.class, ValidateException.class})
    @PutMapping("/departments/{deptId}")
    public @ResponseBody Department editDepartment(
            @ApiParam(value = "Deparment id", required = true) @PathVariable Integer deptId,
            @ApiParam(value = "Update Department object", required = true) Department d) {
        DepartmentValidation.dublicateDepartment(departmentRepository, d);
        return departmentRepository.findById(deptId)
                .map(department -> {
                    department.setName(d.getName());
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId));
    }
    
    //D3 tested
    @ApiOperation(value = "Delete department")
    @ExceptionHandler({ResourceNotFoundException.class, ValidateException.class})
    @DeleteMapping("/departments/{deptId}")
    public @ResponseBody ResponseEntity<?> deleteDepartment(
            @ApiParam(value = "Deparment id", required = true) @PathVariable Integer deptId) {
        return departmentRepository.findById(deptId)
                .map(department -> {
                    DepartmentValidation.hasSubdepartments(departmentRepository, department);
                    DepartmentValidation.hasEmployees(employeeRepository, department);
                    DepartmentBalance deptBalance = departmentBalanceRepositpry.findByDepartmentId(deptId).orElse(null);
                    if (deptBalance != null) {
                        departmentBalanceRepositpry.delete(deptBalance);
                    }
                    departmentRepository.delete(department);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId));
    }
    
    //D4 - tested
    @ApiOperation(value = "View a department info")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ExceptionHandler({ResourceNotFoundException.class})
    @GetMapping(value = "/department/info/{dept:\\d+}")
    public DepartmentInfo departmentInfo(
            @ApiParam(value = "Deparment id", required = true) @PathVariable("dept") int dept) {
        DepartmentInfo result = departmentRepository.findById(dept).map(dep -> {
            DepartmentInfo r = new DepartmentInfo();
            r.setCreatedAt(dep.getCreatedAt());
            r.setDepartmentName(dep.getName());
            List<Employee> teamLeader = employeeRepository.findByDepartmentIdAndChief(dep.getId(), Boolean.TRUE);
            if (teamLeader.size() > 0) {
                r.setTeamLeader(teamLeader.get(0).getSurname() + " " + teamLeader.get(0).getName() + " " + (teamLeader.get(0).getPatronymic() != null ? teamLeader.get(0).getPatronymic() : ""));
            }
            r.setEmployCount(employeeRepository.countByDepartmentId(dep.getId()));
            return r;
        }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + dept));
        return result;
    }
    
    //D5 -tested
    @ApiOperation(value = "View a list of direct subdepartments of department")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/departments/{deptId}/subdepartments")
    @ExceptionHandler({ResourceNotFoundException.class})
    public @ResponseBody DepartmentChidrenTree getSubDepartments(
            @ApiParam(value = "Deparment id for view", required = true) @PathVariable Integer deptId, Department d) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId));
        DepartmentChidrenTree result = new DepartmentChidrenTree();
        result.setDepartment(department.getName());
        result.setChildren(grabSubdepartments(department));
        return result;
    }
    
    //D6 tested
    @ApiOperation(value = "View a list of all subdepartments of department")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/departments/{deptId}/subdepartments/all")
    @ExceptionHandler({ResourceNotFoundException.class})
    public @ResponseBody DepartmentChidrenTree getSubDepartmentsAll(
            @ApiParam(value = "Deparment id for view", required = true) @PathVariable Integer deptId) {
        Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId));
            DepartmentChidrenTree result = new DepartmentChidrenTree();
            result.setDepartment(department.getName());
            result.setChildren(grabSubdepartmentsAll(department));
            return result;
    }
    
    //D7 tested
    @ApiOperation(value = "Move department to another department")
    @ExceptionHandler({ResourceNotFoundException.class, ValidateException.class})
    @PutMapping("/department/{deptId}/moveto/{newParentDeptId}")
    public @ResponseBody Department moveDepartmentTo(
            @ApiParam(value = "Subdeparment id", required = true) @PathVariable Integer deptId, 
            @ApiParam(value = "New Parent Deparment id for this subdepartment", required = true) @PathVariable Integer newParentDeptId) {
        DepartmentValidation.idNotEqualsParent(deptId, newParentDeptId);
        return departmentRepository.findById(deptId).map(d -> {
            d.setParentDepartment(departmentRepository.findById(newParentDeptId)
                    .orElseThrow(() -> new ResourceNotFoundException("New parent department not found with id " + newParentDeptId)));
            return departmentRepository.save(d);
        }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId));
    }
    
    //D8 -tested
    @ApiOperation(value = "View a list of all parents of department")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/departments/{deptId}/parents")
    @ExceptionHandler({ResourceNotFoundException.class})
    public @ResponseBody DepartmentParentTree getDepartmentParents(
            @ApiParam(value = "Deparment id for view", required = true) @PathVariable Integer deptId) {
        
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId));
        DepartmentParentTree result = grabParentDepartments(department);
        return result;
    }

    //D9 - tested
    @ApiOperation(value = "Find department by name")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/departments/findbyname/{deptName}")
    @ExceptionHandler({ResourceNotFoundException.class})
    public @ResponseBody Department findDepartmentsByName(
            @ApiParam(value = "Deparment name", required = true) @PathVariable String deptName) {
        return  departmentRepository.findByName(deptName).orElseThrow(() -> new ResourceNotFoundException("Department not found with name " + deptName));
    }

    //D10 - tested
    @ApiOperation(value = "Calculate department balance")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/departments/{deptId}/balance")
    @ExceptionHandler({ResourceNotFoundException.class})
    public @ResponseBody BalanceResult getDepartmentBalance(
            @ApiParam(value = "Deparment id", required = true) @PathVariable Integer deptId) {
        BalanceResult result = new BalanceResult(departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId)));
        List<Employee> empList = employeeRepository.findByDepartmentId(deptId);
        result.setBalance(0);
        empList.stream().forEach(emp -> {
            result.setBalance(result.getBalance() + emp.getSalary());
        });
        return result;
    }

    //E1 - tested
    @ApiOperation(value = "View a list of employees of department", response = Employee.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/department/{deptId}/employees")
    @ExceptionHandler({ResourceNotFoundException.class})
    public @ResponseBody List<Employee> employeeList(
            @ApiParam(value = "Deparment id", required = true) @PathVariable Integer deptId) {
        return employeeRepository.findByDepartmentId(departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + deptId)).getId());
    }
    
    private List<DepartmentChidrenTree> grabSubdepartments(Department parent) {
        List<DepartmentChidrenTree> result = new ArrayList<>();
        departmentRepository.findByParentDepartmentId(parent.getId()).stream().forEach(dep -> {
            DepartmentChidrenTree tree = new DepartmentChidrenTree();
            tree.setDepartment(dep.getName());
            result.add(tree);
        });
        return result;
    }
    
    private List<DepartmentChidrenTree> grabSubdepartmentsAll(Department parent) {
        List<DepartmentChidrenTree> result = new ArrayList<>();
        List<Department> subDepartments = departmentRepository.findByParentDepartmentId(parent.getId());
        subDepartments.stream().forEach(sub -> {
            DepartmentChidrenTree subDepTree = new DepartmentChidrenTree();
            subDepTree.setDepartment(sub.getName());
            subDepTree.setChildren(grabSubdepartmentsAll(sub));
            result.add(subDepTree);
        });
        return result;
    }
    
    private DepartmentParentTree grabParentDepartments(Department parent) {
        DepartmentParentTree result = new DepartmentParentTree();
        result.setDepartmentName(parent.getName());
        if (parent.getParentDepartment() != null) {
            result.setParent(grabParentDepartments(parent.getParentDepartment()));
        }
        return result;
    }
}

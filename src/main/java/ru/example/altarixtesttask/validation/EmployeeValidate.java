/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.validation;

import ru.example.altarixtesttask.exception.ValidateException;
import ru.example.altarixtesttask.model.Department;
import ru.example.altarixtesttask.model.Employee;
import ru.example.altarixtesttask.repository.EmployeeRepository;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Vladislav
 */
public class EmployeeValidate {
    public static void validDissmisDate(Date recruit, Date dissmis)  throws ValidateException {
        if (recruit.compareTo(dissmis) >= 0) {
            throw new ValidateException("Recruite date is greater then dissmis date");
        }
    }
    
    public static void validRecruiteDate(Employee e)  throws ValidateException {
        if (e.getBirthday().compareTo(e.getRecruitmentDate()) >= 0) {
            throw new ValidateException("Recruite date is less then dissmis date");
        }
    }
    
    public static void validDateFormat(String date)  throws ValidateException {
        String regEx = "\\d{4}-((0?[1-9])|([1][0-2]))-((0?[1-9])|([12][0-9])|([3][01]))";
        if (!Pattern.compile(regEx).matcher(date).matches()) {
            throw new ValidateException("Bad date format");
        }
    }
    
    public static void validEmployeeName(String name)  throws ValidateException {
        if (!Pattern.compile("[А-Я][а-яА-Я\\-]*").matcher(name).matches()) {
            throw new ValidateException("Bad name/surname/patronymic format");
        }
    }
    
    public static void validEmployeePhone(Employee e)  throws ValidateException {
        if (!Pattern.compile("((\\+[0-9]{1,2})|8)"
                + "((\\(\\d{3}\\))|(\\d{3}))"
                + "\\d{3}((\\s)|(\\-))?\\d{2}((\\s)|(\\-))?\\d{2}").matcher(e.getPhone()).matches()) {
            throw new ValidateException("Bad phone format");
        }
    }
    
    public static void validEmployeeEmail(Employee e)  throws ValidateException {
        if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(e.getEmail()).matches()) {
            throw new ValidateException("Bad email format");
        }
    }
    
    public static void validEmployeeDissmis(Employee e)  throws ValidateException {
        if (e.getDismissalDate() != null) {
            throw new ValidateException("No diddmis on create");
        }
    }
    
    public static void validTeamLeader(EmployeeRepository rep, Department dept, Employee e)  throws ValidateException {
        List<Employee> teamLeader = rep.findByDepartmentIdAndChief(dept.getId(), Boolean.TRUE);
        if (teamLeader.size() > 0 && e.getChief().equals(Boolean.TRUE)) {
            throw new ValidateException("Department already has Team Leader");
        }
    }
    
    public static void validTeamLeaderSalary(EmployeeRepository rep, Department dept, Employee e)  throws ValidateException {
        List<Employee> teamLeader = rep.findByDepartmentIdAndChief(dept.getId(), Boolean.TRUE);
        if (teamLeader.size() > 0 && e.getSalary().compareTo(teamLeader.get(0).getSalary()) >= 0) {
            throw new ValidateException("Team Leader salary less then employee salary");
        }
    }
}

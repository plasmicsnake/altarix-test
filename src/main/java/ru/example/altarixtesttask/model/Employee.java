/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Vladislav
 */
@Entity
@Table(name = "employee")
@ApiModel(description = "All details about the Employee.")
public class Employee {
    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated employee ID")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ApiModelProperty(notes = "Department")
    private Department department;
    
    @Column(name = "name", nullable = false)
    @ApiModelProperty(notes = "Employee name")
    private String name;
    
    @Column(name = "surname", nullable = false)
    @ApiModelProperty(notes = "Employee surname")
    private String surname;
    
    @Column(name = "patronymic")
    @ApiModelProperty(notes = "Employee patronymic")
    private String patronymic;
    
    @Column(name = "sex", nullable = false)
    @ApiModelProperty(notes = "Employee sex")
    private String sex;
    
    @Column(name = "birthday", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Employee birthday")
    private Date birthday;
    
    @Column(name = "phone", nullable = false)
    @ApiModelProperty(notes = "Employee phone")
    private String phone;
    
    @Column(name = "email", nullable = false)
    @ApiModelProperty(notes = "Employee email")
    private String email;
    
    @Column(name = "recruitment_date", nullable = false)
    @ApiModelProperty(notes = "Recruitment date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recruitmentDate;
    
    @Column(name = "dismissal_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Dismiss date")
    private Date dismissalDate;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "position", nullable = false)
    @JsonIgnore
    @ApiModelProperty(notes = "Employee rank")
    private EmployeePosition position;
    
    @Column(name = "salary", nullable = false)
    @ApiModelProperty(notes = "Employee salary")
    private Integer salary;
    
    @Column(name = "chief")
    @ApiModelProperty(notes = "Team Leader sign")
    private Boolean chief;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * @param patronymic the patronymic to set
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the recruitmentDate
     */
    public Date getRecruitmentDate() {
        return recruitmentDate;
    }

    /**
     * @param recruitmentDate the recruitmentDate to set
     */
    public void setRecruitmentDate(Date recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    /**
     * @return the dismissalDate
     */
    public Date getDismissalDate() {
        return dismissalDate;
    }

    /**
     * @param dismissalDate the dismissalDate to set
     */
    public void setDismissalDate(Date dismissalDate) {
        this.dismissalDate = dismissalDate;
    }

    /**
     * @return the salary
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     * @return the chief
     */
    public Boolean getChief() {
        return chief;
    }

    /**
     * @param chief the chief to set
     */
    public void setChief(Boolean chief) {
        this.chief = chief;
    }

    /**
     * @return the position
     */
    public EmployeePosition getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(EmployeePosition position) {
        this.position = position;
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
}

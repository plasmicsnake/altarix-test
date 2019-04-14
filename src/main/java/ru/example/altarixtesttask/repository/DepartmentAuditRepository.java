/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.altarixtesttask.model.DepartmentAudit;

/**
 *
 * @author Vladislav
 */
@Repository
public interface DepartmentAuditRepository extends JpaRepository<DepartmentAudit, Integer> {
    
}

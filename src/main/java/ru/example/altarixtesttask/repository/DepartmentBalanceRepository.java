/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.repository;

import ru.example.altarixtesttask.model.DepartmentBalance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladislav
 */
@Repository
public interface DepartmentBalanceRepository  extends JpaRepository<DepartmentBalance, Integer>{
    Optional<DepartmentBalance> findByDepartmentId(Integer deptId);
}

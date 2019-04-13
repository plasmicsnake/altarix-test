/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.example.altarixtesttask.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.altarixtesttask.model.EmployeePosition;

/**
 *
 * @author Vladislav
 */
@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Integer>{
    Optional<EmployeePosition> findByName(String positionName);
}

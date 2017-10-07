package com.programmers.apply.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.programmers.apply.entities.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByName(@Param("employee_name") String name);

    public Employee findById(Long id);

    public List<Employee> findAll();

}
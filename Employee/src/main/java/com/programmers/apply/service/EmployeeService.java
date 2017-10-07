package com.programmers.apply.service;

import java.util.List;

import com.programmers.apply.entities.Employee;

public interface EmployeeService  {
    public Employee getEmployeeByName(String name);

	public List<Employee> getAllEmployees();

	public Employee save(Employee employee);
}

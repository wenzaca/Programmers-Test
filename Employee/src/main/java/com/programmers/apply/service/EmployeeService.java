package com.programmers.apply.service;

import java.util.List;

import com.programmers.apply.entities.Employee;

public interface EmployeeService  {
    /**
     * Method that finds an Employee by his name, if the Employee is not found it return a null Entity
     * @param name
     * @return
     */
    public Employee getEmployeeByName(String name);

	/**
	 * Method that return all employees in the database, if the base is empty, return null
	 * @return
	 */
	public List<Employee> getAllEmployees();

	/**
	 * It saves a new Employee
	 * @param employee
	 * @return
	 */
	public Employee save(Employee employee);
}

package com.programmers.apply.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.programmers.apply.entities.Employee;
import com.programmers.apply.repository.EmployeeRepository;
import com.programmers.apply.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	
	@Before
	public void setUp() {
        Employee employee = new Employee("Wendler Zacariotto","wenzaca@gmail.com");
        Employee employee1= new Employee("Maria Silva","msilva@gmail.com");
        List<Employee> employees = new LinkedList<>();
        employees.add(employee1);
        employees.add(employee);
        
	    Mockito.when(employeeRepository.findByName(employee.getName()))
	      .thenReturn(employee);
	    Mockito.when(employeeRepository.findByName(employee1.getName()))
	      .thenReturn(employee1);
	    Mockito.when(employeeRepository.findAll()).thenReturn(employees);
	}
	
	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
	    String name = "Wendler Zacariotto";
	    Employee found = employeeService.getEmployeeByName(name);
	  
	     assertThat(found.getName())
	      .isEqualTo(name);
	 }
	
	@Test
	public void whenMoreThanOneEmployee_thenListThemAll() {
        List<Employee> employees = employeeService.getAllEmployees();
	     assertThat(employees.size())
	      .isEqualTo(2);
	 }
}

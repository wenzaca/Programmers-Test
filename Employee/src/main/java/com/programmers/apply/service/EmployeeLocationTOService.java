package com.programmers.apply.service;

import org.springframework.stereotype.Service;

import com.programmers.apply.entities.EmployeeLocationTO;

@Service
public interface EmployeeLocationTOService {
   
	public EmployeeLocationTO save(EmployeeLocationTO employeeWithLocation);
}

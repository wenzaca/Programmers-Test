package com.programmers.apply.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmers.apply.entities.EmployeeLocationTO;
import com.programmers.apply.repository.EmployeeRepository;
import com.programmers.apply.repository.PositionRepository;
import com.programmers.apply.service.EmployeeLocationTOService;

@Service
public class EmployeeLocationTOServiceImpl implements EmployeeLocationTOService {
 
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private PositionRepository positionRepository;

	@Override
	public EmployeeLocationTO save(EmployeeLocationTO employeeWithLocation) {
		positionRepository.save(employeeWithLocation.getPosition());
		employeeWithLocation.getEmployee().setPosition(employeeWithLocation.getPosition());
		employeeRepository.save(employeeWithLocation.getEmployee());
		return null;
	}
 
}

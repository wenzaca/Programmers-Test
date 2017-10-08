package com.programmers.apply.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.programmers.apply.entities.Employee;
import com.programmers.apply.entities.EmployeeLocationTO;
import com.programmers.apply.entities.Position;
import com.programmers.apply.service.EmployeeLocationTOService;
import com.programmers.apply.service.EmployeeService;
import com.programmers.apply.service.PositionService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeLocationTOService employeLocationTOService;

	@Autowired
	private PositionService positionService;

	/**
	 * 
	 * ***MAIN PAGE*** Method to fill list view in first page
	 * 
	 * @return to index view (list of employees)
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		ModelAndView modelAndView = new ModelAndView("/index"); //$NON-NLS-1$
		modelAndView.addObject("employees", employeeService.getAllEmployees()); //$NON-NLS-1$
		return modelAndView;
	}

	/**
	 * Method that tries to insert a new employee, it tries to find the
	 * longitude and latitude from the given address If it doesn't find it does
	 * not saved and give a response in the URL that the employee has not saved
	 * 
	 * @param employee
	 * @param binding
	 * @param redirectAttributes
	 * @return to index view (list of employees)
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public ModelAndView insertOperator(@Valid @ModelAttribute Employee employee, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:employees");
		Position position = positionService.getLongitudeLatitudeByAdress(employee.getAddress());
		if (!binding.hasErrors() && position != null) {
			employeLocationTOService.save(new EmployeeLocationTO(employee, position));
			modelAndView.addObject("hasSaved", true);
		} else {
			modelAndView.addObject("hasSaved", false);
		}
		return modelAndView;
	}

	/**
	 * This method redirect to the Google Maps API page with the address
	 * location saved by the address
	 * 
	 * @param employee
	 * @return to Map view
	 */
	@RequestMapping("/showMe")
	public ModelAndView findMe(@ModelAttribute Employee employee) {
		Employee employeeByName = employeeService.getEmployeeByName(employee.getName());
		if (employeeByName != null) {
			Position position = employeeByName.getPosition();
			ModelAndView modelAndView = new ModelAndView("/map");
			modelAndView.addObject("position", position);
			return modelAndView;
		} else {
			System.out.println("Not found this employee");
			return null;
		}
	}
}

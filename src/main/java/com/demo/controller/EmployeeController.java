package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.model.Employee;
import com.demo.service.EmployeeServices;

@Controller
@RequestMapping
public class EmployeeController extends EmployeeAction {

	@Autowired
	EmployeeServices employeeServices;

	// lode add employee form
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String addEmp(Model model) {
		model.addAttribute("insertEmployee", new Employee());
		return "AddEmployee";

	}

	// save employee form
	@PostMapping("/insertEmployee")
	public String insertEmployee(@ModelAttribute("insertEmployee") @Valid Employee emp, BindingResult result, Model model) {
		//performCustomValidation(emp, result);
		if (result.hasErrors()) {
			
			for (ObjectError error : result.getAllErrors()) {
		        System.out.println(error.getDefaultMessage());
		    }
			
			model.addAttribute("insertEmployee", emp);
			return "AddEmployee";
		}

		employeeServices.addEmp(emp);
		return "redirect:/employeeReport";

	}

	// lode employee data
	@GetMapping("employeeReport")
	public String lodeEmployee(Model m) {
		m.addAttribute("employee", employeeServices.getAllEmp());
		m.addAttribute("title", "Employee Report");

		return "EmployeeReport";
	}

	// lode edit form

	@GetMapping("/editEmployee/{id}")
	public String lodeEditForm(@PathVariable(value = "id") Long id, Model m) {
		Employee emp = employeeServices.getById(id);

		System.out.println(emp);
		m.addAttribute("employee", emp);
		m.addAttribute("title", "Edit Employee");

		return "EditEmployee";

	}

	@PostMapping("/editEmployee/updateEmployee")
	public String updateEmp(@ModelAttribute("updateEmployee") @Valid Employee emp, BindingResult result) {

		if (result.hasErrors()) {
			return "AddEmployee";
		}
		
		employeeServices.updateEmp(emp);
		return "redirect:/employeeReport";

	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeServices.deleteEmployee(id);

		return "redirect:/employeeReport";
	}

}
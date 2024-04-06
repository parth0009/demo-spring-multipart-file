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

import com.demo.model.Tester;
import com.demo.service.TesterServices;

@Controller
@RequestMapping
public class TesterController extends TesterAction {

	@Autowired
	TesterServices testerServices;

	// lode add employee form
	@RequestMapping(value = "/addTester", method = RequestMethod.GET)
	public String addTester(Model model) {
		model.addAttribute("insertTester", new Tester());
		return "AddTester";

	}

	// save employee form
	@PostMapping("/insertTester")
	public String insertTester(@ModelAttribute("insertTester") @Valid Tester tester, BindingResult result, Model model) {
		//performCustomValidation(emp, result);
		if (result.hasErrors()) {
			model.addAttribute("insertTester", tester);
			return "AddTester";
		}

		testerServices.addTester(tester);
		return "redirect:/testerReport";

	}

	// lode employee data
	@GetMapping("testerReport")
	public String lodeTester(Model m) {
		m.addAttribute("tester", testerServices.getAllTester());
		m.addAttribute("title", "Tester Report");

		return "TesterReport";
	}

	// lode edit form

	@GetMapping("/editTester/{id}")
	public String lodeEditForm(@PathVariable(value = "id") Long id, Model m) {
		Tester tester = testerServices.getById(id);

		System.out.println(tester);
		m.addAttribute("tester", tester);
		m.addAttribute("title", "Edit Tester");

		return "EditTester";

	}

	@PostMapping("/editTester/updateTester")
	public String updateTester(@ModelAttribute("updateTester") @Valid Tester tester, BindingResult result) {

		if (result.hasErrors()) {
			return "AddTester";
		}
		
		testerServices.updateTester(tester);
		return "redirect:/testerReport";

	}

	@GetMapping("/deleteTester/{id}")
	public String deleteTester(@PathVariable Long id) {
		testerServices.deleteTester(id);

		return "redirect:/testerReport";
	}

}
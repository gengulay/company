package com.gengulay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gengulay.demo.dao.EmployeeDAO;
import com.gengulay.demo.entity.EmployeeEntity;

@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {

	private EmployeeDAO employeeDAO;

	@Autowired
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@RequestMapping({ "/list", "/" })
	public String getAllEmployees(Model model) {
		model.addAttribute("employees", employeeDAO.getAllEmployees());
		return "employees";
	}

	@GetMapping(path = "/{id}")
	public EmployeeEntity getCourse(@PathVariable int id) {
		return employeeDAO.getEmployeeById(id);
	}

}

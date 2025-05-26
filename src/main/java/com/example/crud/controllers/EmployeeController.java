package com.example.crud.controllers;
import java.util.List;
import com.example.crud.services.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crud.entity.Employee;
import com.example.crud.services.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImp employeeServiceImp;
	
	@Autowired
	private EmployeeService employeeService;

    EmployeeController(EmployeeServiceImp employeeServiceImp) {
        this.employeeServiceImp = employeeServiceImp;
    }
	
	@GetMapping("/home")
	String homePage() {
		return "index";
	}

	@GetMapping("/all")
	String listPage(Model model) {
		return findPages(1,model);
	}
	
	@GetMapping("/add")
	public String addPage(Model model){
		model.addAttribute("employee",new Employee());
		return "add";
	}
	
	@PostMapping("/addemmployee")
	String addEmployee(Employee employee) {
		employeeService.addEmployee(employee);
		return "redirect:/employee/all";
	}
	
	@GetMapping("/updateForm/{id}")
	public String updateFormPage(@PathVariable (value="id") Long id ,Model model) {
		Employee employee = employeeService.findUsingId(id);
		 model.addAttribute("employee", employee);
		return "updateEmployee";
	}

	@GetMapping("/deleteEmployee/{id}")
	String deleteEmployee(@PathVariable (value="id") Long id ) {
		employeeService.deleteEmployee(id);
		return "redirect:/employee/all";
	}
	
	@GetMapping("/page/{pageNumber}")
	String findPages(@PathVariable (value = "pageNumber") int pageNumber , Model model) {
		Page<Employee> page = employeeService.findPaginated(pageNumber,7);
		List<Employee> listofEmployeesPage  = page.getContent();
		model.addAttribute("ListEmployee",listofEmployeesPage);
		model.addAttribute("totalpages",page.getTotalPages());
		model.addAttribute("totalIteams",page.getNumberOfElements());
		model.addAttribute("currentPage",pageNumber);
		
		return "list";
		
	}
}

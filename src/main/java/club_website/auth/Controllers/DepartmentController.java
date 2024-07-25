package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Department;
import club_website.auth.Services.DepartmentService;

@RestController
@RequestMapping("/api/dep")
@CrossOrigin("*")
public class DepartmentController {
	
	@Autowired
	private DepartmentService depService;
	
	
//	@GetMapping("{id}")
//	public Department getDepartmentById(@PathVariable Integer id) {
//		return depService.getDepartmentById(id);
//	}
	
	@GetMapping
	public List<Department> getDepartments() {
		return depService.getDepartments();
	}
	
	@PostMapping()
	@PreAuthorize("hasAuthority('SUPER ADMIN')")
	public Department addDepartment(@RequestBody Department d) {
		return depService.addDepartment(d);
	}
}

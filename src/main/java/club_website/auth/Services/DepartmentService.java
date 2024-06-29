package club_website.auth.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;

@Service
public interface DepartmentService {
	
	public Department addDepartment(Department d);
	public List<Department> getDepartments();
	public Department getDepartmentById(Integer id);
	
}

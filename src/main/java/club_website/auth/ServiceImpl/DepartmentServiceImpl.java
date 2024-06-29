package club_website.auth.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepo depRepo;
	
	@Override
	public Department addDepartment(Department d) {
		// TODO Auto-generated method stub
		return depRepo.save(d);
	}

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return depRepo.findAll();
	}

	@Override
	public Department getDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Department> d=depRepo.findById(id);
		return d.isPresent()?d.get():null;
	}
	
	
}

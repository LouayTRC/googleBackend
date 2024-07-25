package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.MonthScore;
import club_website.auth.Services.MonthScoreService;

@RequestMapping("/api/ms")
@CrossOrigin("*")
@RestController
public class MonthScoreController {
	
	@Autowired
	private MonthScoreService monthScoreService;
	
//	@PostMapping("/{id}")
//	public MonthScore addMonthScore(@PathVariable Integer id) {
//		return monthScoreService.addMonthScore(id);
//	}
	
	@PutMapping("/{id}")
	public MonthScore updateMonthScore(@PathVariable Integer id,@RequestBody MonthScore ms) {
		return monthScoreService.updateMonthScore(id,ms);
	}
	
	@GetMapping("/{id}")
	public List<MonthScore> getMonthsScoresByMember(@PathVariable Integer id) {
		return monthScoreService.getMsByMember(id);
	}
}

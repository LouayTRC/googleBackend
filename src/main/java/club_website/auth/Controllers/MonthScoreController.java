package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.MonthScore;
import club_website.auth.Models.UpdateMsRequest;
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
	
	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MonthScore updateMonthScore(@PathVariable Integer id,@RequestBody UpdateMsRequest request,@RequestHeader("Authorization") String token) {
		System.out.println("!d"+request);
		return monthScoreService.updateMonthScore(id,request, token.substring(7));
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('MEMBER')")
	public List<MonthScore> getMonthsScoresByMember(@RequestHeader("Authorization") String token) {
		return monthScoreService.getMsByMember(token.substring(7));
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<MonthScore> getAllMs() {
		return monthScoreService.getAllMs();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MonthScore getMsById(@PathVariable Integer id) {
		return monthScoreService.getMsById(id);
	}
}

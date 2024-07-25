package club_website.auth.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Member;
import club_website.auth.Models.MonthScore;
@Repository
public interface MonthScoreRepo extends JpaRepository<MonthScore, Integer>{
	public List<MonthScore> findByMember(Member m);
}

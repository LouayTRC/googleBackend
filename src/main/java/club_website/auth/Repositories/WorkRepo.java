package club_website.auth.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Member;
import club_website.auth.Models.Task;
import club_website.auth.Models.User;
import club_website.auth.Models.Work;
@Repository
public interface WorkRepo extends JpaRepository<Work, Integer>{
	
	public List<Work> findByTask(Task task);
	
	@Query("SELECT w FROM Work w JOIN w.task t WHERE w.member = :member AND t.deleted = false")
	public List<Work> findByMember(Member member);

	@Query("SELECT w FROM Work w WHERE w.member = :member AND w.dateDepo BETWEEN :startDate AND :endDate")
    List<Work> findWorksByMemberAndPreviousMonth(@Param("member") Member member,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);

	@Query("SELECT w FROM Work w JOIN w.task t WHERE t.deleted = false")
    public List<Work> findAllWorks();
	
	
}

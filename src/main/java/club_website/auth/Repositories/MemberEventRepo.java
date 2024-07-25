package club_website.auth.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Event;
import club_website.auth.Models.MemberEvent;

@Repository
public interface MemberEventRepo extends JpaRepository<MemberEvent, Integer>{
	public List<MemberEvent> findByEvent(Event e);
}

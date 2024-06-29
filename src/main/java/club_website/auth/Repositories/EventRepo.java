package club_website.auth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Event;


@Repository
public interface EventRepo extends JpaRepository<Event, Integer>{

}

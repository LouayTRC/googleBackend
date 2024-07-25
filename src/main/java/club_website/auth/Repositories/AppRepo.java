package club_website.auth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Application;

@Repository
public interface AppRepo extends JpaRepository<Application, Integer>{

}

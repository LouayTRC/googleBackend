package club_website.auth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import club_website.auth.Models.Admin;

public interface AdminRepo extends JpaRepository<Admin,Integer>{

}

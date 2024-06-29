package club_website.auth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import club_website.auth.Models.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{

}

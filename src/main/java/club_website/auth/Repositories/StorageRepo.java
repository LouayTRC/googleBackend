package club_website.auth.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.PictureData;
@Repository
public interface StorageRepo extends JpaRepository<PictureData, Long>{
	
	public Optional<PictureData> findByName(String name);
}

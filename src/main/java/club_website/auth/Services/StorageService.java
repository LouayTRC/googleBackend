package club_website.auth.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import club_website.auth.Models.PictureData;

@Service
public interface StorageService {
	
	public String uploadImage(MultipartFile file);
    public PictureData getImage(String name);
    public String deleteImage(String name);
}

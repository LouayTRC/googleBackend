package club_website.auth.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import club_website.auth.Models.PictureData;
import club_website.auth.Repositories.StorageRepo;
import club_website.auth.Services.StorageService;
import club_website.auth.Utils.PictureUtils;

import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    
    @Autowired
    private StorageRepo storageRepo;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            PictureData pic = storageRepo.save(
                PictureData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .data(PictureUtils.compressImage(file.getBytes()))
                    .build()
            );
            if (pic != null) {
                return "Picture successfully saved: " + file.getOriginalFilename();
            } else {
                return "Error saving picture";
            }
        } catch (Exception e) {
            return "Error saving picture: " + e.getMessage();
        }
    }

    @Override
    public PictureData getImage(String name) {
        Optional<PictureData> pic = storageRepo.findByName(name);
        return pic.orElse(null);
    }

    @Override
    public String deleteImage(String name) {
        try {
            Optional<PictureData> pic = storageRepo.findByName(name);
            if (pic.isPresent()) {
                storageRepo.delete(pic.get());
                return "Picture successfully deleted: " + name;
            } else {
                return "Picture not found: " + name;
            }
        } catch (Exception e) {
            return "Error deleting picture: " + e.getMessage();
        }
    }
}

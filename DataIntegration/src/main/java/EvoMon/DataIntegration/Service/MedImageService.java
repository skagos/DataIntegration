package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Model.MedImage;
import EvoMon.DataIntegration.Repository.MedImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedImageService {
    @Autowired
    MedImageRepository medImageRepository;
    public MedImage getMedImageById(Long id) {
        return medImageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("MedImage not found"));
    }
}

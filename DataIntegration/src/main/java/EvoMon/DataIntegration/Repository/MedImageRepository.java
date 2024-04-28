package EvoMon.DataIntegration.Repository;


import EvoMon.DataIntegration.Model.MedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MedImageRepository extends JpaRepository<MedImage, Long> {
}

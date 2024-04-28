package EvoMon.DataIntegration.Repository;

import EvoMon.DataIntegration.Model.MedImageSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedImageSerieRepository extends JpaRepository<MedImageSerie, Long> {
}

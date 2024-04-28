package EvoMon.DataIntegration.Repository;

import EvoMon.DataIntegration.Model.ModalityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalityTypeRepository extends JpaRepository<ModalityType, Integer> {

    ModalityType findByName(String name);

}




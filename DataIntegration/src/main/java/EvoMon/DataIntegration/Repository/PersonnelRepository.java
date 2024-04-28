package EvoMon.DataIntegration.Repository;

import EvoMon.DataIntegration.Model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    boolean existsByFullName(String fullName);

    boolean existsByFullNameAndSpecialty(String fullName,String Specialty);

}

package EvoMon.DataIntegration.Repository;

import EvoMon.DataIntegration.Model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExamRepository extends JpaRepository<Exam, Long> {



}

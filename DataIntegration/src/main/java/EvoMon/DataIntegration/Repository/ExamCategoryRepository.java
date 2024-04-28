package EvoMon.DataIntegration.Repository;

import EvoMon.DataIntegration.Model.ExamCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


    @Repository
    public interface ExamCategoryRepository extends JpaRepository<ExamCategory, Integer> {

        boolean existsByCode(String code);

        @Query("SELECT ec.fileHistory.name FROM ExamCategory ec WHERE ec.code = :code")
        String findFileHistoryByCode(String code);
    }




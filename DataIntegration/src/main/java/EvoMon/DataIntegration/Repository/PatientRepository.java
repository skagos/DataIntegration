package EvoMon.DataIntegration.Repository;


import EvoMon.DataIntegration.Model.MedImage;
import EvoMon.DataIntegration.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    @Query(value = "SELECT mi FROM Exam e INNER JOIN MedImageSerie  ms ON e.id = ms.exam.id " +
            "INNER JOIN  MedImage mi ON ms.id = mi.medImageSerie.id WHERE e.patient.id = :id")
    List<MedImage> getAllByCustom(@Param("id") Long id);

//    @Query(value = "SELECT p FROM Patient p INNER JOIN Exam e ON p.id = e.patient.id INNER JOIN MedImageSerie  ms ON e.id = ms.examinationRef " +
//            "INNER JOIN  MedImage mi ON ms.id = mi.medImageSerie.id WHERE p.id = :id")
//    Patient getAllByCustom(@Param("id") Long id);




}

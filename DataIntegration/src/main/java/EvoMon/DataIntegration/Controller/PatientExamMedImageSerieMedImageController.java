package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Service.PatientExamMedImageSerieMedImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiii")
public class PatientExamMedImageSerieMedImageController {

    @Autowired
    private PatientExamMedImageSerieMedImageService patientExamMedImageSerieMedImageService;

    @GetMapping("/create-apo-ola")
    public ResponseEntity<String> createPatientsAndExams() {
        try {
            patientExamMedImageSerieMedImageService.generatePatientsAndExamsAndMedImageSeriesAndMedImages();
            return ResponseEntity.ok("Patients and exams created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create patients and exams.");
        }
    }
}

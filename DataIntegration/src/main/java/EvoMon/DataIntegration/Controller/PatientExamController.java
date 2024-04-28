package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Service.PatientExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PatientExamController {

    private final PatientExamService patientExamService;

    @Autowired
    public PatientExamController(PatientExamService patientExamService) {
        this.patientExamService = patientExamService;
    }


    @GetMapping("/create-patients-and-exams")
    public ResponseEntity<String> createPatientsAndExams() {
        try {
            patientExamService.generatePatientsAndExams();
            return ResponseEntity.ok("Patients and exams created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create patients and exams.");
        }
    }



}
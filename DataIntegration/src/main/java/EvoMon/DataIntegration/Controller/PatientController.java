package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Model.MedImage;
import EvoMon.DataIntegration.Model.Patient;
import EvoMon.DataIntegration.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/insert-million")
    public String insertOneMillionRecords() {
        patientService.insertOneMillionRecords();
        return "One million records inserted successfully!";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        long startTime = System.nanoTime();

        Patient patient = patientService.getPatientById(id);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        if(patient != null) {
            return ResponseEntity.ok()
                    .header("Execution-Time-Nanos", Long.toString(executionTime)) // Execution time in response header
                    .body(patient);
        } else {
            return ResponseEntity.notFound()
                    .header("Execution-Time-Nanos", Long.toString(executionTime))
                    .build();
        }
    }

    @GetMapping("/custom/{id}")
    public ResponseEntity<List<MedImage>> getAllByCustom(@PathVariable Long id) {
        long startTime = System.nanoTime();

        List<MedImage> medImages = patientService.getAllByCustom(id);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        if(medImages != null) {
            return ResponseEntity.ok()
                    .header("Execution-Time-Nanos", Long.toString(executionTime)) // Execution time in response header
                    .body(medImages);
        } else {
            return ResponseEntity.notFound()
                    .header("Execution-Time-Nanos", Long.toString(executionTime))
                    .build();
        }
    }

}

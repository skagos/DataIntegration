package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Service.MedImagesAndSeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apii")
public class MedImageAndSerieController {

    private final MedImagesAndSeriesService medImagesAndSeriesService;


    public MedImageAndSerieController(MedImagesAndSeriesService medImagesAndSeriesService) {
        this.medImagesAndSeriesService = medImagesAndSeriesService;
    }


    @GetMapping("/create-medImage-and-series")
    public ResponseEntity<String> createPatientsAndExams() {
        try {
            medImagesAndSeriesService.generateMedImagesAndSeries();
            return ResponseEntity.ok("Patients and exams created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create patients and exams.");
        }
    }
}

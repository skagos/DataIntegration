package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Model.MedImage;
import EvoMon.DataIntegration.Service.MedImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medImage")
public class MedImageController {

    @Autowired
    MedImageService medImageService;

    @GetMapping("/{id}")
    public ResponseEntity<MedImage> getPatientById(@PathVariable Long id) {
        long startTime = System.currentTimeMillis();

        MedImage medImage = medImageService.getMedImageById(id);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        if(medImage != null) {
            return ResponseEntity.ok()
                    .header("Execution-Time", Long.toString(executionTime)) // Execution time in response header
                    .body(medImage);
        } else {
            return ResponseEntity.notFound()
                    .header("Execution-Time", Long.toString(executionTime))
                    .build();
        }
    }
}

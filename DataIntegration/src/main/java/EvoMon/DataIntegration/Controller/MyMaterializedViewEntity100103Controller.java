package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Model.MyMaterializedViewEntity100103;
import EvoMon.DataIntegration.Service.MyMaterializedViewEntity100103Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("patient/materializedView")
public class MyMaterializedViewEntity100103Controller {

    @Autowired
    private MyMaterializedViewEntity100103Service myMaterializedViewEntity100103Service;

    @GetMapping("/100103")
    public ResponseEntity<List<MyMaterializedViewEntity100103>> getMyMaterializedViewEntity100103ById() {
        long startTime = System.currentTimeMillis();

        List<MyMaterializedViewEntity100103> myMaterializedViewEntity100103 = myMaterializedViewEntity100103Service.getAllByCustomMaterial();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        if(myMaterializedViewEntity100103 != null) {
            return ResponseEntity.ok()
                    .header("Execution-Time", Long.toString(executionTime)) // Execution time in response header
                    .body(myMaterializedViewEntity100103);
        } else {
            return ResponseEntity.notFound()
                    .header("Execution-Time", Long.toString(executionTime))
                    .build();
        }
    }
}

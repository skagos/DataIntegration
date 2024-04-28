package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exams")
public class ExamController {


    @Autowired
    private ExamService examService;


    @PostMapping("/insert-millions")
    public String insertOneMillionRecords() {
        examService.insertFiveMillionRecords();
        return "One million records inserted successfully!";
    }
}

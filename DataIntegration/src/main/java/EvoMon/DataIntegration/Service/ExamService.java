package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Model.Exam;
import EvoMon.DataIntegration.Model.Patient;
import EvoMon.DataIntegration.Repository.ExamRepository;
import EvoMon.DataIntegration.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class ExamService {

//    private final ExamRepository examRepository;

    @Autowired
    public ExamRepository examRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void insertFiveMillionRecords() {
        List<Exam> exams = generatePatients(1000000);
        examRepository.saveAll(exams);
    }

    private List<Exam> generatePatients(int count) {
        List<Exam> exams = new ArrayList<>();
        long j = 10000;
        for (int i = 0; i < count; i++) {
            Random random = new Random();

            Exam exam = new Exam();
            // Set all fields with dummy data
            exam.setInstitutionName("Hospital " + random.nextInt(10));
            exam.setPerformedLocation("Location " + random.nextInt(5));
            exam.setExamDuration((long) random.nextInt(60));
            Long patientId = j;
            Patient patient = patientRepository.findById(patientId).orElse(null);

            exam.setPatient(patient);

            if (i % 5 == 0) {
                j++;
            }
            exams.add(exam);
        }
        return exams;
    }
}

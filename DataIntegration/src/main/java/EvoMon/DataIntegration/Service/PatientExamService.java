package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Model.Exam;
import EvoMon.DataIntegration.Model.Patient;
import EvoMon.DataIntegration.Repository.ExamRepository;
import EvoMon.DataIntegration.Repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional
public class PatientExamService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ExamRepository examRepository;

    private final int BATCH_SIZE = 1000;

    public void generatePatientsAndExams() {
        List<Patient> patients = generatePatients(1_000_000);
        List<Exam> exams = generateExams(patients, 10);
        ExecutorService patientExecutor = Executors.newFixedThreadPool(8); // Assuming 8 cores

        // Batch insert patients
        for (int i = 0; i < patients.size(); i += BATCH_SIZE) {
            List<Patient> batch = patients.subList(i, Math.min(i + BATCH_SIZE, patients.size()));
            patientExecutor.execute(() -> patientRepository.saveAll(batch));
        }
        patientExecutor.shutdown();

        // Batch insert exams
        ExecutorService examExecutor = Executors.newFixedThreadPool(8); // Assuming 8 cores
        for (int i = 0; i < exams.size(); i += BATCH_SIZE) {
            List<Exam> batch = exams.subList(i, Math.min(i + BATCH_SIZE, exams.size()));
            examExecutor.execute(() -> examRepository.saveAll(batch));
        }
        examExecutor.shutdown();
    }

    private List<Patient> generatePatients(int count) {
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Patient patient = new Patient();
            // Set random patient data
            // Example:
            patient.setFirstName("Patient" + i);
            // Add patient to list
            patients.add(patient);
        }
        return patients;
    }

    private List<Exam> generateExams(List<Patient> patients, int examsPerPatient) {
        List<Exam> exams = new ArrayList<>();
        for (Patient patient : patients) {
            for (int i = 0; i < examsPerPatient; i++) {
                Exam exam = new Exam();
                // Set random exam data
                // Example:
                exam.setExamId("Exam" + i);
                // Set patient for exam
                exam.setPatient(patient);
                // Add exam to list
                exams.add(exam);
            }
        }
        return exams;
    }
}

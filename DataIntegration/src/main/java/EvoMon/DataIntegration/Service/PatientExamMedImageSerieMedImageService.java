package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Model.Exam;
import EvoMon.DataIntegration.Model.MedImage;
import EvoMon.DataIntegration.Model.MedImageSerie;
import EvoMon.DataIntegration.Model.Patient;
import EvoMon.DataIntegration.Repository.ExamRepository;
import EvoMon.DataIntegration.Repository.MedImageRepository;
import EvoMon.DataIntegration.Repository.MedImageSerieRepository;
import EvoMon.DataIntegration.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class PatientExamMedImageSerieMedImageService {


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private MedImageSerieRepository medImageSerieRepository;

    @Autowired
    private MedImageRepository medImageRepository;

    private final int BATCH_SIZE = 10;

    public void generatePatientsAndExamsAndMedImageSeriesAndMedImages() {
        List<Patient> patients = generatePatients(10);
        List<Exam> exams = generateExams(patients, 2);
        List<MedImageSerie> medImageSeries = generateMedImageSeries(exams, 2);
        List<MedImage> medImages = generateMedImages(medImageSeries, 500);


        ExecutorService patientExecutor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < patients.size(); i += BATCH_SIZE) {
            List<Patient> batch = patients.subList(i, Math.min(i + BATCH_SIZE, patients.size()));
            patientExecutor.execute(() -> patientRepository.saveAll(batch));
        }
        patientExecutor.shutdown();


        try {
            patientExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // Handle interruption
            e.printStackTrace();
        }
        //STOP

        ExecutorService examExecutor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < exams.size(); i += BATCH_SIZE) {
            List<Exam> batch = exams.subList(i, Math.min(i + BATCH_SIZE, exams.size()));
            examExecutor.execute(() -> examRepository.saveAll(batch));
        }
        examExecutor.shutdown();


        try {
            examExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // Handle interruption
            e.printStackTrace();
        }
        //STOP

        ExecutorService medImageSerieExecutor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < medImageSeries.size(); i += BATCH_SIZE) {
            List<MedImageSerie> batch = medImageSeries.subList(i, Math.min(i + BATCH_SIZE, medImageSeries.size()));
            medImageSerieExecutor.execute(() -> medImageSerieRepository.saveAll(batch));
        }
        medImageSerieExecutor.shutdown();


        try {
            medImageSerieExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // Handle interruption
            e.printStackTrace();
        }
        //STOP

        ExecutorService medImageExecutor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < medImages.size(); i += BATCH_SIZE) {
            List<MedImage> batch = medImages.subList(i, Math.min(i + BATCH_SIZE, medImages.size()));
            medImageExecutor.execute(() -> medImageRepository.saveAll(batch));
        }
        medImageExecutor.shutdown();
    }

    private List<Patient> generatePatients(int count) {
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Patient patient = new Patient();
            patient.setFirstName("Patient" + i);
            patients.add(patient);
        }
        return patients;
    }

    private List<Exam> generateExams(List<Patient> patients, int examsPerPatient) {
        List<Exam> exams = new ArrayList<>();
        for (Patient patient : patients) {
            for (int i = 0; i < examsPerPatient; i++) {
                Exam exam = new Exam();
                exam.setExamId("Exam" + i);
                exam.setPatient(patient);
                exams.add(exam);
            }
        }
        return exams;
    }

    private List<MedImageSerie> generateMedImageSeries(List<Exam> exams, int medImageSeriesPerExam) {
        List<MedImageSerie> medImageSeries = new ArrayList<>();
        for (Exam exam : exams) {
            for (int i = 0; i < medImageSeriesPerExam; i++) {
                MedImageSerie medImageSerie = new MedImageSerie();
                medImageSerie.setDescription("Test");
                medImageSerie.setExam(exam);
                medImageSeries.add(medImageSerie);
            }
        }
        return medImageSeries;
    }

    private List<MedImage> generateMedImages(List<MedImageSerie> medImageSeries, int medImagesPerSerie) {
        List<MedImage> medImages = new ArrayList<>();
        for (MedImageSerie medImageSerie : medImageSeries) {
            for (int i = 0; i < medImagesPerSerie; i++) {
                MedImage medImage = new MedImage();
                medImage.setMedImageSerie(medImageSerie);
                medImages.add(medImage);
            }
        }
        return medImages;
    }



}

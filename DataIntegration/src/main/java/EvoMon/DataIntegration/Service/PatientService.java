package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Model.MedImage;
import EvoMon.DataIntegration.Model.Patient;
import EvoMon.DataIntegration.Repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void insertOneMillionRecords() {
        List<Patient> patients = generatePatients(1000000);
        patientRepository.saveAll(patients);
    }

    private List<Patient> generatePatients(int count) {
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Patient patient = new Patient();
            // Set all fields with dummy data
            patient.setFirstName("FirstName" + i);
            patient.setFatherName("FatherName" + i);
            patient.setMotherName("MotherName" + i);
            patient.setLastName("LastName" + i);
            patient.setFullName("FullName" + i);
            // Set other fields similarly...
            patient.setBirthdate(new Date()); // Example date, you can adjust as needed
            patients.add(patient);
        }
        return patients;
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }

    public List<MedImage> getAllByCustom(Long id) {
        return  patientRepository.getAllByCustom(id);
    }
}

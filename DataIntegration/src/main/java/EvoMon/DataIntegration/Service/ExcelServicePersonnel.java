package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Helper.ExcelHelperPersonnel;
import EvoMon.DataIntegration.Model.FileHistory;
import EvoMon.DataIntegration.Model.Personnel;
import EvoMon.DataIntegration.Repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelServicePersonnel {
    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    ExcelHelperPersonnel excelHelperPersonnel;

    public void savePersonnel(MultipartFile file, String name, FileHistory fileHistory) {

        try {
            List<Personnel> personnels = excelHelperPersonnel.excelToPersonnel(file.getInputStream(), name,fileHistory);
            for (Personnel personnel : personnels) {
                try {
                        personnelRepository.save(personnel);
                }
                catch (Exception e) {
                    System.err.println("Failed to save ExamCategory: " + personnel.getId() + ". Error: " + e.getMessage());
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read the Excel file: " + e.getMessage());
        }
    }

}

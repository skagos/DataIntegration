package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Helper.ExcelHelperExamCategory;
import EvoMon.DataIntegration.Model.ExamCategory;
import EvoMon.DataIntegration.Model.FileHistory;
import EvoMon.DataIntegration.Repository.ExamCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


    @Service
    public class ExcelServiceExamCategory {
        @Autowired
        ExamCategoryRepository examCategoryRepository;

        @Autowired
        ExcelHelperExamCategory excelHelperExamCategory;

        public void save(MultipartFile file, String name, FileHistory fileHistory) {

             try {
                 List<ExamCategory> examCategories = excelHelperExamCategory.excelToExamCategories(file.getInputStream(), name,fileHistory);
                 for (ExamCategory examCategory : examCategories) {
                     try {
                         examCategoryRepository.save(examCategory);
                        }
                     catch (Exception e) {
                         System.err.println("Failed to save ExamCategory: " + examCategory.getId() + ". Error: " + e.getMessage());
                     }
                 }
             }
             catch (IOException e) {
                 throw new RuntimeException("Could not read the Excel file: " + e.getMessage());
             }
        }
        public List<ExamCategory> getAllExamCategories() {
            return examCategoryRepository.findAll();
        }
    }


package EvoMon.DataIntegration.Controller;

import EvoMon.DataIntegration.Exception.EmptyCellException;
import EvoMon.DataIntegration.Exception.ExamCategoryExistsException;
import EvoMon.DataIntegration.Exception.PersonnelExistsException;
import EvoMon.DataIntegration.Exception.WrongExcelFormException;
import EvoMon.DataIntegration.Helper.ExcelHelperExamCategory;
import EvoMon.DataIntegration.Message.ResponseMessage;
import EvoMon.DataIntegration.Model.FileHistory;
import EvoMon.DataIntegration.Repository.FileHistoryRepository;
import EvoMon.DataIntegration.Service.ExcelServiceExamCategory;
import EvoMon.DataIntegration.Service.ExcelServicePersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

    public String name;
    boolean emptyFlag = false;

    @Autowired
    ExcelServiceExamCategory excelServiceExamCategory;
    @Autowired
    ExcelServicePersonnel excelServicePersonnel;

    @Autowired
    FileHistoryRepository fileHistoryRepository;

    private FileHistory createAndSaveFileHistory(MultipartFile file) {

        name = file.getOriginalFilename();
        String existFileCheck = fileHistoryRepository.findNameWithDataTrue(name);

        if (existFileCheck != null) {
            throw new ExamCategoryExistsException("File with the name '" + name + "' already exists in the database.");
        }

        FileHistory fileHistory = new FileHistory();
        fileHistory.setName(name);
        fileHistory.setData(true);
        fileHistoryRepository.save(fileHistory);

        return fileHistory;
    }

    @PostMapping("/upload/Personnel")
    public ResponseEntity<ResponseMessage> uploadFilePersonnel(@RequestParam("file") MultipartFile file) {
        String message = "message";

        if (ExcelHelperExamCategory.hasExcelFormat(file)) {
            try {
                FileHistory fileHistory = createAndSaveFileHistory(file);
                excelServicePersonnel.savePersonnel(file,name,fileHistory);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }catch (WrongExcelFormException e) {
                emptyFlag=true;
                if(emptyFlag) {
                    fileHistoryRepository.updateDataFieldToFalseByName(name);
                }emptyFlag=false;

                message = "WrongExcelFormException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }catch (PersonnelExistsException e) {
                emptyFlag=true;
                if(emptyFlag) {
                    fileHistoryRepository.updateDataFieldToFalseByName(name);
                }emptyFlag=false;

                message = "PersonnelExistsException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }catch (EmptyCellException e) {
                emptyFlag=true;
                if(emptyFlag) {
                    fileHistoryRepository.updateDataFieldToFalseByName(name);
                }emptyFlag=false;
                message = "EmptyCellException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }catch (RuntimeException e) {
                message = "RuntimeException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping("/upload/Exams")
    public ResponseEntity<ResponseMessage> uploadFileExams(@RequestParam("file") MultipartFile file) {
        String message = "message";

        if (ExcelHelperExamCategory.hasExcelFormat(file)) {
            try {

                FileHistory fileHistory = createAndSaveFileHistory(file);
                excelServiceExamCategory.save(file,name,fileHistory);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }catch (WrongExcelFormException e) {
                emptyFlag=true;
                if(emptyFlag) {
                    fileHistoryRepository.updateDataFieldToFalseByName(name);
                }emptyFlag=false;

                message = "WrongExcelFormException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }catch (ExamCategoryExistsException e) {
                emptyFlag=true;
                if(emptyFlag) {
                    fileHistoryRepository.updateDataFieldToFalseByName(name);
                }emptyFlag=false;

                message = "ExamCategoryExistsException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            } catch (EmptyCellException e) {
                emptyFlag=true;
                if(emptyFlag) {
                    fileHistoryRepository.updateDataFieldToFalseByName(name);
                }emptyFlag=false;
                message = "EmptyCellException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            } catch (RuntimeException e) {
                emptyFlag=true;
                if(emptyFlag) {
                    fileHistoryRepository.updateDataFieldToFalseByName(name);
                }emptyFlag=false;
                message = "RuntimeException: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}

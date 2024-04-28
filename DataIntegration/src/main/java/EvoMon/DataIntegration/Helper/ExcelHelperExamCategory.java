package EvoMon.DataIntegration.Helper;

import EvoMon.DataIntegration.Exception.EmptyCellException;
import EvoMon.DataIntegration.Exception.ExamCategoryExistsException;
import EvoMon.DataIntegration.Exception.ExcelHelperExamCategoryException;
import EvoMon.DataIntegration.Exception.WrongExcelFormException;
import EvoMon.DataIntegration.Model.ExamCategory;
import EvoMon.DataIntegration.Model.FileHistory;
import EvoMon.DataIntegration.Model.ModalityType;
import EvoMon.DataIntegration.Repository.ExamCategoryRepository;
import EvoMon.DataIntegration.Repository.FileHistoryRepository;
import EvoMon.DataIntegration.Repository.ModalityTypeRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Service
public class ExcelHelperExamCategory {
        public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        static String[] HEADERs = { "Code", "Name", "Modality Type" };
    private String name;

    private boolean flagWrongExcel2 = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Autowired
    private ExamCategoryRepository examCategoryRepository;

    @Autowired
    private ModalityTypeRepository modalityTypeRepository;

    @Autowired
    private FileHistoryRepository fileHistoryRepository;


        public static boolean hasExcelFormat(MultipartFile file) {

            if (!TYPE.equals(file.getContentType())) {
                return false;
            }
            return true;
        }


    public  List<String> getHeaderValues(Row headerRow) {
        List<String> expectedHeaders = Arrays.asList("Code", "Name", "Modality Type");
        List<String> headers = new ArrayList<>();
        Iterator<Cell> headerCells = headerRow.cellIterator();

        while (headerCells.hasNext()) {
            Cell currentCell = headerCells.next();
            headers.add(currentCell.getStringCellValue());
        }
        if (headers.containsAll(expectedHeaders)) {
            flagWrongExcel2 = false;
        }
        else {
            String message = "Wrong Excel Form (Wrong headers check the Excel Template if its right check your request address)!";
            System.out.println(message);
            flagWrongExcel2=true;
        }
        return headers;
    }

        public  List<ExamCategory> excelToExamCategories(InputStream is, String name,FileHistory fileHistory) {
            try {
                Workbook workbook = new XSSFWorkbook(is);

                DataFormatter dataFormatter = new DataFormatter();

                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rows = sheet.iterator();

                List<ExamCategory> examCategories = new ArrayList<ExamCategory>();
                List<String> headers = new ArrayList<>();

                boolean nullLineFlag = false;


                if (rows.hasNext()) {
                    rows.next();
                }

                System.out.println(name);

                    Row headerRow = sheet.getRow(0);
                    headers = getHeaderValues(headerRow);


                int currentRowNum = 1;
                int emptyLines = 0;

                while (rows.hasNext() && emptyLines ==0) {
                    Row currentRow = rows.next();
                    ExamCategory examCategory = new ExamCategory();
                    int errorLine = currentRowNum + 1;
                    nullLineFlag = false;

                    for (int cellIdx = 0; cellIdx < headers.size(); cellIdx++) {
                        Cell currentCell = currentRow.getCell(cellIdx);
                        String header = headers.get(cellIdx);
                            switch (header) {


                                case "Code":

                                    String emptyCodeCheck = null;
                                    if(currentCell.getCellType() == CellType.STRING){
                                         emptyCodeCheck = currentCell.getStringCellValue();
                                    }
                                    if(currentCell.getCellType() == CellType.NUMERIC){
                                        emptyCodeCheck = dataFormatter.formatCellValue(currentCell);
                                    }
                                    if (currentCell == null ||  emptyCodeCheck == null || emptyCodeCheck.trim().isEmpty()) {
                                        nullLineFlag = true;
                                    }
                                    if(currentCell.getCellType() == CellType.STRING && nullLineFlag==false) {
                                        examCategory.setCode(currentCell.getStringCellValue());
                                    }
                                    else if(currentCell.getCellType() == CellType.NUMERIC) {
                                        String codeValue = dataFormatter.formatCellValue(currentCell);
                                        examCategory.setCode(codeValue);
                                    }
                                    break;


                                case "Name":
                                    String emptyNameCheck = null;
                                    if(currentCell != null && currentCell.getCellType() == CellType.STRING){
                                        emptyNameCheck = currentCell.getStringCellValue();
                                    }
                                    else {
                                        emptyNameCheck = null;
                                    }
                                    if (emptyNameCheck == null ||  emptyNameCheck.trim().isEmpty()) {
                                        nullLineFlag = true;
                                    }
                                    else {
                                        examCategory.setDescription(currentCell.getStringCellValue());
                                        examCategory.setName(currentCell.getStringCellValue());
                                        examCategory.setFileHistory(fileHistory);
                                    }
                                    break;

                                case "Modality Type":
                                    String emptyModalityTypeCheck =currentCell.getStringCellValue();

                                    if (currentCell == null || currentCell.getCellType() == CellType.BLANK || emptyModalityTypeCheck.trim().isEmpty()) {
                                        nullLineFlag = true;
                                    }else {
                                        String modalityTypeName = currentCell.getStringCellValue();

                                            ModalityType modalityType = modalityTypeRepository.findByName(modalityTypeName);
                                            if(modalityType == null)
                                            {
                                                String message = "Wrong cell in line:" + errorLine + " Please correct the " + name + " file and re-upload" ;
                                                throw new EmptyCellException(message);
                                            }

                                       examCategory.setModalityType(modalityType);
                                    }
                                    break;

                                default:
                                    break;
                            }

                    }

                    boolean examCategoryExists = examCategoryRepository.existsByCode(examCategory.getCode());
                    if (examCategoryExists) {
                        String nameOfFileThatExist = null;
                        nameOfFileThatExist = examCategoryRepository.findFileHistoryByCode(examCategory.getCode());
                        String message = "Exam Category already exists in the database!"+
                                "At line " + errorLine +  " of your excel (for file: "+ nameOfFileThatExist +")! " +
                                "Please correct the "+ name +" file and re-upload";
                        System.out.println(message);
                        throw new ExamCategoryExistsException(message);
                    }

                    currentRowNum++;

                    boolean hasNullValues =( (examCategory.getCode() == null || examCategory.getCode().isEmpty()) && (examCategory.getName() == null || examCategory.getName().isEmpty()) && (examCategory.getModalityType() == null ) );
                    if (hasNullValues) {
                        emptyLines++;
                    }


                    if(nullLineFlag && emptyLines ==0 ) {
                        String message = "Empty cell in line:" + errorLine + " Please correct the " + name + " file and re-upload" ;
                        System.out.println(message);
                        throw new EmptyCellException(message);

                    }
                    if(flagWrongExcel2){
                        String message = "Wrong Excel Form (Wrong headers check the Excel Template if its right check your request address)!";
                        flagWrongExcel2=false;
                        throw new WrongExcelFormException(message);

                    }

                    if(!hasNullValues){
                        examCategories.add(examCategory);
                    }
                    else break;

                }

                workbook.close();

                return examCategories;
            } catch (IOException e) {
                throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
            }
            catch (ExcelHelperExamCategoryException e) {
                throw new RuntimeException("Error occurred in Service2: " + e.getMessage());
            }
        }
    }


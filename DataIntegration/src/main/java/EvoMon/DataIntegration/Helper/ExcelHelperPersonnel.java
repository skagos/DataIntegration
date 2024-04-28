package EvoMon.DataIntegration.Helper;


import EvoMon.DataIntegration.Exception.*;
import EvoMon.DataIntegration.Model.FileHistory;
import EvoMon.DataIntegration.Model.Personnel;
import EvoMon.DataIntegration.Repository.FileHistoryRepository;
import EvoMon.DataIntegration.Repository.PersonnelRepository;
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
public class ExcelHelperPersonnel {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "FULLNAME", "AMKA", "SPECIALTY","BODYPART_SPECIALTY" };

    private String name;
    private boolean flagWrongExcel=false;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private FileHistoryRepository fileHistoryRepository;

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }


    public  List<String> getHeaderValues(Row headerRow) {
        List<String> expectedHeaders = Arrays.asList("FULLNAME", "AMKA", "SPECIALTY","BODYPART_SPECIALTY" );
        List<String> headers = new ArrayList<>();
        Iterator<Cell> headerCells = headerRow.cellIterator();

        while (headerCells.hasNext()) {
            Cell currentCell = headerCells.next();
            headers.add(currentCell.getStringCellValue());
        }
        if (headers.containsAll(expectedHeaders)) {
            flagWrongExcel=false;
        }
        else {
            String message = "Wrong Excel Form (Wrong headers check the Excel Template if its right check your request address)!";
            System.out.println(message);
            flagWrongExcel=true;
        }
        return headers;
    }

    public  List<Personnel> excelToPersonnel(InputStream is, String name, FileHistory fileHistory) {
        try {
            Workbook workbookPersonnel = new XSSFWorkbook(is);

            DataFormatter dataFormatter = new DataFormatter();

            Sheet sheet = workbookPersonnel.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Personnel> personnels = new ArrayList<Personnel>();
            List<String> headers = new ArrayList<>();

            boolean nullLineFlag = false;


            if (rows.hasNext()) {
                rows.next();
            }

            Row headerRow = sheet.getRow(0);
            headers = getHeaderValues(headerRow);


            int currentRowNum = 1;
            int emptyLines = 0;

            while (rows.hasNext() && emptyLines ==0) {
                Row currentRow = rows.next();
                Personnel personnel = new Personnel();
                int wrongDataTypeFlag = 0;
                int errorLine = currentRowNum + 1;
                nullLineFlag = false;

                for (int cellIdx = 0; cellIdx < headers.size(); cellIdx++) {
                    Cell currentCell = currentRow.getCell(cellIdx);
                    String header = headers.get(cellIdx);
                    switch (header) {


                        case "FULLNAME":
                            String fullname = null;
                           if(currentCell !=null) {
                                if (currentCell.getCellType() == CellType.STRING) {
                                    fullname = currentCell.getStringCellValue();
                                    personnel.setFullName(fullname);
                                    personnel.setFileHistory(fileHistory);
                                } else {
                                    wrongDataTypeFlag = 1;
                                }
                            }else {
                                nullLineFlag = true;
                            }
                            break;


                        case "AMKA":
                                String amka = null;
                                if(currentCell != null) {
                                    if (currentCell.getCellType() == CellType.STRING) {
                                        amka = currentCell.getStringCellValue();
                                        personnel.setAmka(amka);
                                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                                        amka = dataFormatter.formatCellValue(currentCell);
                                        personnel.setAmka(amka);
                                    } else {
                                        amka = null;
                                        personnel.setAmka(amka);
                                    }
                                }
                            else {
                                    personnel.setAmka(null);
                                }
                            break;

                        case "SPECIALTY":
                            String specialty = null;

                            if(currentCell != null) {
                                if (currentCell.getCellType() == CellType.STRING) {
                                    specialty = currentCell.getStringCellValue();
                                    personnel.setSpecialty(specialty);
                                }else {
                                    wrongDataTypeFlag = 1;
                                }
                            }
                            else {
                                nullLineFlag = true;
                            }
                            break;


                        case "BODYPART_SPECIALTY":
                            String bodyPartSpecialty = null;
                            if(currentCell != null) {
                                if (currentCell.getCellType() == CellType.STRING) {
                                    bodyPartSpecialty = currentCell.getStringCellValue();
                                    personnel.setBodypartSpecialty(bodyPartSpecialty);
                                }
                            }
                            else {
                                personnel.setBodypartSpecialty(null);
                            }
                            break;


                        default:
                            break;
                    }

                }

                boolean personnelExists = personnelRepository.existsByFullName(personnel.getFullName());
                if (personnelExists) {

                    String message = "Personnel already exists in the database!"+
                            "At line " + errorLine +  " of your excel! " +
                            "Please correct the "+ name +" file and re-upload";
                    System.out.println(message);
                    throw new PersonnelExistsException(message);
                }

                if(wrongDataTypeFlag>0) {
                    String message = "Wrong Data Form in line"+errorLine+ "Please check the dataType!";
                    wrongDataTypeFlag=0;
                    throw new PersonnelExistsException(message);
                }

                currentRowNum++;

                boolean hasNullValues =( (personnel.getFullName() == null || personnel.getFullName().isEmpty()) && (personnel.getAmka() == null || personnel.getAmka().isEmpty()) && (personnel.getSpecialty() == null || personnel.getSpecialty().isEmpty()) && (personnel.getBodypartSpecialty() == null || personnel.getBodypartSpecialty().isEmpty()) );
                if (hasNullValues) {
                    emptyLines++;
                }

                if(nullLineFlag && emptyLines ==0 ) {
                    String message = "Empty cell in line:" + errorLine + " Please correct the " + name + " file and re-upload (Add personnel FULLNAME and SPECIALTY at least)" ;
                    System.out.println(message);
                    throw new EmptyCellException(message);
                }

                if(flagWrongExcel){
                    String message = "Wrong Excel Form (Wrong headers check the Excel Template if its right check your request address)!";
                    flagWrongExcel=false;
                    throw new WrongExcelFormException(message);
                }

                if(!hasNullValues){
                    personnels.add(personnel);
                }
                else break;
            }

            workbookPersonnel.close();
            return personnels;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        catch (ExcelHelperExamCategoryException e) {
            throw new RuntimeException("Error occurred in Service2: " + e.getMessage());
        }
    }
}

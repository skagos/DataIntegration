package EvoMon.DataIntegration.Model;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "evo_examinations")
public class Exam {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "jfxuid")
    private Long id;

    @Column(name = "examID")
    private String examId;

    @Column(name = "secondaryExamID")
    private String secondaryExamId;

    @Column(name = "studyInstanceUID")
    private String studyInstanceUID;

    @Column(name = "institutionName")
    private String institutionName;

    @Column(name = "institutionDeptName")
    private String institutionDeptName;

    @Column(name = "performedLocation")
    private String performedLocation;

    @Column(name = "stationName")
    private String stationName;

    @Column(name = "companionName")
    private String companionName;

    @Column(name = "examDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Athens")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date examDate;

    @Column(name = "examTime")
    private Time examTime;

    @Column(name = "examDuration")
    private Long examDuration;

    @Column(name = "scheduledExamDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Athens")
    private Date scheduledExamDate;

    @Column(name = "scheduledExamTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Athens")
    private Date scheduledExamTime;

    @Column(name = "scheduledExamDuration")
    private Long scheduledExamDuration;

    @Column(name = "patientHeight")
    private Long patientHeight;

    @Column(name = "patientWeight")
    private Long patientWeight;

    @Column(name = "isEveningExam")
    private Long isEveningExam;

    @Column(name = "appointmentVerification")
    private Long appointmentVerification;

    @Column(name = "priorityRef")
    private Long priorityRef;

    @Column(name = "requestingDoctorRef")
    private Long requestingDoctorRef;

    @Column(name = "firstDiagnosingDoctorRef")
    private Long firstDiagnosingDoctorRef;

    @Column(name = "diagnosingDoctorRef")
    private Long diagnosingDoctorId;

    @Column(name = "diagDocPresence")
    private Long diagdocpresence;

    @Column(name = "medicalPhysisistRef")
    private Long medicalPhysisistRef;

    @Column(name = "nurseRef")
    private Long nurseRef;

    @Column(name = "medicalPhysisistPresence")
    private Long medicalPhysisistPresence;

    @Column(name = "operatorRef")
    private Long operatorRef;

    @Column(name = "examModalityType")
    private String examModalityType;

    @Column(name = "subCategoryRef")
    private Long subCategoryRef;

    @Column(name = "categoryPerformed")
    private Long categoryPerformed;

    @Column(name = "category1Ref")
    private Long category1ref;

    @Column(name = "subCategory1Ref")
    private Long subcategory1ref;

    @Column(name = "category1Performed")
    private Long category1performed;

//    private Long subcategory2ref;
//    private Long category2performed;
//    private Long category3ref;
//    private Long subcategory3ref;
//    private Long category3performed;
//    private Long category4ref;
//    private Long subcategory4ref;
//    private Long category4performed;
//    private Long category5ref;
//    private Long subcategory5ref;
//    private Long category5performed;

    @Column(name = "categorization")
    private String categorization;

    @Column(name = "bodyPartRef")
    private Long bodypartref;

    @Column(name = "organRef")
    private Long organRef;

    @Column(name = "diseaseRef")
    private Long diseaseRef;

    @Column(name = "insuranceRef")
    private Long insuranceRef;

    @Column(name = "comments")
    private String comments;

    @Column(name = "studyDescription")
    private String studyDescription;

    @Column(name = "examPriceUnit_cuid")
    private Long exampriceunit_cuid;

    @Column(name = "examPriceUnit_uid")
    private Long exampriceunit_uid;

    @Column(name = "examPrice")
    private Long examPrice;

    @Column(name = "patientPriceUnit_cuid")
    private Long patientpriceunit_cuid;

    @Column(name = "patientPriceUnit_uid")
    private Long patientpriceunit_uid;

    @Column(name = "patientPrice")
    private Long patientprice;

    @Column(name = "externalStorageLocation")
    private String externalstoragelocation;

    @Column(name = "diagnosisTextRef")
    private Long diagnosisTextRef;

    @Column(name = "diagnosisVoiceRecRef")
    private Long diagnosisvoicerecref;

    @Column(name = "transferedFromRef")
    private Long transferedfromref;

    @Column(name = "projectRef")
    private Long projectRef;

    @Column(name = "dap")
    private Long dap;

    @Column(name = "exposureTime")
    private Long exposureTime;

    @Column(name = "autoForwardRequest")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Athens")
    private Date autoforwardrequest;

    @Column(name = "autoForwardRetries")
    private Long autoforwardretries;

    @Column(name = "autoForwardDone")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Athens")
    private Date autoforwarddone;

    @Column(name = "autoForwardMessage")
    private String autoforwardmessage;

    @Column(name = "syncMissingImages")
    private Long syncmissingimages;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Athens")
    @Column(name = "syncLastCheck")
    private Date synclastcheck;

    @Column(name = "markedForArchive")
    private Long markedForArchive;

    @Column(name = "archivedOn")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Athens")
    private Date archivedOn;

    @Column(name = "archiveAttempts")
    private Long archiveAttempts;

    @Column(name = "hl7ReplySent")
    private Long hl7replysent;

    @Column(name = "hl7MessageRef")
    private Long hl7messageref;

    @Column(name = "patientBmi")
    private Long patientBMI;

    @Column(name = "personDiagnosisInUse")
    private String personDiagnosisInUse;

    @Column(name = "inUse")
    private Long inUse;








    @ManyToOne
    @JoinColumn(name = "modalityTypeRef")
    private ModalityType modalityType;



    @ManyToOne
    @JoinColumn(name = "patientRef")
    @NotNull
    @JsonIgnoreProperties({"evo_examinations", "hibernateLazyInitializer", "handler"})
    private Patient patient;



    ////////////////////////////////////////////////////////////TODO

    @OneToMany(mappedBy = "exam")
//    @OrderBy("imageNumber ASC")
    @JsonIgnoreProperties({"evo_examination", "hibernateLazyInitializer", "handler","exam"})
    private List<MedImageSerie> medImageSeries;


    ////////////////////////////////////////////////////////////TODO


    @ManyToOne
    @JoinColumn(name = "categoryRef")
    private ExamCategory examCategory;


////
//    @ManyToOne
//    @JoinColumn(name = "MEDICALPHYSISISTREF", nullable = false, insertable = false, updatable = false)
//    @NotFound(action = NotFoundAction.IGNORE)
//    @Fetch(FetchMode.JOIN)
//    private MedicalPhysicist medicalPhysicist;


    public List<MedImageSerie> getMedImageSeries() {
        return medImageSeries;
    }



    public void setMedImageSeries(List<MedImageSerie> medImageSeries) {
        this.medImageSeries = medImageSeries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getSecondaryExamId() {
        return secondaryExamId;
    }

    public void setSecondaryExamId(String secondaryExamId) {
        this.secondaryExamId = secondaryExamId;
    }

    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }

    public void setStudyInstanceUID(String studyInstanceUID) {
        this.studyInstanceUID = studyInstanceUID;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionDeptName() {
        return institutionDeptName;
    }

    public void setInstitutionDeptName(String institutionDeptName) {
        this.institutionDeptName = institutionDeptName;
    }

    public String getPerformedLocation() {
        return performedLocation;
    }

    public void setPerformedLocation(String performedLocation) {
        this.performedLocation = performedLocation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCompanionName() {
        return companionName;
    }

    public void setCompanionName(String companionName) {
        this.companionName = companionName;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Time getExamTime() {
        return examTime;
    }

    public void setExamTime(Time examTime) {
        this.examTime = examTime;
    }

    public Long getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(Long examDuration) {
        this.examDuration = examDuration;
    }

    public Date getScheduledExamDate() {
        return scheduledExamDate;
    }

    public void setScheduledExamDate(Date scheduledExamDate) {
        this.scheduledExamDate = scheduledExamDate;
    }

    public Date getScheduledExamTime() {
        return scheduledExamTime;
    }

    public void setScheduledExamTime(Date scheduledExamTime) {
        this.scheduledExamTime = scheduledExamTime;
    }

    public Long getScheduledExamDuration() {
        return scheduledExamDuration;
    }

    public void setScheduledExamDuration(Long scheduledExamDuration) {
        this.scheduledExamDuration = scheduledExamDuration;
    }

    public Long getPatientHeight() {
        return patientHeight;
    }

    public void setPatientHeight(Long patientHeight) {
        this.patientHeight = patientHeight;
    }

    public Long getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(Long patientWeight) {
        this.patientWeight = patientWeight;
    }

    public Long getIsEveningExam() {
        return isEveningExam;
    }

    public void setIsEveningExam(Long isEveningExam) {
        this.isEveningExam = isEveningExam;
    }

    public Long getAppointmentVerification() {
        return appointmentVerification;
    }

    public void setAppointmentVerification(Long appointmentVerification) {
        this.appointmentVerification = appointmentVerification;
    }

    public Long getPriorityRef() {
        return priorityRef;
    }

    public void setPriorityRef(Long priorityRef) {
        this.priorityRef = priorityRef;
    }

    public Long getRequestingDoctorRef() {
        return requestingDoctorRef;
    }

    public void setRequestingDoctorRef(Long requestingDoctorRef) {
        this.requestingDoctorRef = requestingDoctorRef;
    }

    public Long getFirstDiagnosingDoctorRef() {
        return firstDiagnosingDoctorRef;
    }

    public void setFirstDiagnosingDoctorRef(Long firstDiagnosingDoctorRef) {
        this.firstDiagnosingDoctorRef = firstDiagnosingDoctorRef;
    }

    public Long getDiagnosingDoctorId() {
        return diagnosingDoctorId;
    }

    public void setDiagnosingDoctorId(Long diagnosingDoctorId) {
        this.diagnosingDoctorId = diagnosingDoctorId;
    }

    public Long getDiagdocpresence() {
        return diagdocpresence;
    }

    public void setDiagdocpresence(Long diagdocpresence) {
        this.diagdocpresence = diagdocpresence;
    }

    public Long getMedicalPhysisistRef() {
        return medicalPhysisistRef;
    }

    public void setMedicalPhysisistRef(Long medicalPhysisistRef) {
        this.medicalPhysisistRef = medicalPhysisistRef;
    }

    public Long getNurseRef() {
        return nurseRef;
    }

    public void setNurseRef(Long nurseRef) {
        this.nurseRef = nurseRef;
    }

    public Long getMedicalPhysisistPresence() {
        return medicalPhysisistPresence;
    }

    public void setMedicalPhysisistPresence(Long medicalPhysisistPresence) {
        this.medicalPhysisistPresence = medicalPhysisistPresence;
    }

    public Long getOperatorRef() {
        return operatorRef;
    }

    public void setOperatorRef(Long operatorRef) {
        this.operatorRef = operatorRef;
    }

    public String getExamModalityType() {
        return examModalityType;
    }

    public void setExamModalityType(String examModalityType) {
        this.examModalityType = examModalityType;
    }

    public Long getSubCategoryRef() {
        return subCategoryRef;
    }

    public void setSubCategoryRef(Long subCategoryRef) {
        this.subCategoryRef = subCategoryRef;
    }

    public Long getCategoryPerformed() {
        return categoryPerformed;
    }

    public void setCategoryPerformed(Long categoryPerformed) {
        this.categoryPerformed = categoryPerformed;
    }

    public Long getCategory1ref() {
        return category1ref;
    }

    public void setCategory1ref(Long category1ref) {
        this.category1ref = category1ref;
    }

    public Long getSubcategory1ref() {
        return subcategory1ref;
    }

    public void setSubcategory1ref(Long subcategory1ref) {
        this.subcategory1ref = subcategory1ref;
    }

    public Long getCategory1performed() {
        return category1performed;
    }

    public void setCategory1performed(Long category1performed) {
        this.category1performed = category1performed;
    }

//    public Long getCategory2ref() {
//        return category2ref;
//    }
//
//    public void setCategory2ref(Long category2ref) {
//        this.category2ref = category2ref;
//    }
//
//    public Long getSubcategory2ref() {
//        return subcategory2ref;
//    }
//
//    public void setSubcategory2ref(Long subcategory2ref) {
//        this.subcategory2ref = subcategory2ref;
//    }
//
//    public Long getCategory2performed() {
//        return category2performed;
//    }
//
//    public void setCategory2performed(Long category2performed) {
//        this.category2performed = category2performed;
//    }
//
//    public Long getCategory3ref() {
//        return category3ref;
//    }
//
//    public void setCategory3ref(Long category3ref) {
//        this.category3ref = category3ref;
//    }
//
//    public Long getSubcategory3ref() {
//        return subcategory3ref;
//    }
//
//    public void setSubcategory3ref(Long subcategory3ref) {
//        this.subcategory3ref = subcategory3ref;
//    }
//
//    public Long getCategory3performed() {
//        return category3performed;
//    }
//
//    public void setCategory3performed(Long category3performed) {
//        this.category3performed = category3performed;
//    }
//
//    public Long getCategory4ref() {
//        return category4ref;
//    }
//
//    public void setCategory4ref(Long category4ref) {
//        this.category4ref = category4ref;
//    }
//
//    public Long getSubcategory4ref() {
//        return subcategory4ref;
//    }
//
//    public void setSubcategory4ref(Long subcategory4ref) {
//        this.subcategory4ref = subcategory4ref;
//    }
//
//    public Long getCategory4performed() {
//        return category4performed;
//    }
//
//    public void setCategory4performed(Long category4performed) {
//        this.category4performed = category4performed;
//    }
//
//    public Long getCategory5ref() {
//        return category5ref;
//    }
//
//    public void setCategory5ref(Long category5ref) {
//        this.category5ref = category5ref;
//    }
//
//    public Long getSubcategory5ref() {
//        return subcategory5ref;
//    }
//
//    public void setSubcategory5ref(Long subcategory5ref) {
//        this.subcategory5ref = subcategory5ref;
//    }
//
//    public Long getCategory5performed() {
//        return category5performed;
//    }
//
//    public void setCategory5performed(Long category5performed) {
//        this.category5performed = category5performed;
//    }

    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
    }

    public Long getBodypartref() {
        return bodypartref;
    }

    public void setBodypartref(Long bodypartref) {
        this.bodypartref = bodypartref;
    }

    public Long getOrganRef() {
        return organRef;
    }

    public void setOrganRef(Long organRef) {
        this.organRef = organRef;
    }

    public Long getDiseaseRef() {
        return diseaseRef;
    }

    public void setDiseaseRef(Long diseaseRef) {
        this.diseaseRef = diseaseRef;
    }

    public Long getInsuranceRef() {
        return insuranceRef;
    }

    public void setInsuranceRef(Long insuranceRef) {
        this.insuranceRef = insuranceRef;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }

    public Long getExampriceunit_cuid() {
        return exampriceunit_cuid;
    }

    public void setExampriceunit_cuid(Long exampriceunit_cuid) {
        this.exampriceunit_cuid = exampriceunit_cuid;
    }

    public Long getExampriceunit_uid() {
        return exampriceunit_uid;
    }

    public void setExampriceunit_uid(Long exampriceunit_uid) {
        this.exampriceunit_uid = exampriceunit_uid;
    }

    public Long getExamPrice() {
        return examPrice;
    }

    public void setExamPrice(Long examPrice) {
        this.examPrice = examPrice;
    }

    public Long getPatientpriceunit_cuid() {
        return patientpriceunit_cuid;
    }

    public void setPatientpriceunit_cuid(Long patientpriceunit_cuid) {
        this.patientpriceunit_cuid = patientpriceunit_cuid;
    }

    public Long getPatientpriceunit_uid() {
        return patientpriceunit_uid;
    }

    public void setPatientpriceunit_uid(Long patientpriceunit_uid) {
        this.patientpriceunit_uid = patientpriceunit_uid;
    }

    public Long getPatientprice() {
        return patientprice;
    }

    public void setPatientprice(Long patientprice) {
        this.patientprice = patientprice;
    }

    public String getExternalstoragelocation() {
        return externalstoragelocation;
    }

    public void setExternalstoragelocation(String externalstoragelocation) {
        this.externalstoragelocation = externalstoragelocation;
    }

    public Long getDiagnosisTextRef() {
        return diagnosisTextRef;
    }

    public void setDiagnosisTextRef(Long diagnosisTextRef) {
        this.diagnosisTextRef = diagnosisTextRef;
    }

    public Long getDiagnosisvoicerecref() {
        return diagnosisvoicerecref;
    }

    public void setDiagnosisvoicerecref(Long diagnosisvoicerecref) {
        this.diagnosisvoicerecref = diagnosisvoicerecref;
    }

    public Long getTransferedfromref() {
        return transferedfromref;
    }

    public void setTransferedfromref(Long transferedfromref) {
        this.transferedfromref = transferedfromref;
    }

    public Long getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(Long projectRef) {
        this.projectRef = projectRef;
    }

    public Long getDap() {
        return dap;
    }

    public void setDap(Long dap) {
        this.dap = dap;
    }

    public Long getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(Long exposureTime) {
        this.exposureTime = exposureTime;
    }

    public Date getAutoforwardrequest() {
        return autoforwardrequest;
    }

    public void setAutoforwardrequest(Date autoforwardrequest) {
        this.autoforwardrequest = autoforwardrequest;
    }

    public Long getAutoforwardretries() {
        return autoforwardretries;
    }

    public void setAutoforwardretries(Long autoforwardretries) {
        this.autoforwardretries = autoforwardretries;
    }

    public Date getAutoforwarddone() {
        return autoforwarddone;
    }

    public void setAutoforwarddone(Date autoforwarddone) {
        this.autoforwarddone = autoforwarddone;
    }

    public String getAutoforwardmessage() {
        return autoforwardmessage;
    }

    public void setAutoforwardmessage(String autoforwardmessage) {
        this.autoforwardmessage = autoforwardmessage;
    }

    public Long getSyncmissingimages() {
        return syncmissingimages;
    }

    public void setSyncmissingimages(Long syncmissingimages) {
        this.syncmissingimages = syncmissingimages;
    }

    public Date getSynclastcheck() {
        return synclastcheck;
    }

    public void setSynclastcheck(Date synclastcheck) {
        this.synclastcheck = synclastcheck;
    }

    public Long getMarkedForArchive() {
        return markedForArchive;
    }

    public void setMarkedForArchive(Long markedForArchive) {
        this.markedForArchive = markedForArchive;
    }

    public Date getArchivedOn() {
        return archivedOn;
    }

    public void setArchivedOn(Date archivedOn) {
        this.archivedOn = archivedOn;
    }

    public Long getArchiveAttempts() {
        return archiveAttempts;
    }

    public void setArchiveAttempts(Long archiveAttempts) {
        this.archiveAttempts = archiveAttempts;
    }

    public Long getHl7replysent() {
        return hl7replysent;
    }

    public void setHl7replysent(Long hl7replysent) {
        this.hl7replysent = hl7replysent;
    }

    public Long getHl7messageref() {
        return hl7messageref;
    }

    public void setHl7messageref(Long hl7messageref) {
        this.hl7messageref = hl7messageref;
    }

    public Long getPatientBMI() {
        return patientBMI;
    }

    public void setPatientBMI(Long patientBMI) {
        this.patientBMI = patientBMI;
    }

    public String getPersonDiagnosisInUse() {
        return personDiagnosisInUse;
    }

    public void setPersonDiagnosisInUse(String personDiagnosisInUse) {
        this.personDiagnosisInUse = personDiagnosisInUse;
    }

    public Long getInUse() {
        return inUse;
    }

    public void setInUse(Long inUse) {
        this.inUse = inUse;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ModalityType getModalityType() {
        return modalityType;
    }

    public void setModalityType(ModalityType modalityType) {
        this.modalityType = modalityType;
    }




    public ExamCategory getExamCategory() {
        return examCategory;
    }

    public void setExamCategory(ExamCategory examCategory) {
        this.examCategory = examCategory;
    }


}



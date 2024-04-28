package EvoMon.DataIntegration.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "evo_patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "jfxuid")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "fatherName")
    private String fatherName;

    @Column(name = "motherName")
    private String motherName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "isNatural")
    private Long isNatural;

    @Column(name = "identificationTypeRef")
    private Long identificationTypeRef; //////////////////////////////////

    @Column(name = "identitifationNo")
    private String identitifationNo;

    @Column(name = "taxRegistryNo")
    private String taxregistryno;

    @Column(name = "taxOfficeRef")
    private Long taxOfficeRef;

    @Column(name = "mobileTelNo")
    private String mobiletelNo;

    @Column(name = "telephoneNo")
    private String telephoneNo;

    @Column(name = "address")
    private String address;

    @Column(name = "amka")
    private String amka;

    @Column(name = "patientID")
    private String patientId;

    @Column(name = "secondaryPatientID")
    private String secondaryPatientId;

    @Column(name = "firstNameLatin")
    private String firstNameLatin;

    @Column(name = "fatherNameLatin")
    private String fatherNameLatin;

    @Column(name = "lastNameLatin")
    private String lastNameLatin;

    @Column(name = "fullNameLatin")
    private String fullNameLatin;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Athens")
    @Column(name = "birthDate")
    private Date birthdate;

    @Column(name = "age")
    private Long age;

    @Column(name = "height")
    private Long height;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "insuranceRef")
    private Long insuranceRef;

    @Column(name = "insuranceNumber")
    private String insuranceNumber;

    @Column(name = "healthRecordSummary")
    private String healthRecordSummary;

    @Column(name = "finalDiagnosis")
    private String finalDiagnosis;

    @Column(name = "finalDiagnosisIDX")
    private String finalDiagnosisIDX;

    @Column(name = "possibleDiagnosis")
    private String possibleDiagnosis;

    @Column(name = "possibleDiagnosisIDX")
    private String possibleDiagnosisIDX;

    @Column(name = "hasPaceMaker")
    private Long hasPaceMaker;

    @Column(name = "transferedFromRef")
    private Long transferedFromRef;

    @Column(name = "projectRef")
    private Long projectRef;

    @Column(name = "sexRef")
    private Long sexRef;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    @JsonIgnoreProperties(value = {"patient", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private List<Exam> evo_examinations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getIsNatural() {
        return isNatural;
    }

    public void setIsNatural(Long isNatural) {
        this.isNatural = isNatural;
    }

    public Long getIdentificationTypeRef() {
        return identificationTypeRef;
    }

    public void setIdentificationTypeRef(Long identificationTypeRef) {
        this.identificationTypeRef = identificationTypeRef;
    }

    public String getIdentitifationNo() {
        return identitifationNo;
    }

    public void setIdentitifationNo(String identitifationNo) {
        this.identitifationNo = identitifationNo;
    }

    public String getTaxregistryno() {
        return taxregistryno;
    }

    public void setTaxregistryno(String taxregistryno) {
        this.taxregistryno = taxregistryno;
    }

    public Long getTaxOfficeRef() {
        return taxOfficeRef;
    }

    public void setTaxOfficeRef(Long taxOfficeRef) {
        this.taxOfficeRef = taxOfficeRef;
    }

    public String getMobiletelNo() {
        return mobiletelNo;
    }

    public void setMobiletelNo(String mobiletelNo) {
        this.mobiletelNo = mobiletelNo;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getSecondaryPatientId() {
        return secondaryPatientId;
    }

    public void setSecondaryPatientId(String secondaryPatientId) {
        this.secondaryPatientId = secondaryPatientId;
    }

    public String getFirstNameLatin() {
        return firstNameLatin;
    }

    public void setFirstNameLatin(String firstNameLatin) {
        this.firstNameLatin = firstNameLatin;
    }

    public String getFatherNameLatin() {
        return fatherNameLatin;
    }

    public void setFatherNameLatin(String fatherNameLatin) {
        this.fatherNameLatin = fatherNameLatin;
    }

    public String getLastNameLatin() {
        return lastNameLatin;
    }

    public void setLastNameLatin(String lastNameLatin) {
        this.lastNameLatin = lastNameLatin;
    }

    public String getFullNameLatin() {
        return fullNameLatin;
    }

    public void setFullNameLatin(String fullNameLatin) {
        this.fullNameLatin = fullNameLatin;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getInsuranceRef() {
        return insuranceRef;
    }

    public void setInsuranceRef(Long insuranceRef) {
        this.insuranceRef = insuranceRef;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getHealthRecordSummary() {
        return healthRecordSummary;
    }

    public void setHealthRecordSummary(String healthRecordSummary) {
        this.healthRecordSummary = healthRecordSummary;
    }

    public String getFinalDiagnosis() {
        return finalDiagnosis;
    }

    public void setFinalDiagnosis(String finalDiagnosis) {
        this.finalDiagnosis = finalDiagnosis;
    }

    public String getFinalDiagnosisIDX() {
        return finalDiagnosisIDX;
    }

    public void setFinalDiagnosisIDX(String finalDiagnosisIDX) {
        this.finalDiagnosisIDX = finalDiagnosisIDX;
    }

    public String getPossibleDiagnosis() {
        return possibleDiagnosis;
    }

    public void setPossibleDiagnosis(String possibleDiagnosis) {
        this.possibleDiagnosis = possibleDiagnosis;
    }

    public String getPossibleDiagnosisIDX() {
        return possibleDiagnosisIDX;
    }

    public void setPossibleDiagnosisIDX(String possibleDiagnosisIDX) {
        this.possibleDiagnosisIDX = possibleDiagnosisIDX;
    }

    public Long getHasPaceMaker() {
        return hasPaceMaker;
    }

    public void setHasPaceMaker(Long hasPaceMaker) {
        this.hasPaceMaker = hasPaceMaker;
    }

    public Long getTransferedFromRef() {
        return transferedFromRef;
    }

    public void setTransferedFromRef(Long transferedFromRef) {
        this.transferedFromRef = transferedFromRef;
    }

    public Long getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(Long projectRef) {
        this.projectRef = projectRef;
    }

    public Long getSexRef() {
        return sexRef;
    }

    public void setSexRef(Long sexRef) {
        this.sexRef = sexRef;
    }

    public List<Exam> getEvo_examinations() {
        return evo_examinations;
    }

    public void setEvo_examinations(List<Exam> evo_examinations) {
        this.evo_examinations = evo_examinations;
    }

}


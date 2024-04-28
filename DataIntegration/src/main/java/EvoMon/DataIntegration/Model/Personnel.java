package EvoMon.DataIntegration.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "PERSONNEL")
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERSONNEL_FULLNAME")
    private String fullName;

    @Column(name = "AMKA")
    private String amka;

    @Column(name = "SPECIALTY")
    private String specialty;


    @Column(name = "BODYPART_SPECIALTY")
    private String bodypartSpecialty;

    @ManyToOne
    @JoinColumn(name = "FILE_HISTORY_REF")
    private FileHistory fileHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getBodypartSpecialty() {
        return bodypartSpecialty;
    }

    public void setBodypartSpecialty(String bodypartSpecialty) {
        this.bodypartSpecialty = bodypartSpecialty;
    }

    public FileHistory getFileHistory() {
        return fileHistory;
    }

    public void setFileHistory(FileHistory fileHistory) {
        this.fileHistory = fileHistory;
    }
}


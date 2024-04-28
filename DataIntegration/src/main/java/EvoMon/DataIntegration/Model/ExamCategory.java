package EvoMon.DataIntegration.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "EXAM_CATEGORIES")
public class ExamCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "MODALITY_TYPE_REF")
    private ModalityType modalityType;

    @ManyToOne
    @JoinColumn(name = "FILE_HISTORY_REF")
    private FileHistory fileHistory;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModalityType getModalityType() {
        return modalityType;
    }

    public void setModalityType(ModalityType modalityType) {
        this.modalityType = modalityType;
    }

    public FileHistory getFileHistory() {
        return fileHistory;
    }

    public void setFileHistory(FileHistory fileHistory) {
        this.fileHistory = fileHistory;
    }
}

package EvoMon.DataIntegration.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "evo_medimageseries")
public class MedImageSerie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "jfxuid")
    private Long id;

    @Column(name = "examinationRef")
    private Long examinationRef;

    @Column(name = "number")
    private Long number;

    @Column(name = "description")
    private String description;

    @Column(name = "protocolName")
    private String protocolName;

    @Column(name = "hasKeyImages")
    private Integer hasKeyImages;



    ////////////////////////////////////////////////////////////TODO
    @ManyToOne
    @JoinColumn(name = "examRef")
    @NotNull
    @JsonIgnoreProperties({"evo_examinations", "hibernateLazyInitializer", "handler"})
    private Exam exam;
    ////////////////////////////////////////////////////////////TODO


    @OneToMany(mappedBy = "medImageSerie")
    @OrderBy("imageNumber ASC")
    @JsonIgnoreProperties({"medImageSeries"})
    private List<MedImage> imageNames;


    public List<MedImage> getImageNames() {
        return imageNames;
    }

    public void setImageNames(List<MedImage> medImages) {
        this.imageNames = medImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getExaminationRef() {
        return examinationRef;
    }

    public void setExaminationRef(Long examinationRef) {
        this.examinationRef = examinationRef;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public Integer getHasKeyImages() {
        return hasKeyImages;
    }

    public void setHasKeyImages(Integer hasKeyImages) {
        this.hasKeyImages = hasKeyImages;
    }

//    public Exam getExam() {
//        return exam;
//    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}


package EvoMon.DataIntegration.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "evo_medimages")
public class MedImage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "jfxuid")
    private Long id;

    @Column(name = "keyImage")
    private int keyImage;

    @ManyToOne
    @JoinColumn(name = "seriesRef")
    @JsonIgnoreProperties("medImageSeries")
    private MedImageSerie medImageSerie;

    @Column(name = "imageFolderCode")
    private Integer imageFolderCode;

    @Column(name = "imageNumber")
    private Integer imageNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKeyImage() {
        return keyImage;
    }

    public void setKeyImage(int keyImage) {
        this.keyImage = keyImage;
    }

    public Integer getImageFolderCode() {
        return imageFolderCode;
    }

    public void setImageFolderCode(Integer imageFolderCode) {
        this.imageFolderCode = imageFolderCode;
    }

    public Integer getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(Integer imageNumber) {
        this.imageNumber = imageNumber;
    }

//    public MedImageSerie getMedImageSerie() {
//        return medImageSerie;
//    }

    public void setMedImageSerie(MedImageSerie medImageSerie) {
        this.medImageSerie = medImageSerie;
    }
}


package EvoMon.DataIntegration.Model;


import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "my_materialized_view_100103")
public class MyMaterializedViewEntity100103 {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "jfxuid")
    private Long id;

    @Column(name = "keyImage")
    private int keyImage;

    @Column(name = "seriesRef")
    private Long medImageSerie;

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

    public Long getMedImageSerie() {
        return medImageSerie;
    }

    public void setMedImageSerie(Long medImageSerie) {
        this.medImageSerie = medImageSerie;
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
}

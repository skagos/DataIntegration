package EvoMon.DataIntegration.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "file_history")
public class FileHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "SUCCESS_INSERT")
    private Boolean data;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

}






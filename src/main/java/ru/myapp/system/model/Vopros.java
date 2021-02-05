package ru.myapp.system.model;


import javax.persistence.*;
import java.sql.Blob;


@Entity
@Table(name = "product")
public class Vopros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "opisanie")
    private String opisanie;

    @Column(name = "flag")
    private boolean flag;

    @Column(name = "file")
    private String file;

    @Column(name = "fileblob")
    private byte[] filebaty;

    @Transient
    private Blob fileBlob;

    @Column(name = "otvet")
    private String otvet;

    public void setFileBlob(Blob fileBlob) {
        this.fileBlob = fileBlob;
    }

    public byte[] getFilebaty() {
        return filebaty;
    }

    public void setFilebaty(byte[] fileblob) {
        this.filebaty = fileblob;
    }

    public String getOtvet() {
        return otvet;
    }

    public void setOtvet(String otvet) {
        this.otvet = otvet;
    }



    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flagClose) {
        this.flag = flagClose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTtle(String title) {
        this.title = title;
    }

    public String getOpisanie() {
        return opisanie;
    }

    public void setOpisanie(String opisanie) {
        this.opisanie = opisanie;
    }
}

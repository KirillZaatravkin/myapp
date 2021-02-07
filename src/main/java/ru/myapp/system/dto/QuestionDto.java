package ru.myapp.system.dto;

public class QuestionDto {
    private int id;

    private String title;

    private String description;

    private boolean flag;

    private String file;

    private byte[] filebaty;

    private String otvet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public byte[] getFilebaty() {
        return filebaty;
    }

    public void setFilebaty(byte[] filebaty) {
        this.filebaty = filebaty;
    }

    public String getOtvet() {
        return otvet;
    }

    public void setOtvet(String otvet) {
        this.otvet = otvet;
    }
}

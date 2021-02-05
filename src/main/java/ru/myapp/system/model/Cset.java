package ru.myapp.system.model;

import javax.persistence.*;

@Entity
@Table(name = "cset")
public class Cset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "cset")
    private int cset;

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

    public int getCset() {
        return cset;
    }

    public void setCset(int cset) {
        this.cset = cset;
    }
}

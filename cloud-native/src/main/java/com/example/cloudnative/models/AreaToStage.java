package com.example.cloudnative.models;

import javax.persistence.*;

@Entity
public class AreaToStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String wordDescription;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    public AreaToStage(String wordDescription, Area area) {
        this.wordDescription = wordDescription;
        this.area = area;
    }

    public AreaToStage() {
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordDescription() {
        return wordDescription;
    }

    public void setWordDescription(String wordDescription) {
        this.wordDescription = wordDescription;
    }

    @Override
    public String toString() {
        return "AreaToStage{" +
                "id=" + id +
                ", wordDescription='" + wordDescription + '\'' +
                ", area=" + area +
                '}';
    }
}

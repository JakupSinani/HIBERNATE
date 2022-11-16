package com.example.hb_01_one_to_one_uni.entity;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    // define fields

    //define constructor

    //define getter/setters

    //define toString()

    //annotate fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private int id;
    @JoinColumn(name = "comment")
    private String comment;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}

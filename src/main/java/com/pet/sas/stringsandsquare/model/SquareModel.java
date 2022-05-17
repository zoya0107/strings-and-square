package com.pet.sas.stringsandsquare.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "square")
public class SquareModel {
    @Id
    private Long id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "a1")
    private int a1;
    @Column(name = "a2")
    private int a2;
    @Column(name = "a3")
    private int a3;
    @Column(name = "a4")
    private int a4;
    @Column(name = "a5")
    private int a5;
    @Column(name = "a6")
    private int a6;
    @Column(name = "a7")
    private int a7;
    @Column(name = "a8")
    private int a8;
    @Column(name = "a9")
    private int a9;

    @OneToOne(mappedBy = "square")
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private TypeModel type;

    public Long getId() {
        return id;
    }

}

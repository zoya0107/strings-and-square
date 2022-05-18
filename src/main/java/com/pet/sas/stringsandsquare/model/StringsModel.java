package com.pet.sas.stringsandsquare.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "strings")
@Data
public class StringsModel {
    @Id
    private Long id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "s1")
    private String s1;

    @Column(name = "s2")
    private String s2;

    @OneToOne(mappedBy = "strings")
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private TypeModel type;

}

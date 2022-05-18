package com.pet.sas.stringsandsquare.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "task_type")
@Data
public class TypeModel {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private SquareModel square;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private StringsModel strings;

}

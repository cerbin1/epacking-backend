package com.epacking.notes.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "note")
@Data
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate dateFrom;

    @Column
    private LocalDate dateTo;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String comment;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id")
    private Set<Item> items;

}

package com.epacking.notes.model;

import com.epacking.authentication.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "note")
@Getter
@Setter
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
@JsonBackReference
    //    @JoinColumn(name="user_id", nullable = true)
    private User user;

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

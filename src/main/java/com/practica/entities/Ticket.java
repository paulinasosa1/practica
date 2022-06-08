package com.practica.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "estimate")
    private float estimate;

}

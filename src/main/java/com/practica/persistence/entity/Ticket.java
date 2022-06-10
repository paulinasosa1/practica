package com.practica.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty(message = "Titulo es requerido")
    private String title;

    @Column(name = "detail")
    @NotEmpty(message = "Detalle es requerido")
    private String detail;

    @Column(name = "estimate")
    @Min(value = 0, message = "El valor de la estimación no puede ser negativo")
    private float estimate;

}

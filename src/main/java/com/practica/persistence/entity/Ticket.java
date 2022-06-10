package com.practica.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;


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
    @DecimalMin(value = "0.0", inclusive = false, message = "La estimación es requerida y debe ser mayor a 0.0")
    @Digits(integer = 2, fraction = 1, message = "Se debe respetar el formato de estimación XX,X")
    @Min(value = 0, message = "El valor de la estimación no puede ser negativo")
    private float estimate;

}

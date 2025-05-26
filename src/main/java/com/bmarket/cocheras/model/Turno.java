package com.bmarket.cocheras.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity(name = "turno")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_turno;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_entrada",nullable = false)
    private LocalDateTime entrada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_salida")
    private LocalDateTime salida;

    @Column(name ="matricula",nullable = false)
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id", nullable = false)  // Clave foránea
    private TipoVehiculo tipo;

    @Column(name ="piso")
    private int piso;

    @Column(name = "posicion")
    private int pos;
}

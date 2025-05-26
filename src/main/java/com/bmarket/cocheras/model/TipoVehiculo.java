package com.bmarket.cocheras.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tipovehiculo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre; // Ejemplo: "Camioneta", "Moto", "Auto"

    @Column(name = "precio",nullable = false)
    @OneToMany(mappedBy = "tipoVehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrecioVehiculo> precio;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)  // Relación con Turno
    private List<Turno> turnos;
}
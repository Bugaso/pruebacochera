package com.bmarket.cocheras.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrecioVehiculo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private BigDecimal precio;

    private LocalDate fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id",nullable = false)
    private TipoVehiculo tipoVehiculo;

}

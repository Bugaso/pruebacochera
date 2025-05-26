package com.bmarket.cocheras.service;

import com.bmarket.cocheras.model.PrecioVehiculo;
import com.bmarket.cocheras.model.TipoVehiculo;
import com.bmarket.cocheras.repository.PrecioVehiculoRepository;
import com.bmarket.cocheras.repository.TipoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class TipoVehiculoService {
    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    private PrecioVehiculoRepository precioVehiculoRepository;


    public TipoVehiculo addTipoVehiculo(TipoVehiculo tipoVehiculo) {
        if (tipoVehiculoRepository.existsByNombre(tipoVehiculo.getNombre())) {
            throw new IllegalArgumentException("Ya existe un tipo de vehículo con ese nombre.");
        }

        // Solo seteamos la fecha si no viene una (opcional)
        if (tipoVehiculo.getFecha() == null) {
            tipoVehiculo.setFecha(LocalDateTime.now());
        }

        // Si tiene precios, asegurarse de que estén correctamente referenciados
        if (tipoVehiculo.getPrecio() != null) {
            for (PrecioVehiculo precio : tipoVehiculo.getPrecio()) {
                precio.setTipoVehiculo(tipoVehiculo); // clave: asociar el tipo
            }
        }

        return tipoVehiculoRepository.save(tipoVehiculo);
    }


    public void actualizarPrecio(Long id, BigDecimal precio){
        TipoVehiculo vehiculo = tipoVehiculoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Tipo de vehiculo no encontrado"));

        PrecioVehiculo precioVehiculo = new PrecioVehiculo();
        precioVehiculo.setTipoVehiculo(vehiculo);
        precioVehiculo.setPrecio(precio);
        precioVehiculo.setFechaActualizacion(LocalDate.now());

        precioVehiculoRepository.save(precioVehiculo);
    }

    BigDecimal obtenerPrecioActualPorTipo(Long tipoVehiculoId) {
        return precioVehiculoRepository
                .findFirstByTipoVehiculoOrderByFechaActualizacionDesc(tipoVehiculoRepository.findById(tipoVehiculoId).get())
                .map(PrecioVehiculo::getPrecio)
                .orElseThrow(() -> new RuntimeException("No hay precio definido para el tipo de vehículo"));
    }

    public TipoVehiculo findById(Long id){
        return tipoVehiculoRepository.findById(id).orElseThrow();
    }

    public List<TipoVehiculo> findAll(){
        return tipoVehiculoRepository.findAll();
    }
}

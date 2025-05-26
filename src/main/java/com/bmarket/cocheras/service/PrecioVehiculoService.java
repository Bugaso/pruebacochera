package com.bmarket.cocheras.service;

import com.bmarket.cocheras.model.PrecioVehiculo;
import com.bmarket.cocheras.repository.PrecioVehiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class PrecioVehiculoService {

    private PrecioVehiculoRepository precioVehiculoRepository;

    public void save(PrecioVehiculo precio){
        precioVehiculoRepository.save(precio);
    }
}

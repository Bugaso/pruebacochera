package com.bmarket.cocheras.repository;

import com.bmarket.cocheras.model.PrecioVehiculo;
import com.bmarket.cocheras.model.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface PrecioVehiculoRepository extends JpaRepository<PrecioVehiculo,Long> {

    //Precio mas reciente
    Optional<PrecioVehiculo> findFirstByTipoVehiculoOrderByFechaActualizacionDesc(TipoVehiculo tipoVehiculo);

}

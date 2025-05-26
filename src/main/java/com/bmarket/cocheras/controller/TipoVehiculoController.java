package com.bmarket.cocheras.controller;


import com.bmarket.cocheras.model.PrecioVehiculo;
import com.bmarket.cocheras.model.TipoVehiculo;
import com.bmarket.cocheras.model.TipoVehiculoConPrecioDTO;
import com.bmarket.cocheras.service.PrecioVehiculoService;
import com.bmarket.cocheras.service.TipoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tipo-vehiculo")
public class TipoVehiculoController {
    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    private PrecioVehiculoService precioVehiculoService;

    @PostMapping("/crear-con-precio")
    public ResponseEntity<TipoVehiculoConPrecioDTO> createTipoVehiculoConPrecio(@RequestBody TipoVehiculoConPrecioDTO dto) {

        if (dto.getPrecio() == 0) {
            return ResponseEntity.badRequest().build(); // Seguridad extra
        }

        TipoVehiculo tipo = new TipoVehiculo();
        tipo.setNombre(dto.getNombre());
        tipo.setFecha(LocalDateTime.now());

        PrecioVehiculo precio = new PrecioVehiculo();
        precio.setPrecio(BigDecimal.valueOf(dto.getPrecio()));
        precio.setFechaActualizacion(LocalDate.now());
        precio.setTipoVehiculo(tipo); // Muy importante


        List<PrecioVehiculo> precios = new ArrayList<>();
        precios.add(precio);

        tipo.setPrecio(precios); // Vinculamos ambos

        TipoVehiculo guardado = tipoVehiculoService.addTipoVehiculo(tipo); // Se guardan los dos

        return ResponseEntity.ok(new TipoVehiculoConPrecioDTO(guardado.getNombre(),precio.getPrecio().doubleValue()));
    }


    @PostMapping("/crear")
    public ResponseEntity<TipoVehiculo> createTipoVehiculo(@RequestBody TipoVehiculo tipoVehiculo){
        if(tipoVehiculo.getFecha() == null){
            tipoVehiculo.setFecha(LocalDateTime.now());
        }
        TipoVehiculo aux = tipoVehiculoService.addTipoVehiculo(tipoVehiculo);
        return ResponseEntity.ok(aux);
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<String> actualizarPrecio(
            @PathVariable Long id,
            @RequestBody BigDecimal nuevoPrecio
    ){

        tipoVehiculoService.actualizarPrecio(id,nuevoPrecio);
        return ResponseEntity.ok("Precio actualizado :)");

    }

}

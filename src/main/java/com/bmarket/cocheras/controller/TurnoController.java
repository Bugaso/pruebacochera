package com.bmarket.cocheras.controller;


import com.bmarket.cocheras.model.Turno;
import com.bmarket.cocheras.service.TipoVehiculoService;
import com.bmarket.cocheras.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/turnos-fragment")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @GetMapping("/turnos-form")
    public String mostrarTurnos(Model model) {
        return "turnos :: turnos-form"; // Retorna el fragmento 'turnos-form' dentro de la plantilla 'turnos.html'
    }

    @GetMapping("/turnos")

    public String mostrarFormulario(Model model) {
        Turno turno = new Turno();
        // Formato para 'datetime-local' (yyyy-MM-dd'T'HH:mm)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        // Asignar fechas formateadas si es necesario
        if (turno.getEntrada() != null) {
            String formattedEntrada = sdf.format(turno.getEntrada());
            model.addAttribute("formattedEntrada", formattedEntrada);
        }

        if (turno.getSalida() != null) {
            String formattedSalida = sdf.format(turno.getSalida());
            model.addAttribute("formattedSalida", formattedSalida);
        }

        // Agregar los demás atributos del modelo
        model.addAttribute("turno", turno);
        model.addAttribute("tiposVehiculo", tipoVehiculoService.findAll());

        return "turnos"; // Asegúrate de que el template 'turnos' se está devolviendo
    }


    @PostMapping("/crear")
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        // Si necesitas asignar una fecha de entrada, la puedes establecer aquí
        if (turno.getEntrada() == null) {
            turno.setEntrada(LocalDateTime.now());  // Asigna la fecha actual si no se ha proporcionado
        }

        Turno turnoCreado = turnoService.addTurno(turno);
        return ResponseEntity.ok(turnoCreado);
    }

    @GetMapping("/{id_turno}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable("id_turno") Long id) {
        Turno t = turnoService.findById(id);
        if (t == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(t);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Turno> buscarTurnoPorMatricula(@RequestParam String matricula) {
        Optional<Turno> turnoOpt = turnoService.buscarTurnoActivo(matricula);
        return turnoOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package com.bmarket.cocheras.service;

import com.bmarket.cocheras.model.Turno;
import com.bmarket.cocheras.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno addTurno(Turno t){
        if(turnoRepository.findByMatricula(t.getMatricula()) !=null){
            return turnoRepository.save(t);
        }
        return null;
    }

    public void finalizarTurno(String matricula){
        try {
            Turno turno = buscarTurnoActivo(matricula).orElseThrow();
            turno.setSalida(LocalDateTime.now());
        }catch (Exception e){
            System.err.println("Error en buscar turno - "+e.getMessage());
        }

    }

    public void finalizarTurno(String matricula, LocalDateTime salida){

        try {
        Turno turno = buscarTurnoActivo(matricula).orElseThrow();
        turno.setSalida(salida);

        }catch (Exception e){
            System.err.println("Error en buscar turno - "+e.getMessage());
        }

    }

    public Optional<Turno> buscarTurnoActivo(String matricula){
        return turnoRepository.findFirstByMatriculaAndSalidaIsNullOrderByEntradaDesc(matricula);
    }

    public Turno findById(Long id){
        return turnoRepository.findById(id).orElse(null);
    }

    public List<Turno> findTurnos(){
        return turnoRepository.findAll();
    }

}

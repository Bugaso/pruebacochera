package com.bmarket.cocheras.repository;

import com.bmarket.cocheras.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {

    Turno findByMatricula(String matricula);

    Optional<Turno> findFirstByMatriculaAndSalidaIsNullOrderByEntradaDesc(String matricula);

}

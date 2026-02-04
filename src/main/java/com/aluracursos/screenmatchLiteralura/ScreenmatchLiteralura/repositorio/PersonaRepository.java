package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.repositorio;

import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(Integer fechaDeNacimiento, Integer fechaDeFallecimiento);
    List<Persona> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoIsNull(Integer fechaDeNacimiento);
    Optional<Persona> findByNombre(String nombre);
}

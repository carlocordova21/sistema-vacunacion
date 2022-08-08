package com.carlocordova.sistemavacunacion.repositories;

import com.carlocordova.sistemavacunacion.entities.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
}

package com.carlocordova.sistemavacunacion.repositories;

import com.carlocordova.sistemavacunacion.entities.Dosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DosisRepository extends JpaRepository<Dosis, Long> {
}

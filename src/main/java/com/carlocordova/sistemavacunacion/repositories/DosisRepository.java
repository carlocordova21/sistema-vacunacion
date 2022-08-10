package com.carlocordova.sistemavacunacion.repositories;

import com.carlocordova.sistemavacunacion.entities.Dosis;
import com.carlocordova.sistemavacunacion.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

public interface DosisRepository extends JpaRepository<Dosis, Long> {
    List<Dosis> findByEmpleadoId(long id);

    Dosis findByEmpleadoIdAndNumeroDosis(long empleadoId, int numeroDosis);
}

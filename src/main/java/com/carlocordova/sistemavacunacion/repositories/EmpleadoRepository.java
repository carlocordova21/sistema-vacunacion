package com.carlocordova.sistemavacunacion.repositories;

import com.carlocordova.sistemavacunacion.dto.EmpleadoDTO;
import com.carlocordova.sistemavacunacion.entities.Empleado;
import com.carlocordova.sistemavacunacion.services.EmpleadoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    @Query(value = "SELECT COUNT(*) FROM dosis d WHERE d.empleado_id = ?1"
            , nativeQuery = true)
    int countVaccinesByEmpleadoId(long empleadoId);

    @Query(value = "SELECT q.* FROM " +
            "(SELECT e.*, COUNT(e.id) AS cantidad_dosis " +
            "FROM empleados e " +
            "INNER JOIN dosis d ON e.id = d.empleado_id " +
            "GROUP BY e.id) AS q WHERE q.cantidad_dosis >= "+ EmpleadoService.NUMERO_MIN_VACUNAS +
            "ORDER BY q.id", nativeQuery = true)
    List<Empleado> findAllVaccinatedEmployees();

    @Query(value = "SELECT DISTINCT e.* FROM empleados e " +
            "INNER JOIN dosis d ON e.id = d.empleado_id " +
            "WHERE d.vacuna_id = ?1", nativeQuery = true)
    List<Empleado> findAllByVaccinesType(long vacunaId);

    @Query(value = "SELECT DISTINCT e.* FROM empleados e " +
            "INNER JOIN dosis d ON e.id = d.empleado_id " +
            "WHERE d.fecha_dosis >= ?1 " +
            "AND d.fecha_dosis <= ?2", nativeQuery = true)
    List<Empleado> findAllByVaccinesDate(LocalDate dateStart, LocalDate dateEnd);
}

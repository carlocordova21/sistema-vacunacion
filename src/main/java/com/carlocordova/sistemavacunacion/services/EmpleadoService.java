package com.carlocordova.sistemavacunacion.services;

import com.carlocordova.sistemavacunacion.dto.EmpleadoDTO;
import com.carlocordova.sistemavacunacion.dto.EmpleadoResponse;
import com.carlocordova.sistemavacunacion.entities.Empleado;

import java.time.LocalDate;
import java.util.List;

public interface EmpleadoService {
    int NUMERO_MIN_VACUNAS = 3;
    EmpleadoDTO createEmployee(EmpleadoDTO empleadoDTO);

    EmpleadoResponse findAllEmployees(int page, int size, String sortBy, String sortDir);

    EmpleadoDTO findEmployeeById(long id);

    EmpleadoDTO updateEmployee(EmpleadoDTO empleadoDTO, long id);

    void deleteEmployee(long id);

    EmpleadoResponse findAllVaccinatedEmployees(int page, int size, String sortBy, String sortDir);

    List<EmpleadoDTO> findAllByVaccinesType(long vacunaId);

    List<EmpleadoDTO> findAllByVaccinesDate(LocalDate dateStart, LocalDate dateEnd);
}

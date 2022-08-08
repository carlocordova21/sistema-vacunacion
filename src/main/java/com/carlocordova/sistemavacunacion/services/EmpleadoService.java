package com.carlocordova.sistemavacunacion.services;

import com.carlocordova.sistemavacunacion.dto.EmpleadoDTO;
import com.carlocordova.sistemavacunacion.dto.EmpleadoResponse;

import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO createEmployee(EmpleadoDTO empleadoDTO);

    EmpleadoResponse getAllEmployees(int page, int size, String sortBy, String sortDir);

    EmpleadoDTO findEmployeeById(long id);

    EmpleadoDTO updateEmployee(EmpleadoDTO empleadoDTO, long id);

    void deleteEmployee(long id);
}

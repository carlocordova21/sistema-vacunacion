package com.carlocordova.sistemavacunacion.services;

import com.carlocordova.sistemavacunacion.dto.VacunaDTO;
import com.carlocordova.sistemavacunacion.entities.Vacuna;

import java.util.List;

public interface VacunaService {
    VacunaDTO createVaccine(VacunaDTO vacunaDTO);

    List<VacunaDTO> findAllVaccines();

    VacunaDTO findVaccineById(long id);

    VacunaDTO updateVaccine(VacunaDTO vacunaDTO, long id);

    void deleteVaccine(long id);
}

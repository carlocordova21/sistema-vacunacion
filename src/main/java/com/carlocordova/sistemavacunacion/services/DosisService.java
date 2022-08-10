package com.carlocordova.sistemavacunacion.services;

import com.carlocordova.sistemavacunacion.dto.DosisDTO;

import java.util.List;

public interface DosisService {
    DosisDTO createDose(long empleadoId, long vacunaId, DosisDTO dosisDTO);

    List<DosisDTO> findAllDosesByEmployeeId(long id);

    DosisDTO findDoseById(long empleadoId, long vacunaId, long id);

    DosisDTO updateDose(long empleadoId, long vacunaId, DosisDTO dosisDTO, long id);

    void deleteDoseByDoseNumber(long empleadoId, int dosisNumber);
}

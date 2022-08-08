package com.carlocordova.sistemavacunacion.services;

import com.carlocordova.sistemavacunacion.dto.VacunaDTO;
import com.carlocordova.sistemavacunacion.entities.Vacuna;
import com.carlocordova.sistemavacunacion.exceptions.ResourceNotFoundException;
import com.carlocordova.sistemavacunacion.repositories.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacunaServiceImpl implements VacunaService {
    @Autowired
    private VacunaRepository vacunaRepository;

    @Override
    public VacunaDTO createVaccine(VacunaDTO vacunaDTO) {
        //Convertir DTO a Entity
        Vacuna vacuna = mapEntity(vacunaDTO);
        //Guardar en base de datos
        Vacuna nuevaVacuna = vacunaRepository.save(vacuna);
        //Convertir Entity a DTO y retornar respuesta
        return mapDTO(nuevaVacuna);
    }

    @Override
    public List<VacunaDTO> findAllVaccines() {
        List<Vacuna> vacunas = vacunaRepository.findAll();
        return vacunas.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public VacunaDTO findVaccineById(long id) {
        Vacuna vacuna = vacunaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada", "id", id));
        return mapDTO(vacuna);
    }

    @Override
    public VacunaDTO updateVaccine(VacunaDTO vacunaDTO, long id) {
        Vacuna vacuna = vacunaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada", "id", id));
        vacuna.setNombre(vacunaDTO.getNombre());

        Vacuna vacunaActualizada = vacunaRepository.save(vacuna);
        return mapDTO(vacunaActualizada);
    }

    @Override
    public void deleteVaccine(long id) {
        Vacuna vacuna = vacunaRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Vacuna no encontrada", "id", id)));
        vacunaRepository.delete(vacuna);
    }

    private VacunaDTO mapDTO(Vacuna vacuna) {
        VacunaDTO vacunaDTO = new VacunaDTO();
        vacunaDTO.setId(vacuna.getId());
        vacunaDTO.setNombre(vacuna.getNombre());
        return vacunaDTO;
    }

    private Vacuna mapEntity(VacunaDTO vacunaDTO) {
        Vacuna vacuna = new Vacuna();
        vacuna.setNombre(vacunaDTO.getNombre());
        return vacuna;
    }
}

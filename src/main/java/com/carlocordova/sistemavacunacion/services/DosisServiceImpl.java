package com.carlocordova.sistemavacunacion.services;

import com.carlocordova.sistemavacunacion.dto.DosisDTO;
import com.carlocordova.sistemavacunacion.entities.Dosis;
import com.carlocordova.sistemavacunacion.entities.Empleado;
import com.carlocordova.sistemavacunacion.entities.Vacuna;
import com.carlocordova.sistemavacunacion.exceptions.ResourceNotFoundException;
import com.carlocordova.sistemavacunacion.exceptions.VacunacionAppException;
import com.carlocordova.sistemavacunacion.repositories.DosisRepository;
import com.carlocordova.sistemavacunacion.repositories.EmpleadoRepository;
import com.carlocordova.sistemavacunacion.repositories.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DosisServiceImpl implements DosisService {
    @Autowired
    private DosisRepository dosisRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private VacunaRepository vacunaRepository;

    @Override
    public DosisDTO createDose(long empleadoId, long vacunaId, DosisDTO dosisDTO) {
        //Convertir DTO a Entity
        Dosis dosis = mapEntity(dosisDTO);
        //Buscar Empleado
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado", "id", empleadoId));
        //Buscar Vacuna
        Vacuna vacuna = vacunaRepository.findById(vacunaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada", "id", vacunaId));
        //Asignar Empleado y Vacuna a Dosis
        dosis.setEmpleado(empleado);
        dosis.setVacuna(vacuna);
        //Guardar en la base de datos
        Dosis nuevaDosis = dosisRepository.save(dosis);

        //Cambiar estado de vacunado segun el numero minimo de vacunas
        int cantidadVacunas = empleadoRepository.countVaccinesByEmpleadoId(empleadoId);
        empleado.setEsVacunado(cantidadVacunas >= EmpleadoService.NUMERO_MIN_VACUNAS);
        empleadoRepository.save(empleado);

        //Convertir Entity a DTO
        return mapDTO(nuevaDosis);
    }

    @Override
    public List<DosisDTO> findAllDosesByEmployeeId(long empleadoId) {
        List<Dosis> listaDosis = dosisRepository.findByEmpleadoId(empleadoId);
        return listaDosis.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public DosisDTO findDoseById(long empleadoId, long vacunaId, long id) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado", "id", empleadoId));
        Vacuna vacuna = vacunaRepository.findById(vacunaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada", "id", vacunaId));
        Dosis dosis = dosisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dosis no encontrada", "id", id));

        if(!dosis.getEmpleado().getId().equals(empleado.getId()) || !dosis.getVacuna().getId().equals(vacuna.getId())) {
            System.out.println("No coinciden los ids");
            throw new VacunacionAppException(HttpStatus.BAD_REQUEST, "El empleado no tiene acceso a la dosis");
        }
        return mapDTO(dosis);
    }

    @Override
    public DosisDTO updateDose(long empleadoId, long vacunaId, DosisDTO dosisDTO, long id) {
        //Buscar Dosis
        Dosis dosis = dosisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dosis no encontrada", "id", id));
        //Buscar Empleado
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado", "id", empleadoId));
        //Buscar Vacuna
        Vacuna vacuna = vacunaRepository.findById(vacunaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada", "id", vacunaId));

        if(!dosis.getEmpleado().getId().equals(empleado.getId()) || !dosis.getVacuna().getId().equals(vacuna.getId())) {
            System.out.println("No coinciden los ids");
            throw new VacunacionAppException(HttpStatus.BAD_REQUEST, "El empleado no tiene acceso a la dosis");
        }

        Dosis nuevaDosis = mapEntity(dosisDTO);
        dosis.setEmpleado(empleado);
        dosis.setVacuna(vacuna);
        dosis.setFechaDosis(nuevaDosis.getFechaDosis());
        dosis.setNumeroDosis(nuevaDosis.getNumeroDosis());

        Dosis dosisActualizada = dosisRepository.save(dosis);
        return mapDTO(dosisActualizada);
    }

    @Override
    public void deleteDoseByDoseNumber(long empleadoId, int dosisNumber) {
        Dosis dosis = dosisRepository.findByEmpleadoIdAndNumeroDosis(empleadoId, dosisNumber);

        if(dosis == null) {
            throw new ResourceNotFoundException("Dosis no encontrada", "numeroDosis", (long) dosisNumber);
        }
        //Eliminar empleado
        dosisRepository.deleteById(dosis.getId());

        //Cambiar estado de vacunado segun el numero minimo de vacunas
        Empleado empleado = empleadoRepository.findById(dosis.getEmpleado().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado", "id", dosis.getEmpleado().getId()));
        int cantidadVacunas = empleadoRepository.countVaccinesByEmpleadoId(empleado.getId());
        empleado.setEsVacunado(cantidadVacunas >= EmpleadoService.NUMERO_MIN_VACUNAS);
        empleadoRepository.save(empleado);
    }

    private DosisDTO mapDTO(Dosis dosis) {
        DosisDTO dosisDTO = new DosisDTO();
        dosisDTO.setId(dosis.getId());
        dosisDTO.setFechaDosis(dosis.getFechaDosis());
        dosisDTO.setNumeroDosis(dosis.getNumeroDosis());
        return dosisDTO;
    }

    private Dosis mapEntity(DosisDTO dosisDTO) {
        Dosis dosis = new Dosis();
        dosis.setFechaDosis(dosisDTO.getFechaDosis());
        dosis.setNumeroDosis(dosisDTO.getNumeroDosis());
        return dosis;
    }
}

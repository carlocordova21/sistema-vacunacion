package com.carlocordova.sistemavacunacion.services;

import com.carlocordova.sistemavacunacion.dto.EmpleadoDTO;
import com.carlocordova.sistemavacunacion.dto.EmpleadoResponse;
import com.carlocordova.sistemavacunacion.entities.Empleado;
import com.carlocordova.sistemavacunacion.exceptions.ResourceNotFoundException;
import com.carlocordova.sistemavacunacion.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoDTO createEmployee(EmpleadoDTO empleadoDTO) {
        //Convertir DTO a Entity
        Empleado empleado = mapEntity(empleadoDTO);
        //Guardar en base de datos
        Empleado nuevoEmpleado = empleadoRepository.save(empleado);
        //Convertir Entity a DTO y retornar respuesta
        return mapDTO(nuevoEmpleado);
    }

    @Override
    public EmpleadoResponse getAllEmployees(int page, int size, String sortBy, String sortDir) {
        //Paginacion de empleados
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Empleado> pageEmpleados = empleadoRepository.findAll(pageable);
        List<Empleado> empleados = pageEmpleados.getContent();
        List<EmpleadoDTO> content = empleados.stream().map(this::mapDTO).collect(Collectors.toList());

        EmpleadoResponse empleadoResponse = new EmpleadoResponse();
        empleadoResponse.setContent(content);
        empleadoResponse.setNumberPage(pageEmpleados.getNumber());
        empleadoResponse.setSizePage(pageEmpleados.getSize());
        empleadoResponse.setTotalElements(pageEmpleados.getTotalElements());
        empleadoResponse.setTotalPages(pageEmpleados.getTotalPages());
        empleadoResponse.setLast(pageEmpleados.isLast());
        return empleadoResponse;
    }

    @Override
    public EmpleadoDTO findEmployeeById(long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado", "id", id));
        return mapDTO(empleado);
    }

    @Override
    public EmpleadoDTO updateEmployee(EmpleadoDTO empleadoDTO, long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado", "id", id));
        empleado.setCedula(empleadoDTO.getCedula());
        empleado.setNombres(empleadoDTO.getNombres());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setCorreo(empleadoDTO.getCorreo());

        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return mapDTO(empleadoActualizado);
    }

    @Override
    public void deleteEmployee(long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado", "id", id));
        empleadoRepository.delete(empleado);
    }

    //Convertir Entity a DTO
    private EmpleadoDTO mapDTO(Empleado empleado) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setCedula(empleado.getCedula());
        empleadoDTO.setNombres(empleado.getNombres());
        empleadoDTO.setApellidos(empleado.getApellidos());
        empleadoDTO.setCorreo(empleado.getCorreo());
        return empleadoDTO;
    }

    //Convertir DTO a Entity
    private Empleado mapEntity(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado();
        empleado.setCedula(empleadoDTO.getCedula());
        empleado.setNombres(empleadoDTO.getNombres());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setCorreo(empleadoDTO.getCorreo());
        return empleado;
    }
}

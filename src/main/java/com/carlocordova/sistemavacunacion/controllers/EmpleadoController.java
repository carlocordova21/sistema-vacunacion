package com.carlocordova.sistemavacunacion.controllers;

import com.carlocordova.sistemavacunacion.dto.EmpleadoDTO;
import com.carlocordova.sistemavacunacion.dto.EmpleadoResponse;
import com.carlocordova.sistemavacunacion.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@RequestBody EmpleadoDTO empleadoDTO) {
        return new ResponseEntity<>(empleadoService.createEmployee(empleadoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public EmpleadoResponse findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {
        return empleadoService.getAllEmployees(page, size, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> findById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(empleadoService.findEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> update(@RequestBody EmpleadoDTO empleadoDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(empleadoService.updateEmployee(empleadoDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") long id) {
        empleadoService.deleteEmployee(id);
        return new ResponseEntity<>("Empleado eliminado con id '" + id + "'", HttpStatus.OK);
    }
}

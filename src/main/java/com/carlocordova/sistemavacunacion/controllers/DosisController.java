package com.carlocordova.sistemavacunacion.controllers;

import com.carlocordova.sistemavacunacion.dto.DosisDTO;
import com.carlocordova.sistemavacunacion.entities.Dosis;
import com.carlocordova.sistemavacunacion.entities.Empleado;
import com.carlocordova.sistemavacunacion.services.DosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DosisController {
    @Autowired
    private DosisService dosisService;

    @PostMapping("/empleados/{empleadoId}/vacunas/{vacunaId}/dosis")
    public ResponseEntity<DosisDTO> create(@PathVariable long empleadoId, @PathVariable long vacunaId, @RequestBody DosisDTO dosisDTO) {
        return new ResponseEntity<>(dosisService.createDose(empleadoId, vacunaId, dosisDTO), HttpStatus.CREATED);
    }

    @PostMapping("/prueba")
    public String prueba(@RequestBody DosisDTO dosisDTO) {
        return dosisDTO.getFechaDosis().toString();
    }

    @GetMapping("/empleados/{empleadoId}/dosis")
    public List<DosisDTO> findAllByEmployeeId(@PathVariable long empleadoId) {
        return dosisService.findAllDosesByEmployeeId(empleadoId);
    }

    @GetMapping("/empleados/{empleadoId}/vacunas/{vacunaId}/dosis/{id}")
    public DosisDTO findById(@PathVariable long empleadoId, @PathVariable long vacunaId, @PathVariable long id) {
        return dosisService.findDoseById(empleadoId, vacunaId, id);
    }

    @DeleteMapping("/dosis/{numeroDosis}")
    public ResponseEntity<String> deleteByDoseNumber(@PathVariable long empleadoId, @PathVariable long numeroDosis) {
        return new ResponseEntity<>("Dosis de empleado con id '"+ empleadoId +"' y numeroDosis '"+ numeroDosis +"' eliminada", HttpStatus.OK);
    }
}

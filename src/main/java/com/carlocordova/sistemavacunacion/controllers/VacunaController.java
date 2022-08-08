package com.carlocordova.sistemavacunacion.controllers;

import com.carlocordova.sistemavacunacion.dto.VacunaDTO;
import com.carlocordova.sistemavacunacion.services.VacunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacunas")
public class VacunaController {
    @Autowired
    private VacunaService vacunaService;

    @PostMapping
    public ResponseEntity<VacunaDTO> create(@RequestBody VacunaDTO vacunaDTO) {
        return new ResponseEntity<>(vacunaService.createVaccine(vacunaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<VacunaDTO> findAll() {
        return vacunaService.findAllVaccines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacunaDTO> findById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(vacunaService.findVaccineById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacunaDTO> update(@RequestBody VacunaDTO vacunaDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(vacunaService.updateVaccine(vacunaDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        vacunaService.deleteVaccine(id);
        return new ResponseEntity<>("Vacuna eliminada con id '" + id + "'", HttpStatus.OK);
    }
}

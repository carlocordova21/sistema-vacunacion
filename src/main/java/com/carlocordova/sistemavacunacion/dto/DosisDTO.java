package com.carlocordova.sistemavacunacion.dto;

import java.time.LocalDate;

public class DosisDTO {
    private Long id;
    private String nombreEmpleado;
    private String nombreVacuna;
    private LocalDate fechaDosis;
    private Integer numeroDosis;

    public DosisDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public LocalDate getFechaDosis() {
        return fechaDosis;
    }

    public void setFechaDosis(LocalDate fechaDosis) {
        this.fechaDosis = fechaDosis;
    }

    public Integer getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(Integer numeroDosis) {
        this.numeroDosis = numeroDosis;
    }
}

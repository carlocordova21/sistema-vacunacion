package com.carlocordova.sistemavacunacion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DosisDTO {
    private Long id;
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

package com.carlocordova.sistemavacunacion.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dosis")
public class Dosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacuna_id", nullable = false)
    private Vacuna vacuna;

    @Column(name = "fecha_dosis", nullable = false)
    private LocalDate fechaDosis;

    @Column(name = "numero_dosis", nullable = false)
    private Integer numeroDosis;

    public Dosis() {
    }

    public Dosis(Long id, Empleado empleado, Vacuna vacuna, LocalDate fechaDosis, Integer numeroDosis) {
        this.id = id;
        this.empleado = empleado;
        this.vacuna = vacuna;
        this.fechaDosis = fechaDosis;
        this.numeroDosis = numeroDosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
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

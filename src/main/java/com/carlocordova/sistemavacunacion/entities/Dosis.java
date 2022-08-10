package com.carlocordova.sistemavacunacion.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "dosis")
public class Dosis {
    private static final int MAX_DOSES = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacuna_id", nullable = false)
    private Vacuna vacuna;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_dosis", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaDosis;

    @Column(name = "numero_dosis", nullable = false)
    @Min(1)
    @Max(MAX_DOSES)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dosis dosis = (Dosis) o;
        return id.equals(dosis.id) && empleado.equals(dosis.empleado) && vacuna.equals(dosis.vacuna) && fechaDosis.equals(dosis.fechaDosis) && numeroDosis.equals(dosis.numeroDosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empleado, vacuna, fechaDosis, numeroDosis);
    }

    @Override
    public String toString() {
        return "Dosis{" +
                "id=" + id +
                ", empleado=" + empleado +
                ", vacuna=" + vacuna +
                ", fechaDosis=" + fechaDosis +
                ", numeroDosis=" + numeroDosis +
                '}';
    }
}

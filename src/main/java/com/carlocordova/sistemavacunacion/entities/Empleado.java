package com.carlocordova.sistemavacunacion.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "empleados",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"cedula"})}
)
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cedula", nullable = false)
    private String cedula;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "correo", nullable = false)
    private String correo;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "es_vacunado")
    private boolean esVacunado;
    @Column(name = "activo", nullable = false, columnDefinition = "boolean default true")
    private boolean activo;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dosis> dosis = new HashSet<>();

    public Empleado() {
        //Constructor vacio
    }

    public Empleado(Long id, String cedula, String nombres, String apellidos, String correo, LocalDate fechaNacimiento, String direccion, String telefono, boolean esVacunado, boolean activo) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.esVacunado = esVacunado;
        this.activo = activo;
    }

    public Empleado(String cedula, String nombres, String apellidos, String correo) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEsVacunado() {
        return esVacunado;
    }

    public void setEsVacunado(boolean esVacunado) {
        this.esVacunado = esVacunado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return esVacunado == empleado.esVacunado && activo == empleado.activo && Objects.equals(id, empleado.id) && cedula.equals(empleado.cedula) && nombres.equals(empleado.nombres) && apellidos.equals(empleado.apellidos) && correo.equals(empleado.correo) && Objects.equals(fechaNacimiento, empleado.fechaNacimiento) && Objects.equals(direccion, empleado.direccion) && Objects.equals(telefono, empleado.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cedula, nombres, apellidos, correo, fechaNacimiento, direccion, telefono, esVacunado, activo);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", esVacunado=" + esVacunado +
                ", activo=" + activo +
                '}';
    }
}

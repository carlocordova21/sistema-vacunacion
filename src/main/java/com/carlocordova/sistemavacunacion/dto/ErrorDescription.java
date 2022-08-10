package com.carlocordova.sistemavacunacion.dto;

import java.util.Date;

public class ErrorDescription {
    private Date marca;
    private String mensaje;
    private String detalles;

    public ErrorDescription(Date marca, String mensaje, String detalles) {
        super();
        this.marca = marca;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public Date getMarca() {
        return marca;
    }

    public void setMarcaDeTiempo(Date marca) {
        this.marca = marca;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "ErrorDescription{" +
                "marca=" + marca +
                ", mensaje='" + mensaje + '\'' +
                ", detalles='" + detalles + '\'' +
                '}';
    }
}

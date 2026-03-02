package com.nomina.dominio;


public class Empleado {

    public String nombre;
    public String cargo;        // "GERENTE" | "COORDINADOR" | "ASISTENTE" | "OPERARIO"
    public int    horasTrabajadas;
    public boolean tieneHijosACargo;

    public Empleado(String nombre, String cargo, int horasTrabajadas, boolean tieneHijosACargo) {
        this.nombre           = nombre;
        this.cargo            = cargo;
        this.horasTrabajadas  = horasTrabajadas;
        this.tieneHijosACargo = tieneHijosACargo;
    }
}

package com.nomina.dominio;

/**
 * Resultado del cálculo de nómina para un empleado.
 */
public class ResultadoNomina {

    public String nombreEmpleado;
    public String cargo;
    public double salarioBase;
    public double bonificacion;
    public double descuentoSalud;
    public double descuentoPension;
    public double totalDevengado;   // salarioBase + bonificacion
    public double totalDeducido;    // descuentoSalud + descuentoPension
    public double netoAPagar;       // totalDevengado - totalDeducido

 
}

package com.nomina.servicio;

import com.nomina.dominio.Empleado;
import com.nomina.dominio.ResultadoNomina;


public class NominaService {

   
    private static final double PORCENTAJE_SALUD    = 0.04;  // 4%
    private static final double PORCENTAJE_PENSION  = 0.04;  // 4%

    
    public double calcularSalarioBase(Empleado empleado) {

        if (empleado.cargo.equals("GERENTE")) {
            return 8_500_000;

        } else if (empleado.cargo.equals("COORDINADOR")) {
            return 4_200_000;

        } else if (empleado.cargo.equals("ASISTENTE")) {
            return 2_100_000;

        } else if (empleado.cargo.equals("OPERARIO")) {
            return 1_300_000;

        } else {
            
            throw new RuntimeException("Cargo no reconocido: " + empleado.cargo);
        }
    }

    
    public double calcularBonificacion(Empleado empleado) {

        if (empleado.cargo.equals("GERENTE")) {
            // Bono fijo por productividad + bono por hijos
            double bono = 1_200_000;
            if (empleado.tieneHijosACargo) {
                bono += 500_000;
            }
            return bono;

        } else if (empleado.cargo.equals("COORDINADOR")) {
            double bono = 600_000;
            if (empleado.tieneHijosACargo) {
                bono += 350_000;
            }
            return bono;

        } else if (empleado.cargo.equals("ASISTENTE")) {
            
            double bono = 300_000;
            if (empleado.tieneHijosACargo) {
                bono += 200_000; 
            }
            return bono;

        } else if (empleado.cargo.equals("OPERARIO")) {
            // Auxilio de transporte fijo, sin bono por hijos
            return 162_000;

        } else {
            throw new RuntimeException("Cargo no reconocido: " + empleado.cargo);
        }
    }


    public double calcularDescuentoSalud(Empleado empleado) {
        double base = calcularSalarioBase(empleado);

        if (empleado.cargo.equals("GERENTE")) {
            return base * PORCENTAJE_SALUD;

        } else if (empleado.cargo.equals("COORDINADOR")) {
            return base * PORCENTAJE_SALUD;

        } else if (empleado.cargo.equals("ASISTENTE")) {
            return base * PORCENTAJE_SALUD;

        } else if (empleado.cargo.equals("OPERARIO")) {
            return base * PORCENTAJE_SALUD;

        } else {
            throw new RuntimeException("Cargo no reconocido: " + empleado.cargo);
        }
    }

    public double calcularDescuentoPension(Empleado empleado) {
        double base = calcularSalarioBase(empleado);

        
        if (empleado.cargo.equals("GERENTE")) {
            return base * PORCENTAJE_PENSION;

        } else if (empleado.cargo.equals("COORDINADOR")) {
            return base * PORCENTAJE_PENSION;

        } else if (empleado.cargo.equals("ASISTENTE")) {
            return base * PORCENTAJE_PENSION;

        } else if (empleado.cargo.equals("OPERARIO")) {
            return base * PORCENTAJE_PENSION;

        } else {
            throw new RuntimeException("Cargo no reconocido: " + empleado.cargo);
        }
    }

    /**
     * Orquesta el cálculo completo y arma el ResultadoNomina.
     */
    public ResultadoNomina calcular(Empleado empleado) {
        double salarioBase       = calcularSalarioBase(empleado);
        double bonificacion      = calcularBonificacion(empleado);
        double descuentoSalud    = calcularDescuentoSalud(empleado);
        double descuentoPension  = calcularDescuentoPension(empleado);

        ResultadoNomina resultado = new ResultadoNomina();
        resultado.nombreEmpleado  = empleado.nombre;
        resultado.cargo           = empleado.cargo;
        resultado.salarioBase     = salarioBase;
        resultado.bonificacion    = bonificacion;
        resultado.descuentoSalud  = descuentoSalud;
        resultado.descuentoPension = descuentoPension;
        resultado.totalDevengado  = salarioBase + bonificacion;
        resultado.totalDeducido   = descuentoSalud + descuentoPension;
        resultado.netoAPagar      = resultado.totalDevengado - resultado.totalDeducido;

        return resultado;
    }
}

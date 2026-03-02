package com.nomina.reporte;

import com.nomina.dominio.ResultadoNomina;

public class ReporteService {

    public String generarRecibo(ResultadoNomina resultado) {

        
        String encabezado;

        if (resultado.cargo.equals("GERENTE")) {
            encabezado = "=== LIQUIDACIÓN EJECUTIVA — NIVEL GERENCIAL ===";

        } else if (resultado.cargo.equals("COORDINADOR")) {
            encabezado = "=== LIQUIDACIÓN — COORDINACIÓN ===";

        } else if (resultado.cargo.equals("ASISTENTE")) {
            encabezado = "=== LIQUIDACIÓN — ASISTENCIA ===";

        } else if (resultado.cargo.equals("OPERARIO")) {
            encabezado = "=== LIQUIDACIÓN — OPERACIONES ===";

        } else {
            
            encabezado = "=== LIQUIDACIÓN ===";
        }

        return String.format("""
                %s
                Empleado  : %s
                Cargo     : %s
                ─────────────────────────────
                Salario base    : $%,.0f
                Bonificación    : $%,.0f
                ─────────────────────────────
                Total devengado : $%,.0f
                Descuento salud : $%,.0f
                Desc. pensión   : $%,.0f
                Total deducido  : $%,.0f
                ─────────────────────────────
                NETO A PAGAR    : $%,.0f
                """,
                encabezado,
                resultado.nombreEmpleado,
                resultado.cargo,
                resultado.salarioBase,
                resultado.bonificacion,
                resultado.totalDevengado,
                resultado.descuentoSalud,
                resultado.descuentoPension,
                resultado.totalDeducido,
                resultado.netoAPagar
        );
    }
}

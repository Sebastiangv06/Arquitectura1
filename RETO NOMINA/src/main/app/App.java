package com.nomina.app;

import com.nomina.dominio.Empleado;
import com.nomina.dominio.ResultadoNomina;
import com.nomina.reporte.ReporteService;
import com.nomina.servicio.NominaService;


public class App {

    public static void main(String[] args) {
        NominaService nominaService = new NominaService();
        ReporteService reporteService = new ReporteService();

        Empleado[] empleados = {
            new Empleado("Laura Martínez",  "GERENTE",     160, true),
            new Empleado("Carlos Herrera",  "COORDINADOR", 160, false),
            new Empleado("Ana Gómez",       "ASISTENTE",   160, true),
            new Empleado("Pedro Ramírez",   "OPERARIO",    160, false),
           
        };

        for (Empleado emp : empleados) {
            ResultadoNomina resultado = nominaService.calcular(emp);
            String recibo = reporteService.generarRecibo(resultado);
            System.out.println(recibo);
        }
    }
}

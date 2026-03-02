package com.nomina.servicio;

import com.nomina.dominio.Empleado;
import com.nomina.dominio.ResultadoNomina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class NominaServiceTest {

    private NominaService service;

    @BeforeEach
    void setUp() {
        service = new NominaService();
    }

    // ─── GERENTE ─────────────────────────────────────────────────────────
    @Test
    void gerente_sin_hijos_calcula_neto_correcto() {
        Empleado emp = new Empleado("Laura", "GERENTE", 160, false);
        ResultadoNomina r = service.calcular(emp);

        
        assertEquals(8_500_000, r.salarioBase,   0.01);
        assertEquals(1_200_000, r.bonificacion,  0.01);
        assertEquals(9_700_000, r.totalDevengado, 0.01);

        double expectedSalud   = 8_500_000 * 0.04;
        double expectedPension = 8_500_000 * 0.04;
        assertEquals(expectedSalud,   r.descuentoSalud,    0.01);
        assertEquals(expectedPension, r.descuentoPension,  0.01);
        assertEquals(9_700_000 - expectedSalud - expectedPension, r.netoAPagar, 0.01);
    }

    @Test
    void gerente_con_hijos_recibe_bono_adicional() {
        Empleado emp = new Empleado("Laura", "GERENTE", 160, true);
        ResultadoNomina r = service.calcular(emp);

        
        assertEquals(1_700_000, r.bonificacion, 0.01);
    }

    // ─── COORDINADOR ─────────────────────────────────────────────────────────

    @Test
    void coordinador_sin_hijos_calcula_correctamente() {
        Empleado emp = new Empleado("Carlos", "COORDINADOR", 160, false);
        ResultadoNomina r = service.calcular(emp);

        assertEquals(4_200_000, r.salarioBase,  0.01);
        assertEquals(600_000,   r.bonificacion, 0.01);
    }

    @Test
    void coordinador_con_hijos_recibe_bono_adicional() {
        Empleado emp = new Empleado("Carlos", "COORDINADOR", 160, true);
        ResultadoNomina r = service.calcular(emp);

        assertEquals(950_000, r.bonificacion, 0.01);
    }

    // ─── ASISTENTE ───────────────────────────────────────────────────────────

    @Test
    void asistente_sin_hijos_calcula_correctamente() {
        Empleado emp = new Empleado("Ana", "ASISTENTE", 160, false);
        ResultadoNomina r = service.calcular(emp);

        assertEquals(2_100_000, r.salarioBase,  0.01);
        assertEquals(300_000,   r.bonificacion, 0.01);
    }

    @Test
    void asistente_con_hijos_recibe_bono_por_hijos() {
        Empleado emp = new Empleado("Ana", "ASISTENTE", 160, true);
        ResultadoNomina r = service.calcular(emp);

     
        assertEquals(500_000, r.bonificacion, 0.01); // 300_000 + 200_000
    }

    // ─── OPERARIO ────────────────────────────────────────────────────────────

    @Test
    void operario_siempre_recibe_auxilio_transporte() {
        Empleado empSinHijos  = new Empleado("Pedro", "OPERARIO", 160, false);
        Empleado empConHijos  = new Empleado("Pedro", "OPERARIO", 160, true);

        // El operario no tiene bono por hijos — ambos deben dar lo mismo
        assertEquals(162_000, service.calcularBonificacion(empSinHijos), 0.01);
        assertEquals(162_000, service.calcularBonificacion(empConHijos), 0.01);
    }

   

    @Test
    void cargo_desconocido_lanza_excepcion_en_runtime() {
        Empleado emp = new Empleado("X", "SUPERVISOR", 160, false);

       
        assertThrows(RuntimeException.class, () -> service.calcular(emp));
    }
}

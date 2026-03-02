# Reto — Sistema de Nómina

## Contexto

Una empresa colombiana liquida mensualmente la nómina de sus empleados.
Cada empleado tiene un cargo, y según ese cargo se calcula:

- **Salario base**
- **Bonificación** (algunos cargos dan un bono adicional si el empleado tiene hijos a cargo)
- **Descuentos de ley:** salud 4% y pensión 4% sobre el salario base
- **Neto a pagar:** lo que devengó menos lo que se le descuenta

Los cargos actuales son:

| Cargo       | Salario base | Bonificación base | Bono por hijos |
|-------------|-------------|-------------------|----------------|
| GERENTE     | $8.500.000  | $1.200.000        | +$500.000      |
| COORDINADOR | $4.200.000  | $600.000          | +$350.000      |
| ASISTENTE   | $2.100.000  | $300.000          | +$200.000      |
| OPERARIO    | $1.300.000  | $162.000          | sin bono       |

## Lo que pide el negocio

La empresa acaba de crear un nuevo cargo: **AUXILIAR**.

- Salario base: $1.300.000
- Bonificación: auxilio de transporte $162.000
- Sin bono por hijos
- Descuentos de ley iguales al resto

Impleméntenlo.

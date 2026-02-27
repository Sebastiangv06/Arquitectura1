# Arquitectura1
Arquitectura de software 1 grupo 190304005-3

💳 Proyecto G6: Sistema de Procesamiento de Pagos
Este repositorio contiene la implementación de una arquitectura modular para el procesamiento de transacciones, aplicando principios de diseño SOLID y patrones GRASP.

🎯 Dominio Elegido
Pasarela de Pagos Digitales (E-commerce / Finanzas).
El sistema actúa como un motor agnóstico que gestiona diversas modalidades de pago para una plataforma comercial.

⚠️ Descripción del Problema de Diseño Inicial
En la versión inicial (el "Antes"), el sistema presentaba las siguientes deficiencias:

Violación del OCP: El procesador central contenía múltiples bloques condicionales (if/else) para identificar cada tipo de pago. Añadir un nuevo método obligaba a modificar y recompilar el núcleo del sistema.

Baja Cohesión: La clase principal asumía la responsabilidad de conocer los detalles de implementación de cada entidad bancaria o método de recaudo.

Fragilidad: Cualquier cambio en la lógica de un pago (ej. un cambio en la API de PSE) ponía en riesgo la estabilidad de los otros métodos de pago.

🛠️ Principios Aplicados
1. OCP (Open/Closed Principle)
Cerrado a la modificación: El núcleo del sistema no se toca una vez probado.

Abierto a la extensión: Implementamos una estructura que permite añadir nuevos métodos sin alterar el código existente.

2. Polimorfismo (Patrón GRASP)
Utilizamos una Interfaz como contrato común para las clases CardPayment, CashPayment y PsePayment.

El sistema envía el mismo mensaje (process) y cada clase responde con su lógica específica, permitiendo un comportamiento dinámico.

💡 Decisiones de Diseño Relevantes y Justificación
Abstracción de Métodos de Pago: Se decidió crear clases independientes para CardPayment, CashPayment y PsePayment.

Justificación: Esto garantiza una Alta Cohesión, donde cada clase se encarga únicamente de su protocolo de pago específico.

Desacoplamiento mediante Interfaz: El procesador de pagos solo conoce la interfaz, no las clases concretas.

Justificación: Logramos un Bajo Acoplamiento, facilitando el mantenimiento y las pruebas unitarias sin depender de implementaciones rígidas.

Preparación para Escalabilidad: El diseño permite que, si en el futuro se desea integrar pagos con Cripto o QR, solo sea necesario crear una nueva clase que cumpla el contrato establecido, cumpliendo el ideal del OCP.

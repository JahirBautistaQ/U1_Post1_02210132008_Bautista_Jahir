Análisis de Violaciones a los Principios SOLID

El código de este taller corresponde a la clase OrderManager, la cual implementa un sistema de gestión de pedidos. Durante el análisis se identificaron múltiples violaciones a los principios SOLID, lo que dificulta su mantenimiento, escalabilidad y reutilización.

Violaciones que encontre en el analisis:

1. Violación del Principio de Responsabilidad Única (SRP)

El principio SRP nos dice que una clase debe tener un solo motivo para cambiar. Basicamente: "El SRP dice que cada (clase) debe tener un solo (trabajo específico) así si algo cambia en el sistema (por ejemplo requerimientos), solo afectamos a una parte y no arriesgamos a que todo el sistema falle por una confusión de tareas.

La clase OrderManager concentra múltiples responsabilidades:

- Creación de pedidos
- Cálculo de descuentos
- Persistencia en archivo (File I/O)
- Envío de notificaciones al cliente
- Cálculo de impuestos
- Generación de reportes
- Gestión de almacenamiento en memoria

Esto implica que cualquier cambio en alguna de estas funcionalidades obligaría a modificar la misma clase, aumentando el riesgo de errores y dificultando el mantenimiento.

2. Violación del Principio Abierto/Cerrado (OCP)

El principio OCP establece que las entidades deben estar abiertas para extensión pero cerradas para modificación.

La lógica de descuentos está implementada mediante condiciones dentro del método createOrder:

- if (total > 1000) total *= 0.9;
- if (total > 5000) total *= 0.85;

Si se requiere agregar nuevos tipos de descuento (por ejemplo, clientes VIP, promociones especiales o descuentos por temporada), sería necesario modificar directamente el código existente, lo cual viola este principio.

3. Violación del Principio de Inversión de Dependencias (DIP)

El principio DIP indica que los módulos de alto nivel no deben depender de módulos de bajo nivel, sino de abstracciones.

La clase depende directamente de implementaciones concretas, como:

- java.io.FileWriter para guardar datos en archivo
- System.out.println para notificaciones

Esto impide cambiar fácilmente el mecanismo de persistencia (por ejemplo, base de datos o almacenamiento en la nube) o el canal de notificación (SMS, correo real, notificaciones push), ya que la lógica está fuertemente acoplada a implementaciones específicas.

4. Falta de Separación de Responsabilidades y Bajo Nivel de Cohesión

La clase mezcla lógica de negocio con operaciones de entrada/salida y presentación:

- Cálculos de negocio (descuentos, impuestos)
- Persistencia en archivo
- Comunicación con el usuario (mensajes por consola)
- Generación de reportes

Además, utiliza una estructura de datos poco adecuada para representar pedidos:

- List<String[]> orders = new ArrayList<>();

El uso de arreglos de cadenas para almacenar información estructurada dificulta la legibilidad, aumenta la probabilidad de errores y rompe el encapsulamiento, ya que no existe una entidad clara que represente un pedido.

¿SERIA FACIL AGREGAR UNA NUEVA FUNCIONALIDAD?

El diseño actual presenta un alto nivel de acoplamiento y baja cohesión, lo que dificulta significativamente la incorporación de nuevas funcionalidades sin modificar el código existente. Cualquier cambio en descuentos, persistencia, notificaciones o generación de reportes requeriría alterar la misma clase, aumentando el riesgo de generar errores.

Estas deficiencias justifican la necesidad de refactorizar el sistema aplicando los principios SOLID, mediante la separación de responsabilidades, el uso de interfaces y la inyección de dependencias, con el objetivo de lograr un código más flexible, mantenible y extensible.
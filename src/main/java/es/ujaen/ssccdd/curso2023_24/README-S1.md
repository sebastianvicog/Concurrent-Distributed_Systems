# 1. Descentralized Agreement System

Se quiere implementar un sistema de consenso descentralizado donde los diferentes nodos presentes en el sistema deben valorar una serie de propuestas para poder alcanzar una decisión por consenso.

Para la realización del ejercicio propuesto se deben crear las siguientes clases:

- Añadir todas las constantes necesarias en la interface **Constantes**  para la resolución del ejercicio. Además hay que añadir las constantes que tengas un ámbito de ejecución asociado al proyecto, en otro caso se definirán en el archivo de la clase asociada.
- Definir la clase  **Nodo**  que tendrá un identificador único que será su nombre y un  **Comportamiento**. Deberá implementarse el método toString()  para tener una representación del nodo.
- Definir la clase  **Decision**  tendrá un identificador único, una lista de los nodos asociados, umbral para avanzar de estado y una lista con los estados disponibles para la decisión y los nodos asociados al estado. Inicialmente todos los nodos estarán en el estado  **PROPUESTA**. Se deberán definir métodos para:

    - Pedir el avance a un estado. Los nodos cambiarán de estado atendiendo a su comportamiento. Además debemos asegurarnos que hay el número mínimo de nodos en el estado previo.
    - Estado actual de la decisión.
    - Saber si se ha finalizado la decisión
    - También hay que dar una implementación para el método toString() para dar una representación _imprimible_ al usuario.


- **HiloPrincipal**:

  - Se crea una lista de 4 decisiones con un número aleatorio de nodos de entre 6 y 8. El umbral mínimo será del 80% de los nodos presentes en una decisión
  - Ejecutar un número de ciclos entre 10 y 20 o hasta que se hayan completados todas las decisiones.

    - Seleccionar aleatoriamente la mitad de las decisiones y pedir un avance en la decisión si es posible.
    - Comprobar si las decisiones se han completado e incluirlas en una lista.
    - Si una decisión no avanza pasadas tres peticiones se reducirá el humbral un 10%.
  - Antes de finalizar presentar la lista de decisiones completadas y una lista con el resto de decisiones
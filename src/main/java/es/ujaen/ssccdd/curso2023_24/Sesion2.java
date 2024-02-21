package es.ujaen.ssccdd.curso2023_24;

import java.util.ArrayList;
import java.util.List;

public class Sesion2 {
    private static final int NUM_DECISIONES = 4;
    private static final int MIN_NODOS = 6;
    private static final int MAX_NODOS = 8;
    private static final int UMBRAL = 80; // 80% para avanzar de estado
    private static final int MIN_CICLOS = 10;
    private static final int MAX_CICLOS = 20;


    public static void main(String[] args) {
        // Declaración de variables
        List<Moderador> moderadores = new ArrayList<>();
        List<Thread> moderadorThreads = new ArrayList<>();

        // Inicialización de variables
        for (int i = 0; i < NUM_DECISIONES; i++) {
            Moderador moderador = new Moderador("Moderador_" + i, UMBRAL);
            moderadores.add(moderador);
            Thread thread = new Thread(moderador); // Creamos un hilo que ejecuta el moderador
            moderadorThreads.add(thread); // Agregamos el hilo a la lista
            thread.start(); // Iniciar el hilo del moderador
        }

        // Cuerpo de ejecución
        // Ejecutar 60 ciclos
        for (int ciclo = 1; ciclo <= 60; ciclo++) {
            // Crear datos para hasta 3 decisiones
            for (Moderador moderador : moderadores) {
                if (moderador.getDecisionesPendientes() < 3 && moderador.getCiclos() < MAX_CICLOS) {
                    Decision decision = new Decision("Decision_" + moderador.getDecisionesCreadas(), moderador.getNodos(), UMBRAL);
                    moderador.nuevaDecision(decision);
                    moderador.incrementarDecisionesCreadas();
                    moderador.setCiclos(0); // Reiniciar contador de ciclos
                }
            }

            // Crear hasta 5 nuevos nodos
            for (Moderador moderador : moderadores) {
                for (int i = 0; i < 5; i++) {
                    String idNodo = "Nodo_" + moderador.getNodosCreados();
                    Constantes.Comportamiento comportamiento = Constantes.Comportamiento.getComportamiento();
                    Nodo nodo = new Nodo(idNodo, comportamiento);
                    moderador.nuevoNodo(nodo);
                    moderador.incrementarNodosCreados();
                }
            }

            // Solicitar avances en las decisiones en ciclos pares
            if (ciclo % 2 == 0) {
                for (Moderador moderador : moderadores) {
                    moderador.avanceDecisiones();
                }
            }

            // Reducir el umbral de consenso si se alcanza un número de intentos sin avance
            for (Moderador moderador : moderadores) {
                if (moderador.getNumFallosAvance() >= Constantes.FALLOS_AVANCE) {
                    moderador.reducirUmbral();
                    moderador.resetNumFallosAvance();
                }
            }

            // Simular 1 segundo de ejecución
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Incrementar el contador de ciclos de los moderadores
            for (Moderador moderador : moderadores) {
                moderador.incrementarCiclos();
            }
        }

        // Solicitar interrupción de los hilos de los moderadores y esperar a que finalicen
        for (Thread thread : moderadorThreads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Presentar resultados
        System.out.println("\n---------- RESULTADOS ----------\n");
        for (Moderador moderador : moderadores) {
            System.out.println("Moderador: " + moderador.getNombre());
            moderador.getDecisiones().forEach(System.out::println);
            System.out.println();
        }

        System.out.println("\n----------- FIN RESULTADOS ----------\n");

        // Finalización
        System.out.println("Hilo(Principal) Ha finalizado");
    }
}

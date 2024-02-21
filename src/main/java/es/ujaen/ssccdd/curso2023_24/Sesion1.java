package es.ujaen.ssccdd.curso2023_24;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
public class Sesion1 {
    public static void main(String[] args) {
        // Declarar variables
        List<Decision> decisiones = new ArrayList<>();
        List<Decision> decisionesCompletadas = new ArrayList<>();
        int numCiclos;
        int ciclosRealizados = 0;

        // Inicializar variables
        for (int i = 0; i < 4; ++i){
            int numNodos = Constantes.aleatorio.nextInt(Constantes.MAX_NUM_NODOS - Constantes.MIN_NUM_NODOS + 1) + Constantes.MIN_NUM_NODOS; //Entre 6 y 8 nodos
            List<Nodo> nodos = new ArrayList<>();
            for (int j = 0; j < numNodos; ++j){
                nodos.add(new Nodo("Nodo" + j, Constantes.Comportamiento.getComportamiento()));
            }
            decisiones.add(new Decision("Decision" + i, nodos, (int) (numNodos * Constantes.UMBRAL_MINIMO)));
        }

        numCiclos = Constantes.aleatorio.nextInt(Constantes.MAX_NUM_CICLOS - Constantes.MIN_NUM_CICLOS + 1) + Constantes.MIN_NUM_CICLOS;

        // Cuerpo de ejecución
        while (ciclosRealizados < numCiclos && !decisiones.isEmpty()){
            List<Decision> decisionesAleatorias = new ArrayList<>(decisiones);
            Collections.shuffle(decisionesAleatorias);
            int numDecisionesAProcesar = decisionesAleatorias.size() / 2;
            for (int i = 0; i < numDecisionesAProcesar; ++i){
                Decision decision = decisionesAleatorias.get(i);
                decision.avanzarEstado();
                if (decision.decisionFinalizada()){
                    decisionesCompletadas.add(decision);
                    decisiones.remove(decision);
                }
            }
            ciclosRealizados++;

            for (int i = 0; i < decisiones.size(); ++i){
                Decision decision = decisionesAleatorias.get(i);
                if (decision.getEstado() == Constantes.EstadoConsenso.PROPUESTA.ordinal()){
                    decision.avanzarEstado();
                    if (decision.getEstado() == Constantes.EstadoConsenso.PROPUESTA.ordinal()){
                        decision.avanzarEstado();
                        if (decision.getEstado() == Constantes.EstadoConsenso.PROPUESTA.ordinal()){
                            decision.umbral -= decision.umbral * Constantes.REDUCCION_UMBRAL;
                        }
                    }
                }
            }
        }

        // Presentar resultados
        System.out.println("Decisiones completadas: ");
        for (Decision decision : decisionesCompletadas){
            System.out.println(decision);
        }

        System.out.println("\nResto de decisiones: ");
        for (Decision decision : decisiones){
            System.out.println(decision);
        }


        // Finalización
        System.out.println("\nHa finalizado la ejecución del Hilo(PRINCIPAL)");
    }
}
*/
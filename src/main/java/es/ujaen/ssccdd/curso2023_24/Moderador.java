package es.ujaen.ssccdd.curso2023_24;

import java.util.ArrayList;
import java.util.List;

public class Moderador implements Runnable {
    private final String nombre; //Identificador único dentro del sistema.
    private final List<Nodo> nodos;
    private final List<Decision> decisiones;
    private int umbral;
    private int decisionesCreadas;
    private int nodosCreados;
    private int ciclos;
    private int numFallosAvance;

    /**
     * @brief Constructor de la clase Moderador
     * @param nombre
     * @param umbral
     */
    public Moderador(String nombre, int umbral) {
        this.nombre = nombre;
        this.nodos = new ArrayList<>();
        this.decisiones = new ArrayList<>();
        this.umbral = umbral;
        this.decisionesCreadas = 0;
        this.nodosCreados = 0;
        this.ciclos = 0;
        this.numFallosAvance = 0;
    }

    /**
     * @brief run
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !todasDecisionesCompletadas()) {
            if (ciclos % 2 == 0) {
                avanceDecisiones();
            }
            if (numFallosAvance >= Constantes.FALLOS_AVANCE) {
                reducirUmbral();
                numFallosAvance = 0;
            }
            if (decisionesCreadas < Constantes.MAX_DECISIONES && ciclos >= Constantes.MIN_CICLOS && ciclos <= Constantes.MAX_CICLOS) {
                int numNodos = (int) (umbral * 1.2);
                obtenerNodos(numNodos);
                crearDecision();
                ciclos++;
            } else {
                ciclos++;
            }
            try {
                Thread.sleep(1000); // Esperar 1 segundo (equivalente a un ciclo)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaurar el estado interruptible
                System.err.println("El hilo del moderador ha sido interrumpido.");

            }
        }
        //Decisiones completadas
        for (Decision decision : decisiones) {
            if (decision.completada()) {
                System.out.println(decision);
            }
        }
    }

    /**
     * @brief Decisiones completadas
     * @return bool
     */
    private boolean todasDecisionesCompletadas() {
        for (Decision decision : decisiones) {
            if (!decision.completada()) {
                return false;
            }
        }
        return true;
    }

    private void obtenerNodos(int numNodos) {
        // Generar nodos aleatorios
        for (int i = 0; i < numNodos; i++) {
            Nodo nodo = new Nodo("Nodo_" + nodosCreados++, Constantes.Comportamiento.getComportamiento());
            nodos.add(nodo);
        }
    }
    public List<Nodo> getNodos() {
        return nodos;
    }

    public int getNodosCreados() {
        return nodosCreados;
    }

    public void incrementarNodosCreados() {
        nodosCreados++;
    }

    /**
     * @brief nuevaDecision
     *
     * Este método recibe una instancia de la clase Decision y la agrega a la lista de decisiones del moderador.
     *
     * @param decision
     */
    public void nuevaDecision(Decision decision) {
        decisiones.add(decision);
    }


    /**
     * @brief crearDecision
     *
     * Este método se encarga de crear una nueva instancia de Decision utilizando los nodos y el umbral actuales del moderador.
     *
     */
    private void crearDecision() {
        Decision decision = new Decision("Decision_" + decisionesCreadas++, nodos, umbral);
        decisiones.add(decision);
    }

    public void incrementarDecisionesCreadas() {
        decisionesCreadas++;
    }

    public int getDecisionesCreadas() {
        return decisionesCreadas;
    }

    /**
     * @brief avanceDecisiones
     *
     * Este método se encarga de avanzar el estado de todas las decisiones supervisadas por el moderador.
     *
     */
    void avanceDecisiones() {
        for (Decision decision : decisiones) {
            decision.avanceDecision();
        }
    }

    /**
     * @brief nuevoNodo
     * @param nodo
     */
    public void nuevoNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    void reducirUmbral() {
        umbral -= Constantes.INTERVALO_REDUCCION_UMBRAL;
    }

    public int getDecisionesPendientes() {
        int count = 0;
        for (Decision decision : decisiones) {
            if (!decision.completada()) {
                count++;
            }
        }
        return count;
    }

    public int getCiclos() {
        return ciclos;
    }

    public void setCiclos(int ciclos) {
        this.ciclos = ciclos;
    }

    public int getNumFallosAvance() {
        return numFallosAvance;
    }

    public void resetNumFallosAvance() {
        numFallosAvance = 0;
    }

    public void incrementarCiclos() {
        ciclos++;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Decision> getDecisiones() {
        return decisiones;
    }
}
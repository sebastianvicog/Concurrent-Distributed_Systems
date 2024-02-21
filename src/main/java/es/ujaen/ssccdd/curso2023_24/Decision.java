package es.ujaen.ssccdd.curso2023_24;

import es.ujaen.ssccdd.curso2023_24.Constantes.EstadoConsenso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static es.ujaen.ssccdd.curso2023_24.Constantes.*;
import static es.ujaen.ssccdd.curso2023_24.Constantes.EstadoConsenso.PROPUESTA;

public class Decision {
    private final String iD;
    private final List<Nodo>[] estadoNodos;
    private int umbral;
    private final int nodosDecision;
    private EstadoConsenso estadoActual;
    private int numFallosAvance;

    public Decision(String iD, List<Nodo> listaNodos, int umbral) {
        this.iD = iD;
        this.umbral = umbral;
        this.estadoActual = PROPUESTA;
        this.numFallosAvance = 0;
        this.nodosDecision = listaNodos.size();

        // Inicializar el estado de la decisión
        this.estadoNodos = new List[estadosDecision.length];
        Arrays.setAll(this.estadoNodos, i -> new ArrayList<>());
        this.estadoNodos[PROPUESTA.ordinal()] = new ArrayList<>(listaNodos);
    }

    public String getiD() {
        return iD;
    }

    public EstadoConsenso getEstadoActual() {
        return estadoActual;
    }

    /**
     * Desde el estado actual de la decisión se solicita a cada nodo presente que cambie
     * al siguiente estado de la decisión atendiendo a su comportamiento.
     *
     * Se cambia el estado actual de la decisión si el número de nodos alcanza al menos el
     * umbral de la decisión o cuenta como un fallo en el avance.
     */
    public void avanceDecision() {
        int aceptarAvance;

        EstadoConsenso estadoSiguiente = this.estadoSiguiente();
        int numNodosAvance = nodosDecision * umbral / 100;
        List<Nodo> listaNodos = new ArrayList<>(estadoNodos[estadoActual.ordinal()]);
        estadoNodos[estadoActual.ordinal()].clear();

        for( Nodo nodo : listaNodos ) {
            aceptarAvance = aleatorio.nextInt(D100);
            // Se comprueba si el nodo cambia al siguiente estado
            if ( nodo.getComportamiento().getAceptarCambio() < aceptarAvance ) {
                estadoNodos[estadoSiguiente.ordinal()].add(nodo);
            } else {
                estadoNodos[estadoActual.ordinal()].add(nodo);
            }
        }

        if ( estadoNodos[estadoSiguiente.ordinal()].size() >= numNodosAvance )
            estadoActual = estadoSiguiente;
        else
            numFallosAvance++;
    }

    /**
     * Si no se ha alcanzado el estado final de la decisión nos devuelve el siguiente
     * en la lista de estados posibles. En otro caso el estado final de la decisión.
     * @return un estado de la decisión
     */
    public EstadoConsenso estadoSiguiente() {
        if ( !completada() )
            return estadosDecision[estadoActual.ordinal() + 1];
        else
            return estadoActual;
    }

    /**
     * Nos indica si se ha alcanzado el estado final de la decisión
     * @return true si se ha alcanzado el estado final, false en otro caso
     */
    public boolean completada() {
        return estadoActual.equals(estadosDecision[estadosDecision.length-1]);
    }


    /**
     * Comprueba si se han producido un número de avances en la decisión para reducir el
     * umbral necesario un % establecido para conseguir el consenso.
     * @return true si se ha reducido el umbral, false en otro caso.
     */
    public boolean reducirUmbral() {
        boolean resultado = false;

        if ( numFallosAvance == FALLOS_AVANCE ) {
            numFallosAvance = 0;
            umbral -= REDUCCION_UMBRAL;
            resultado = true;
        }
        return resultado;
    }

    @Override
    public String toString() {
        String resultado = "Decision{";
        resultado += "\n\tID: " + iD + "\n\tEstado Decision:";

        for ( EstadoConsenso estado : estadosDecision) {
            resultado += "\n\t   " + estado + " : " + estadoNodos[estado.ordinal()];
        }

        resultado += "\n\t Nodos para la decsión:\t" + nodosDecision +
                "\n\tUmbral de decisión:\t\t" + umbral + "%" +
                "\n\tEstado de la decisión:\t" + estadoActual + "\n}";

        return resultado;
    }
}

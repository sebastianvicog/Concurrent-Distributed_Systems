package es.ujaen.ssccdd.curso2023_24.Descentralized_Agreement_System;

import java.util.ArrayList;
import java.util.List;

import static es.ujaen.ssccdd.curso2023_24.Descentralized_Agreement_System.Constantes.D100;

public class Decision {
    private String id;
    private List<Nodo> nodosAsociados;
    int umbral; //Usado para avanzar de estado
    private int estado;
    private List<Nodo> nodosPorEstado[];

    /**
     * @brief Constructor clase Decision
     * @param id
     * @param nodosAsociados
     * @param umbral
     */
    public Decision(String id, List<Nodo> nodosAsociados, int umbral) {
        this.id = id;
        this.nodosAsociados = nodosAsociados;
        this.umbral = umbral;
        this.estado = Constantes.EstadoConsenso.PROPUESTA.ordinal();
        this.nodosPorEstado = new List[]{
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
        };
        // Inicialmente todos los nodos deben estar en el estado PROPUESTA
        for (int i = 0; i < nodosAsociados.size(); ++i){
            Nodo nodo = nodosAsociados.get(i);
            nodosPorEstado[Constantes.EstadoConsenso.PROPUESTA.ordinal()].add(nodo);
        }
    }

    /**
     * @brief Avanzar estado
     *
     * Pedir el avance a un estado. Los nodos cambiarán de estado atendiendo a su comportamiento.
     * Además debemos asegurarnos que hay el número mínimo de nodos en el estado previo.
     *
     */
    public void avanzarEstado(){
        if (estado < Constantes.EstadoConsenso.FINALIZADO.ordinal()){
            int contador = 0;
            List<Nodo> nodosEnEstadoActual = nodosPorEstado[estado];
            for (int i = 0; i < nodosEnEstadoActual.size(); ++i){
                Nodo nodo = nodosEnEstadoActual.get(i);
                if (nodosComportamiento(nodo)){
                    contador++;
                }
            }
            if (((double) contador / nodosPorEstado[estado].size())* D100 >= umbral){
                estado++;
                for (Nodo nodo : nodosAsociados){
                    nodosPorEstado[estado].addAll(nodosAsociados);
                    nodosPorEstado[estado - 1].removeAll(nodosAsociados);
                }
            }
        }
    }

    /**
     * @brief Get Estado
     * @return estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @brief Saber si se ha finalizado la decision
     * @return
     */
    public boolean decisionFinalizada(){
        return estado == Constantes.EstadoConsenso.FINALIZADO.ordinal();
    }

    public boolean nodosComportamiento(Nodo nodo){
        int aceptarCambio = nodo.comportamiento.getAceptarCambio();
        int probabilidad = Constantes.aleatorio.nextInt(D100);
        return probabilidad <= aceptarCambio;
    }

    /**
     * @brief Dar informacion al usuario del id, umbral y estado.
     *
     * He puesto los nodos asociados al final para que la informacion que se muestra sea mas clara
     *
     * @return
     */
    @Override
    public String toString() {
        return "Decision{" +
                "id='" + id + '\'' +
                ", umbral=" + umbral +
                ", estado=" + Constantes.EstadoConsenso.values()[estado] +
                " | ( NODOS ASOCIADOS ) =" + nodosAsociados +
                '}';
    }
}

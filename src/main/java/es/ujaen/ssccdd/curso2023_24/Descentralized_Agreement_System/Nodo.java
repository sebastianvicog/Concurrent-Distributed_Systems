package es.ujaen.ssccdd.curso2023_24.Descentralized_Agreement_System;

import es.ujaen.ssccdd.curso2023_24.Descentralized_Agreement_System.Constantes;

public class Nodo {
    private String id;
    Constantes.Comportamiento comportamiento;

    /**
     * @brief Constructor clase Nodo
     * @param id
     * @param comportamiento
     */
    public Nodo(String id, Constantes.Comportamiento comportamiento) {
        this.id = id;
        this.comportamiento = comportamiento;
    }

    /**
     * @brief Representacion del nodo
     * @return
     */
    @Override
    public String toString() {
        return "Nodo{" +
                "id='" + id + '\'' +
                ", comportamiento=" + comportamiento +
                '}';
    }
}

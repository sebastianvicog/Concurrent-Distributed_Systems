package es.ujaen.ssccdd.curso2023_24;

import es.ujaen.ssccdd.curso2023_24.Constantes.Comportamiento;
public class Nodo implements Comparable<Nodo> {
    private final String iD;
    private final Comportamiento comportamiento;

    public Nodo(String iD, Comportamiento comportamiento) {
        this.iD = iD;
        this.comportamiento = comportamiento;
    }

    public String getiD() {
        return iD;
    }

    public Comportamiento getComportamiento() {
        return comportamiento;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "iD='" + iD + '\'' +
                ", comportamiento=" + comportamiento +
                '}';
    }

    @Override
    public int compareTo(Nodo nodo) {
        return this.iD.compareTo(nodo.getiD());
    }
}

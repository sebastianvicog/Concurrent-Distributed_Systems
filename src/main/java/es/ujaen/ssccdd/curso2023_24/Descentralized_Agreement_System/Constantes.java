package es.ujaen.ssccdd.curso2023_24.Descentralized_Agreement_System;

import java.util.Random;

public interface Constantes {
    Random aleatorio = new Random();

    enum EstadoConsenso {
        PROPUESTA(40), VOTACION(30), CONFIRMACION(15),
        EJECUCION(10), FINALIZADO(5);
        private final int peso;

        EstadoConsenso(int peso) {
            this.peso = peso;
        }

        /**
         * Devuelve una categoría de juego aleatoria atendendo a las
         * preferencias del usuario.
         * @return una categoría de juego
         */
        public static EstadoConsenso getEstado() {
            EstadoConsenso resultado = null;
            int suma = 0;
            int indice = 0;
            int peso = aleatorio.nextInt(D100);

            while( (indice < listaCategorias.length) && (resultado == null) ) {
                suma += listaCategorias[indice].peso;
                if( suma > peso )
                    resultado = listaCategorias[indice];

                indice++;
            }

            return resultado;
        }
    }

    enum Comportamiento{
        COLABORADOR(60,90), INDIFERENTE(25,50), DISCREPANTE(15,20);

        private final int peso;
        private final int aceptarCambio;

        Comportamiento(int peso, int aceptarCambio) {
            this.peso = peso;
            this.aceptarCambio = aceptarCambio;
        }

        /**
         * Genera un comportamiento de forma aleatoria atendiendo a su
         * peso
         * @return el comportamiento
         */
        public static Comportamiento getComportamiento() {
            Comportamiento resultado = null;
            int suma = 0;
            int indice = 0;
            int peso = aleatorio.nextInt(D100);

            while( (indice < listaComportamientos.length) && (resultado == null) ) {
                suma += listaComportamientos[indice].peso;
                if( suma > peso )
                    resultado = listaComportamientos[indice];

                indice++;
            }

            return resultado;
        }

        /**
         * La probabilidad de cambio asociada a un comportamiento
         * @return la probabiliad aceptar el cambio
         */
        public int getAceptarCambio() {
            return aceptarCambio;
        }
    }
    int D100 = 100; // Para una tirada aleatoria de probabilidad
    EstadoConsenso[] listaCategorias = EstadoConsenso.values();
    Comportamiento[] listaComportamientos = Comportamiento.values();

    //CONSTANTES NECESARIAS AÑADIDAS
    int MIN_NUM_NODOS = 6;
    int MAX_NUM_NODOS = 8;
    double UMBRAL_MINIMO = 0.8;
    int MIN_NUM_CICLOS = 10;
    int MAX_NUM_CICLOS = 20;
    double REDUCCION_UMBRAL = 0.10;
}

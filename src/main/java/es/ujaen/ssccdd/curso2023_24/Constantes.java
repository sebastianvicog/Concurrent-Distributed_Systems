package es.ujaen.ssccdd.curso2023_24;

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

            while( (indice < estadosDecision.length) && (resultado == null) ) {
                suma += estadosDecision[indice].peso;
                if( suma > peso )
                    resultado = estadosDecision[indice];

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

            while( (indice < comportamientosPosibles.length) && (resultado == null) ) {
                suma += comportamientosPosibles[indice].peso;
                if( suma > peso )
                    resultado = comportamientosPosibles[indice];

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
    EstadoConsenso[] estadosDecision = EstadoConsenso.values();
    Comportamiento[] comportamientosPosibles = Comportamiento.values();
    int FALLOS_AVANCE =3;
    int REDUCCION_UMBRAL = 10; // 10% de reducción en el umbral

    int MAX_DECISIONES = 10; // Máximo de decisiones permitidas
    int MIN_CICLOS = 5; // Mínimo de ciclos permitidos para crear una decisión
    int MAX_CICLOS = 10; // Máximo de ciclos permitidos para crear una decisión

    int INTERVALO_REDUCCION_UMBRAL = 10; // Intervalo para reducir el umbral en un 10%
}

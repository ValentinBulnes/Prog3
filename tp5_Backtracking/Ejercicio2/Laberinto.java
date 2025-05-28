package ProgramacionIII.tp5_Backtracking.Ejercicio2;

import java.util.ArrayList;

public class Laberinto {
    private Casillero destino;
    private Camino mejorCamino;

    public Camino getMejorCamino(Casillero origen) {
        Camino camino = new Camino();
        camino.agregarAlCamino(origen);
        camino.marcarVisitado(origen);
        camino.incrementar(origen.getValor());
        getMejorCamino(origen, camino);
        return mejorCamino;
    }

    private void getMejorCamino(Casillero actual, Camino caminoActual) {
        if (actual.equals(this.destino)) { // Si llegue a destino / condicion de corte
            if (mejorCamino == null || mejorCamino.getValor() > caminoActual.getValor()) {
                mejorCamino = caminoActual;
            }
        } else {
            ArrayList<Casillero> vecinos = actual.getVecinos(); // Me da los casilleros a los cuales me puedo mover
            for (Casillero vecino: vecinos) {
                if (!caminoActual.estaVisitado(vecino)) {
                    // Aplicar / Hacer cambios / Agregar solucion
                    caminoActual.agregarAlCamino(vecino);
                    caminoActual.marcarVisitado(vecino);
                    caminoActual.incrementar(vecino.getValor());
                    if (mejorCamino == null || caminoActual.getValor() <= mejorCamino.getValor()) // Poda
                        getMejorCamino(vecino,caminoActual); // Llamado recursivo

                    // Deshacer
                    caminoActual.quitarUltimo();
                    caminoActual.quitarVisitado(vecino);
                    caminoActual.decrementar(vecino.getValor());
                }
            }
        }
    }
}
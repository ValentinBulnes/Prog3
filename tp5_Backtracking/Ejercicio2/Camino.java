package ProgramacionIII.tp5_Backtracking.Ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Camino {
    private List<Casillero> casilleros;
    private Set<Casillero> visitados;
    private int valor;

    public Camino() {
        this.casilleros = new ArrayList<Casillero>();
        this.visitados = new HashSet<>();
        this.valor = 0;
    }

    public void agregarAlCamino(Casillero casillero) {
        if (!casilleros.contains(casillero)) {
            casilleros.add(casillero);
        }
    }

    public void quitarUltimo() {
        casilleros.removeLast();
    }

    public boolean estaVisitado(Casillero casillero) {
        return visitados.contains(casillero);
    }

    public void marcarVisitado(Casillero casillero) {
        visitados.add(casillero);
    }

    public void quitarVisitado(Casillero casillero) {
        visitados.remove(casillero);
    }

    public int getValor() {
        return valor;
    }

    public void incrementar(int valorCasillero) {
        valor += valorCasillero;
    }

    public void decrementar(int valorCasillero) {
        valor -= valorCasillero;
    }
}

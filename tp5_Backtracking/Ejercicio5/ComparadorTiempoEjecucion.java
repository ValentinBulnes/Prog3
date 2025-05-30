package ProgramacionIII.tp5_Backtracking.Ejercicio5;

import java.util.Comparator;

public class ComparadorTiempoEjecucion implements Comparator<Tarea> {

    @Override
    public int compare(Tarea t1, Tarea t2) {
        return Integer.compare(t2.getTiempoEjecucion(), t1.getTiempoEjecucion());
    }
}

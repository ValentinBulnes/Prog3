package ProgramacionIII.tp5_Backtracking.Ejercicio5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AsignacionTareasAProcesadores {
    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private int mejorTiempo;
    private List<Procesador> mejorAsignacion;

    public List<Procesador> asignarTareas(List<Tarea> tareas, int numProcesadores) {
        // Inicialización
        this.tareas = new ArrayList<>(tareas);
        this.procesadores = new ArrayList<>();
        for (int i = 0; i < numProcesadores; i++) {
            procesadores.add(new Procesador());
        }
        this.mejorTiempo = Integer.MAX_VALUE;
        this.mejorAsignacion = null;

        // Ordenar tareas de mayor a menor tiempo para mejorar eficiencia
        tareas.sort(new ComparadorTiempoEjecucion());

        // Llamar al backtracking
        backtrack(0);

        return new ArrayList<>(mejorAsignacion);
    }

    private void backtrack(int indiceTarea) {
        // Caso base: todas las tareas asignadas
        if (indiceTarea == tareas.size()) {
            // Calcular el tiempo máximo entre los procesadores
            int tiempoActual = calcularTiempoMaximo();

            // Si encontramos una mejor solución, actualizamos
            if (tiempoActual < mejorTiempo) {
                mejorTiempo = tiempoActual;
                // Guardamos una copia del estado actual
                mejorAsignacion = copiarProcesadores();
            }
            return;
        }

        Tarea tareaActual = tareas.get(indiceTarea);

        // Probar asignar la tarea a cada procesador
        for (Procesador proc : procesadores) {
            // Asignar la tarea
            proc.addTarea(tareaActual);

            // Podar si ya superamos el mejor tiempo encontrado
            if (calcularTiempoMaximo() <= mejorTiempo) {
                backtrack(indiceTarea + 1);
            }

            // Deshacer la asignación (backtrack)
            proc.removeTarea(tareaActual);

            // Si el procesador está vacío, no probar con los siguientes (evita simetrías)
            if (proc.getTareasAsignadas().isEmpty()) {
                break;
            }
        }
    }

    private int calcularTiempoMaximo() {
        int maxTiempo = 0;
        for (Procesador proc : procesadores) {
            int tiempoProc = proc.getTiempoEjecucion();
            if (tiempoProc > maxTiempo) {
                maxTiempo = tiempoProc;
            }
        }
        return maxTiempo;
    }

    private List<Procesador> copiarProcesadores() {
        List<Procesador> copia = new ArrayList<>();
        for (Procesador original : procesadores) {
            Procesador nuevo = new Procesador();
            for (Tarea t : original.getTareasAsignadas()) {
                nuevo.addTarea(t);
            }
            copia.add(nuevo);
        }
        return copia;
    }

    public int getMejorTiempo() {
        return mejorTiempo;
    }

    public static void main(String[] args) {
        // Crear lista de tareas
        List<Tarea> tareas = new ArrayList<>();
        tareas.add(new Tarea(5));
        tareas.add(new Tarea(3));
        tareas.add(new Tarea(7));
        tareas.add(new Tarea(2));
        tareas.add(new Tarea(4));

        // Número de procesadores disponibles
        int numProcesadores = 2;

        // Crear asignador y resolver
        AsignacionTareasAProcesadores asignador = new AsignacionTareasAProcesadores();
        List<Procesador> solucion = asignador.asignarTareas(tareas, numProcesadores);

        // Mostrar resultados
        System.out.println("\n--- Mejor asignación encontrada ---");
        System.out.println("Tiempo máximo de ejecución: " + asignador.getMejorTiempo());

        for (int i = 0; i < solucion.size(); i++) {
            Procesador p = solucion.get(i);
            System.out.println("\nProcesador " + (i+1) + " (Tiempo total: " + p.getTiempoEjecucion() + "):");

            for (Tarea t : p.getTareasAsignadas()) {
                System.out.println("  - " + t);
            }
        }

    }
}

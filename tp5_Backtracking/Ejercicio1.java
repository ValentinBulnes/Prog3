package ProgramacionIII.tp5_Backtracking;
import ProgramacionIII.tp4_grafos.GrafoDirigido;

import java.util.*;

public class Ejercicio1 {
    private GrafoDirigido<?> grafo;
    private List<Integer> mejorSolucion;

    public Ejercicio1(GrafoDirigido<?> grafo) {
        this.grafo = grafo;
        this.mejorSolucion = new ArrayList<>();
    }

    public List<Integer> buscarCaminoMasLargo(Integer origen, Integer destino) {
        Set<Integer> visitados = new HashSet<>();
        List<Integer> caminoActual = new ArrayList<>();
        // Iniciar la búsqueda desde la sala de entrada
        buscarCamino(origen, destino, caminoActual, visitados);
        return mejorSolucion;
    }

    private void buscarCamino(Integer actual, Integer destino, List<Integer> caminoActual, Set<Integer> visitados) {
        // Agregar la sala actual al camino y marcarla como visitada
        caminoActual.add(actual);  //AGREGO
        visitados.add(actual);     //AGREGO
        // Si llegamos a la sala de salida, verificamos si es el camino más largo
        if (actual == destino) { //condicion de corte
            if (caminoActual.size() > mejorSolucion.size()) {
                this.mejorSolucion.clear();
                this.mejorSolucion.addAll(caminoActual); // Guardar la mejor solución encontrada
            }
        } else {
            // Iterar sobre los vecinos de la sala actual
            Iterator<Integer> itAdy = grafo.obtenerAdyacentes(actual);
            while (itAdy.hasNext()) { //por cada posible decision
                Integer adyacente = itAdy.next();
                // Si el vértice no ha sido visitado
                if (!visitados.contains(adyacente)) { // Evitar ciclos
                    buscarCamino(adyacente, destino, caminoActual, visitados);
                }
            }
        }
        visitados.remove(actual);                                 //DESHAGO (misma altura que agrego)
        caminoActual.remove(caminoActual.size() - 1);       //DESHAGO (misma altura que agrego)
    }

    public static void main(String[] args) {
        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();
        Ejercicio1 ejercicio1 = new Ejercicio1(grafo);

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(8);

        grafo.agregarArco(1, 2, null);
        grafo.agregarArco(2, 3, null);
        grafo.agregarArco(3, 5, null);
        grafo.agregarArco(5, 8, null);
        grafo.agregarArco(4, 8, null);
        grafo.agregarArco(1, 4, null);

        ejercicio1.buscarCaminoMasLargo(1, 8);
        System.out.println("Camino más largo encontrado: " + ejercicio1.mejorSolucion);
    }
}

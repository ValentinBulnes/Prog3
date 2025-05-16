package ProgramacionIII.tp4_grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
//camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que el
//grafo de entrada es acíclico

public class Ejercicio4 {       //resuelvo utilizando DFS
        private static final String BLANCO = "Blanco";
        private static final String AMARILLO = "Amarillo";
        private static final String NEGRO = "Negro";

        private HashMap<Integer, String> estados;
        private List<Integer> mejorCamino;
        private int maxLongitud;
        private Grafo<Integer> grafo;
        private int verticeFin;

        public List<Integer> encontrarCaminoMasLargo(Grafo<Integer> grafo, int inicio, int fin) {
            this.grafo = grafo;
            this.verticeFin = fin;
            this.mejorCamino = new ArrayList<>();
            this.maxLongitud = -1;
            this.estados = new HashMap<>();

            // Inicializar todos los vértices como BLANCO (no visitados)
            Iterator<Integer> vertices = grafo.obtenerVertices();
            while (vertices.hasNext()) {
                estados.put(vertices.next(), BLANCO);
            }

            List<Integer> caminoActual = new ArrayList<>();
            caminoActual.add(inicio);
            estados.put(inicio, AMARILLO); // Marcamos como en proceso

            dfsVisit(inicio, caminoActual);

            return new ArrayList<>(mejorCamino);
        }

        private void dfsVisit(int actual, List<Integer> caminoActual) {
            // Si llegamos al vértice final, verificamos si es el camino más largo
            if (actual == verticeFin) {
                if (caminoActual.size() > maxLongitud) {
                    maxLongitud = caminoActual.size();
                    mejorCamino = new ArrayList<>(caminoActual);
                }
            }

            // Explorar vértices adyacentes
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                int vecino = adyacentes.next();
                String estado = estados.get(vecino);

                if (BLANCO.equals(estado)) {
                    estados.put(vecino, AMARILLO);
                    caminoActual.add(vecino);

                    dfsVisit(vecino, caminoActual);

                    // Backtracking
                    caminoActual.remove(caminoActual.size() - 1);
                    estados.put(vecino, BLANCO);
                }
            }

            estados.put(actual, NEGRO); // Marcamos como completamente procesado
        }

    public static void main(String[] args) {
        // Crear un grafo dirigido acíclico de ejemplo
        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();

        // Agregar vértices
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);

        // Agregar arcos (el String es la etiqueta, pero no lo usaremos en este caso)
        grafo.agregarArco(1, 3, null);
        grafo.agregarArco(1, 4, null);
        grafo.agregarArco(4, 3, null);
        grafo.agregarArco(3, 2, null);

        // Instanciar el resolver
        Ejercicio4 ej4 = new Ejercicio4();
        List<Integer> camino = ej4.encontrarCaminoMasLargo(grafo, 1, 2);

        System.out.println("Camino más largo encontrado: " + camino);
        System.out.println("Longitud del camino: " + camino.size());


    }
}
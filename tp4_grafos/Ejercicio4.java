package ProgramacionIII.tp4_grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
//camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que el
//grafo de entrada es acíclico

public class Ejercicio4 {       //resuelvo utilizando DFS

        private Grafo<Integer> grafo;
        private HashMap<Integer, String> colores;
        private List<Integer> mejorCamino;
        private int maxLongitud;

        public Ejercicio4(Grafo<Integer> grafo ) {
            this.grafo = grafo;
            this.colores = new HashMap<>();
            this.mejorCamino = new ArrayList<>();
            this.maxLongitud = -1;
        }

        public List<Integer> encontrarCaminoMasLargo(Integer inicio, Integer fin) {
            // Inicializar todos los vértices como BLANCO (no visitados)
            Iterator<Integer> itVertices = grafo.obtenerVertices();
            while (itVertices.hasNext()) {
                Integer vertice = itVertices.next();
                colores.put(vertice, "blanco");
            }

            List<Integer> caminoActual = new ArrayList<>();
            caminoActual.add(inicio);
            colores.put(inicio, "amarillo"); // Genero mi estado inicial, marco como en proceso

            dfsVisit(inicio, fin, caminoActual);

            return new ArrayList<>(mejorCamino);
        }

        private void dfsVisit(Integer actual,Integer fin, List<Integer> caminoActual) {
            // Si llegamos al vértice final, verificamos si es el camino más largo
            if (actual == fin) {
                if (caminoActual.size() > mejorCamino.size()) {
                    this.mejorCamino.clear();
                    this.mejorCamino.addAll(caminoActual);
                }
            } else {
                // Explorar vértices adyacentes
                Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(actual);
                while (itAdyacentes.hasNext()) { //por cada posible decision
                    Integer adyacente = itAdyacentes.next();

                    if (colores.get(adyacente).equals("blanco")) {
                        colores.put(adyacente, "amarillo"); // aplicaba los cambios
                        caminoActual.add(adyacente);

                        dfsVisit(adyacente, fin, caminoActual); // llamaba recursivamente

                        // Backtracking
                        colores.put(adyacente, "blanco");
                        caminoActual.removeLast();
                    }
                }
            }

            colores.put(actual, "negro"); // Marcamos como completamente procesado
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
        Ejercicio4 ej4 = new Ejercicio4(grafo);
        List<Integer> camino = ej4.encontrarCaminoMasLargo( 1, 2);

        System.out.println("Camino más largo encontrado: " + camino);
        System.out.println("Longitud del camino: " + camino.size());


    }
}
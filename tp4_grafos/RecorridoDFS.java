package ProgramacionIII.tp4_grafos;
import java.util.*;

//Ejercicio 2 Implementar los recorridos Depth-First-Search
// DFS es para busquedas profundas
/*
 * Usá DFS cuando:
 * Te interesa explorar caminos completos antes de probar otros.
 * Buscás ciclos en un grafo.
 * Querés ver si hay una ruta entre dos nodos (no la más corta).
 * Trabajás con problemas de backtracking (como laberintos, sudokus, etc).
 */
public class RecorridoDFS<T> {
    private static final String BLANCO = "Blanco";
    private static final String AMARILLO = "Amarillo";
    private static final String NEGRO = "Negro";

    private HashMap<Integer, String> visitados = new HashMap<>();
    private boolean tieneCiclos;

    // Complejidad Computacional O(|V| + |A|) con Listas de Adyacencia
    // |V| representa la cantidad de vértices y |A| la cantidad de aristas en el grafo.
    public void DFS(Grafo<T> grafo) {
        visitados = new HashMap<>();
        tieneCiclos = false;

        Iterator<Integer> verticesIterator = grafo.obtenerVertices();

        // Inicializar todos los vértices como no visitados "Blanco"
        while (verticesIterator.hasNext()) {
            visitados.put(verticesIterator.next(), BLANCO);
        }

        verticesIterator = grafo.obtenerVertices();

        // Iniciar DFS en cada vértice que aún no haya sido visitado
        while (verticesIterator.hasNext()) {
            int v = verticesIterator.next();
            if (BLANCO.equals(visitados.get(v))) {
                DFS_Visit(v, grafo);
            }
        }
    }

    private void DFS_Visit(int v, Grafo<T> grafo) {
        // Marcar el vértice como en proceso
        visitados.put(v, AMARILLO);

        Iterator<Integer> adyacentesIterator = grafo.obtenerAdyacentes(v);

        while (adyacentesIterator.hasNext()) {
            int adyacenteId = adyacentesIterator.next();
            String color = visitados.get(adyacenteId);
            // Si el vértice adyacente aún no ha sido visitado, llamada recursiva a explorarlo
            if (BLANCO.equals(color)) {
                DFS_Visit(adyacenteId, grafo);
            } else if (AMARILLO.equals(color)) {      /* Ejercicio 3 Implemente un algoritmo que determine si un grafo dirigido tiene algún ciclo. (no funciona para NO dirigidos) */
                tieneCiclos = true;
            }
        }

        // Una vez que se ha terminado de explorar todos los vecinos, marcar el vértice como terminado
        visitados.put(v, NEGRO);
    }

    public boolean tieneCiclos() {
        return tieneCiclos;
    }
}

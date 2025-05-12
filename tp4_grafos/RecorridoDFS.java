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

public class RecorridoDFS<T, G extends Grafo<T>> {

    private G grafo;
    private static final int BLANCO = 0; //No visitado
    private static final int AMARILLO = 1; // Visitado, pero con vecinos aún sin explorar (en proceso)
    private static final int NEGRO = 2; // Visitado completamente (terminado)
    private final HashMap<Integer, Integer> distancias = new HashMap<>();
    private int tiempo;

    public RecorridoDFS(G grafo) {
        this.grafo = grafo;
    }

    // Complejidad Computacional O(|V| + |A|) con Listas de Adyacencia
    // |V| representa la cantidad de vértices y |A| la cantidad de aristas en el grafo.
    public void DFS() {
        HashMap<Integer, Integer> color = new HashMap<Integer, Integer>();
        Iterator<Integer> verticesIterator = grafo.obtenerVertices();

        // Inicializar todos los vértices como no visitados
        while (verticesIterator.hasNext()) {
            color.put(verticesIterator.next(), BLANCO);
        }

        tiempo = 0;
        verticesIterator = grafo.obtenerVertices();

        // Iniciar DFS en cada vértice que aún no haya sido visitado
        while (verticesIterator.hasNext()) {
            Integer vertice = verticesIterator.next();
            if (color.get(vertice) == BLANCO) {
                DFS_Visit(vertice, color);
            }
        }
    }

    public void DFS_Visit(int vertice, HashMap<Integer, Integer> color) {
        // Marcar el vértice como en proceso
        color.put(vertice, AMARILLO);
        tiempo++;

        Iterator<Integer> vecinosIterator = grafo.obtenerAdyacentes(vertice);

        while (vecinosIterator.hasNext()) {
            Integer adyacente = vecinosIterator.next();

            // Si el vértice adyacente aún no ha sido visitado, llamada recursiva a explorarlo
            if (color.get(adyacente) == BLANCO) {
                DFS_Visit(adyacente, color);
            } else if (color.get(adyacente) == AMARILLO){
                /* Ejercicio 3 Implemente un algoritmo que determine si un grafo dirigido tiene algún ciclo. */
                // Si se encuentra un vértice que aún está en proceso, hay un ciclo
                // Un vértice en AMARILLO significa que estamos volviendo a un nodo que aún no ha sido terminado,
                // lo que indica la presencia de un ciclo en el grafo dirigido
                System.out.println("Existe un ciclo.");
            }
        }

        // Una vez que se ha terminado de explorar todos los vecinos, marcar el vértice como terminado
        color.put(vertice, NEGRO);

        // Guardar el tiempo de finalización del vértice
        distancias.put(vertice, tiempo);
    }
}

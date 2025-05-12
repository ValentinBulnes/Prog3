package ProgramacionIII.tp4_grafos;

import java.util.*;

public class RecorridoBFS<T, G extends Grafo<T>> {

    private G grafo;
    private static final int BLANCO = 0; // NO_VISITADO
    private static final int AMARILLO = 1; // VISITADO

    public RecorridoBFS(G grafo) {
        this.grafo = grafo;
    }

    public List<Integer> BFS() {
        // Inicializar vértices como NO_VISITADO
        HashMap<Integer, Integer> color = new HashMap<Integer, Integer>();
        Iterator<Integer> verticesIterator = grafo.obtenerVertices();

        while (verticesIterator.hasNext()) {
            color.put(verticesIterator.next(), BLANCO);
        }

        // Crear el ArrayList para almacenar el recorrido
        ArrayList<Integer> recorridoBFS = new ArrayList<>();

        // Recorrer cada vértice en el grafo
        verticesIterator = grafo.obtenerVertices();

        while (verticesIterator.hasNext()) {
            Integer vertice = verticesIterator.next();
            // Si el vértice no ha sido visitado, llamamos al método BFS_Visit
            if (color.get(vertice) == BLANCO) {
                BFS_Visit(vertice, color, recorridoBFS);
            }
        }

        return recorridoBFS;
    }

    public void BFS_Visit(int vertice, HashMap<Integer, Integer> color, ArrayList<Integer> recorrido) {
        color.put(vertice, AMARILLO); // Cambiar a VISITADO
        Queue<Integer> cola = new LinkedList<Integer>();
        cola.add(vertice); // Agregar el vértice a la cola

        while (!cola.isEmpty()) {
            // Obtener el vértice de la cola y recorrerlo
            int u = cola.poll();
            recorrido.add(u); // Agregar el vértice al recorrido

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(u); // Cambio de getAdyacentes a obtenerAdyacentes
            while (adyacentes.hasNext()) {
                Integer v = adyacentes.next();
                // Si el vértice adyacente no ha sido visitado, marcarlo como VISITADO
                // y agregarlo a la cola
                if (color.get(v) == BLANCO) {
                    color.put(v, AMARILLO); // Cambiar de NO_VISITADO a VISITADO
                    cola.add(v);
                }
            }
        }
    }
}

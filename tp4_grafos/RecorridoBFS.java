package ProgramacionIII.tp4_grafos;

import java.util.*;

public class RecorridoBFS<T> {

    private Queue<Integer> cola;
    private HashSet<Integer> visitados;

    public RecorridoBFS() {
        cola = new LinkedList<>();
    }

    public List<Integer> BFS(Grafo<T> grafo) {
        // Inicializar vértices como NO_VISITADO
        visitados = new HashSet<>();
        Iterator<Integer> verticesIt = grafo.obtenerVertices();

        // Crear el ArrayList para almacenar el recorrido
        ArrayList<Integer> recorridoBFS = new ArrayList<>();

        // Marcar todos como no visitados
        while (verticesIt.hasNext()) {
            Integer vertice = verticesIt.next();
            if (!visitados.contains(vertice)) {
                BFS_Visit(grafo, vertice, recorridoBFS);
            }
        }
        return recorridoBFS;
    }

    public void BFS_Visit(Grafo<T> grafo, Integer inicio, ArrayList<Integer> recorrido) {
        visitados.add(inicio);
        cola.add(inicio);
        recorrido.add(inicio);

        while (!cola.isEmpty()) {
            Integer verticeActual = cola.poll(); // Tomamos vértice x de la fila

            Iterator<Integer> adyacentesIt = grafo.obtenerAdyacentes(verticeActual);
            while (adyacentesIt.hasNext()) {
                Integer adyacente = adyacentesIt.next(); // Tomar y guardar el adyacente
                if (!visitados.contains(adyacente)){
                    visitados.add(adyacente);       // Marcar como VISITADO
                    cola.add(adyacente);           // Agregar a la fila F
                    recorrido.add(adyacente);
                }
            }
        }
    }
}

package ProgramacionIII.tp4_grafos;
import java.util.*;

public class Ejercicio5 {
    private Set<Integer> visitados;
    private Set<Integer> verticesConCamino;
    private int verticeDestino;

    public List<Integer> buscarCaminoDeUnVertice(Grafo<Integer> grafo, int vertice) {
        List<Integer> resultados = new ArrayList<>();
        resultados.add(vertice); // El vértice destino siempre está incluido

        Iterator<Integer> vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            int v = vertices.next();
            if (v != vertice && existeCaminoA(grafo, v, vertice, new HashSet<>())) {
                resultados.add(v);
            }
        }

        return resultados;
    }

    private boolean existeCaminoA(Grafo<Integer> grafo, int origen, int destino, Set<Integer> visitados) {

        visitados.add(origen);

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(origen);
        while (adyacentes.hasNext()) {
            int vecino = adyacentes.next();
            if (vecino == destino)
                return true;
            if (!visitados.contains(vecino) && existeCaminoA(grafo, vecino, destino, visitados))
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);

        grafo.agregarArco(1,2,null);
        grafo.agregarArco(2, 3, null);
        grafo.agregarArco(3, 4, null);
        grafo.agregarArco(1, 5, null);
        grafo.agregarArco(5, 6, null);
        grafo.agregarArco(2, 4, null);

        // Instanciar el resolver
        Ejercicio5 resolver = new Ejercicio5();

        // Buscar todos los vértices que tienen camino al vértice 4
        int verticeEvaluar = 4;
        List<Integer> verticesConCamino = resolver.buscarCaminoDeUnVertice(grafo, verticeEvaluar);

        // Mostrar resultados
        System.out.println("\nVértices desde los cuales existe camino a " + verticeEvaluar + ":");
        System.out.println(verticesConCamino);

    }
}

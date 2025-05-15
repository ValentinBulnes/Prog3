package ProgramacionIII.tp4_grafos;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {

    private HashMap<Integer, Vertice<T>> vertices;

    public GrafoDirigido() {
        this.vertices = new HashMap<>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (!vertices.containsKey(verticeId)) {
            vertices.put(verticeId, new Vertice<>(verticeId));
        } else {
            System.out.println("El vértice " + verticeId + " ya se encuentra agregado.");
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        if (vertices.containsKey(verticeId)) {
            vertices.remove(verticeId);
            // Eliminar el vértice de los conjuntos de adyacentes de los demás vértices
            for (Vertice<T> v : vertices.values()) {
                v.borrarArco(verticeId);
            }
        } else {
            System.out.println("El vértice " + verticeId + " no existe.");
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)) {
            Vertice<T> origen = vertices.get(verticeId1);
            origen.agregarArco(new Arco<>(verticeId1, verticeId2, etiqueta));
        } else {
            System.out.println("El grafo no contiene al menos uno de los vértices.");
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if (vertices.containsKey(verticeId1)) {
            vertices.get(verticeId1).borrarArco(verticeId2);
        } else {
            System.out.println("El grafo no contiene el vértice " + verticeId1);
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if (vertices.containsKey(verticeId1)) {
            return vertices.get(verticeId1).esAdyacente(verticeId2);
        }
        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if (vertices.containsKey(verticeId1)) {
            return vertices.get(verticeId1).getAdyacentes().get(verticeId2);
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    @Override
    public int cantidadArcos() {
        int totalArcos = 0;

        for (Vertice<T> v : vertices.values()) {
            totalArcos += v.getAdyacentes().size();
        }

        return totalArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        if (vertices.containsKey(verticeId)) {
            LinkedList<Integer> adyacentes = new LinkedList<>();
            for (Arco<T> arco : vertices.get(verticeId).getAdyacentes()) {
                adyacentes.add(arco.getVerticeDestino());
            }
            return adyacentes.iterator();
        }
        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<T>> todos = new LinkedList<>();
        for (Vertice<T> v : vertices.values()) {
            todos.addAll(v.getAdyacentes());
        }
        return todos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if (vertices.containsKey(verticeId)) {
            return vertices.get(verticeId).getAdyacentes().iterator();
        }
        return null;
    }
}

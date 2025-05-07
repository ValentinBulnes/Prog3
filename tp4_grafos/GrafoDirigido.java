package ProgramacionIII.tp4_grafos;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {
    // LISTA DE ADYACENCIA
    // relacione cada ID de vértice con su lista de adyacencia
    private List<Vertice<T>> vertices; // guardamos los id, no el Vertice?
    // private List<Arco<T>> adyacentes;

    public GrafoDirigido() {
        this.vertices = new ArrayList<>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        Vertice<T> v = new Vertice<>(verticeId);
        if (!vertices.contains(v)) {  // o(n)
            vertices.add(v);
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        // buscar su adyacente y sacar arcos que apunten tambien
        Vertice<T> v = vertices.get(verticeId);
        // y borrar arco
        for (Arco<T> arco : v.getAdyacentes()) {
            borrarArco(verticeId, arco.getVerticeDestino());
            borrarArco(arco.getVerticeDestino(), verticeId);
        }
        vertices.remove(v);
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);

         Vertice<T> v1 = vertices.get(verticeId1);
         v1.addAdyacente(arco);
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int cantidadVertices() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int cantidadArcos() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        // TODO Auto-generated method stub
        return null;
    }

}

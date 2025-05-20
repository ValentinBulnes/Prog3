package ProgramacionIII.tp4_grafos;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {

    private HashMap<Integer, LinkedList<Arco<T>>> grafo;
    protected int cantArcos;

    public GrafoDirigido() {
        this.grafo = new HashMap<>();
        cantArcos = 0;
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (!this.contieneVertice(verticeId))
            grafo.put(verticeId, new LinkedList<Arco<T>>());
    }

    @Override
    public void borrarVertice(int verticeId) {
        grafo.remove(verticeId);
        Iterator<Integer> vertices = this.obtenerVertices();
        while (vertices.hasNext()) {
            Integer vertice = vertices.next();
            this.borrarArco(vertice, verticeId);
            cantArcos--;
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2) && !existeArco(verticeId1, verticeId2)) {
            Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
            grafo.get(verticeId1).add(arco);
            cantArcos++;
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if(existeArco(verticeId1, verticeId2)) {
            LinkedList<Arco<T>> adyacentes = grafo.get(verticeId1);
            Iterator<Arco<T>> itAdy = adyacentes.iterator();
            while (itAdy.hasNext()) {
                Arco<T> arco = itAdy.next();
                if (arco.getVerticeDestino() == verticeId2) {
                    adyacentes.remove(arco);
                    cantArcos--;
                    return;
                }
            }
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return grafo.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        boolean existe = false;
        if (contieneVertice(verticeId1)) {
            LinkedList<Arco<T>> adyacentes = grafo.get(verticeId1);
            Iterator<Arco<T>> itAdy = adyacentes.iterator();
            while (!existe && itAdy.hasNext()) {
                Arco<T> arco = itAdy.next();
                if (arco.getVerticeDestino() == verticeId2) {
                    existe = true;
                }
            }
        }
        return existe;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if (contieneVertice(verticeId1)) {
            LinkedList<Arco<T>> adyacentes = grafo.get(verticeId1);
            Iterator<Arco<T>> itAdy = adyacentes.iterator();
            while (itAdy.hasNext()) {
                Arco<T> arco = itAdy.next();
                if (arco.getVerticeDestino() == verticeId2) {
                    return arco;
                }
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return grafo.size();
    }

    @Override
    public int cantidadArcos() {
        int totalArcos = 0;

        for (LinkedList<Arco<T>> arcos : grafo.values()) {
            totalArcos += arcos.size();
        }

        return totalArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return grafo.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        LinkedList<Integer> vecinos = new LinkedList<>();
        for (Arco<T> arco : grafo.get(verticeId)) {
            vecinos.add(arco.getVerticeDestino());
        }
        return vecinos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<T>> listaArcos = new LinkedList<>();

        for (LinkedList<Arco<T>> listaAdyacencia : grafo.values()) {
            listaArcos.addAll(listaAdyacencia);
        }

        return listaArcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        LinkedList<Arco<T>> adyacentes = new LinkedList<>();
        if (contieneVertice(verticeId)) {
            adyacentes = grafo.get(verticeId);
        }

        return adyacentes.iterator();
    }
}

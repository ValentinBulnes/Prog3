package ProgramacionIII.tp4_grafos;
import java.util.ArrayList;

public class Vertice<T> {
    //if (!vertices.contains(vertieId)) o(n)
    // no es buena opcion tener lista de arcos pq si me piden de Xvertice tendria q recorrer TODOS los arcos
    private int id;
    private ArrayList<Arco<T>> adyacentes;

    public Vertice(int id) {
        this.id = id;
        this.adyacentes = new ArrayList<>();
    }

    public boolean esAdyacente(int verticeId) {
        for (Arco<T> adyacente : adyacentes) {
            if (adyacente.getVerticeDestino() == verticeId) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Arco<T>> getAdyacentes() {
        return new ArrayList<>(this.adyacentes);
    }

    public void setAdyacentes() {
        this.adyacentes = new ArrayList<>();
    }

    public void agregarArco(Arco<T> arco) {
        adyacentes.add(arco);
    }

    public void borrarArco(int destino) {
        adyacentes.removeIf(arco -> arco.getVerticeDestino() == destino);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

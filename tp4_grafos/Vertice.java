package ProgramacionIII.tp4_grafos;

import java.util.ArrayList;
import java.util.Objects;

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
            if (adyacente.getVerticeDestino() == verticeId){
                return true;
            }
        }
        return false;
    }

    public void addAdyacente(Arco<T> arco) {
        this.adyacentes.add(arco);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Arco<T>> getAdyacentes() {
        return new ArrayList<>(adyacentes);
    }

    public void setAdyacentes(ArrayList<Arco<T>> adyacentes) {
        this.adyacentes = adyacentes;
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "id=" + id +
                ", adyacentes=" + adyacentes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice<?> vertice = (Vertice<?>) o;
        return id == vertice.id && Objects.equals(adyacentes, vertice.adyacentes);
    }
}

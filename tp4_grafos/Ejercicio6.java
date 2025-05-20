package ProgramacionIII.tp4_grafos;

import java.util.*;

public class Ejercicio6<T> {
    //Utilizando BFS
    private Queue<Integer> cola;
    private HashSet<Integer> visitados;
    private HashMap<Integer, Integer> padres; // Para reconstruir el camino
    private HashMap<Integer, Integer> distancias; // Para llevar la distancia desde el origen

    public Ejercicio6() {
        cola = new LinkedList<>();
        visitados = new HashSet<>();
        padres = new HashMap<>();
        distancias = new HashMap<>();
    }

    public List<Integer> caminoMasCorto(Grafo<T> grafo, Integer origen, Integer destino) {
        // Inicializar estructuras
        visitados.clear();
        padres.clear();
        distancias.clear();
        cola.clear();

        // Inicializar el origen
        visitados.add(origen);
        cola.add(origen);
        padres.put(origen, null);  //(vertice, vertice que lo descubrio) (clave,valor)
        distancias.put(origen, 0);

        boolean encontrado = false;

        while (!cola.isEmpty() && !encontrado) {
            Integer verticeActual = cola.poll();

            Iterator<Integer> adyacentesIt = grafo.obtenerAdyacentes(verticeActual);
            while (adyacentesIt.hasNext() && !encontrado) {
                Integer adyacente = adyacentesIt.next();

                if (!visitados.contains(adyacente)) {
                    visitados.add(adyacente);
                    padres.put(adyacente, verticeActual);
                    distancias.put(adyacente, distancias.get(verticeActual) + 1);
                    cola.add(adyacente);

                    if (adyacente.equals(destino)) {
                        encontrado = true;
                    }
                }
            }
        }
        return reconstruirCamino(destino);
    }

    private List<Integer> reconstruirCamino(Integer destino) {
        List<Integer> camino = new ArrayList<>();
        Integer actual = destino;

        while (actual != null) {
            camino.add(actual);  //voy agregando los vertices al camino en sentido opuesto, de destino a origen
            actual = padres.get(actual); //dame el valor que indica la clave "actual". te doy el vertice y dame quien lo descubrio
        }

        Collections.reverse(camino); //invierto la lista, ahora es de origen a destino
        return camino;
    }

    public static void main(String[] args) {
        // Crear el grafo
        Grafo<Integer> grafo = new GrafoNoDirigido<>();

        // Agregar vértices (nodos)
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);
        grafo.agregarVertice(8);
        grafo.agregarVertice(9);

        // Agregar aristas (conexiones entre nodos)
        grafo.agregarArco(1, 6, null);
        grafo.agregarArco(1, 9,null);
        grafo.agregarArco(6, 8,null);
        grafo.agregarArco(6, 2,null);
        grafo.agregarArco(9, 2,null);
        grafo.agregarArco(9, 4,null);
        grafo.agregarArco(2, 5,null);
        grafo.agregarArco(2, 7,null);
        grafo.agregarArco(5, 3,null);
        grafo.agregarArco(4, 5,null);

        // Definir origen y destino
        Integer origen = 1;
        Integer destino = 3;

        // Ejecutar el algoritmo
        Ejercicio6<Integer> buscador = new Ejercicio6<>();
        List<Integer> caminoMasCorto = buscador.caminoMasCorto(grafo, origen, destino);

        // Imprimir resultado
        if (caminoMasCorto.isEmpty()) {
            System.out.println("No hay camino entre " + origen + " y " + destino);
        } else {
            System.out.println("Camino más corto de " + origen + " a " + destino + ":");
            System.out.println(caminoMasCorto);
            System.out.println("Longitud: " + (caminoMasCorto.size() - 1) + " aristas");
        }
    }

}

package ProgramacionIII.tp4_grafos;

import java.util.*;

public class Ejercicio7 {

    private Grafo<?> grafo;
    private HashMap<Integer, String> colores;
    private Set<List<Integer>> soluciones;
    private final int VERTICE_LAS_FLORES = 5;
    private final int VERTICE_RAUCH = 6;

    public Ejercicio7(Grafo<?> grafo) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
        this.soluciones = new HashSet<>();
    }

    public Set<List<Integer>> encontrarTodosLosCaminos(Integer origen, Integer destino) {
        colores.clear();
        soluciones.clear();

        // Inicializar todos los vértices como "blanco" (no visitados)
        Iterator<Integer> itVert = grafo.obtenerVertices();
        while (itVert.hasNext()) {
            colores.put(itVert.next(), "blanco");
        }

        LinkedList<Integer> caminoActual = new LinkedList<>();
        caminoActual.add(origen);
        colores.put(origen, "amarillo");

        encontrarTodosLosCaminosRec(origen, destino, caminoActual);
        return soluciones;
    }

    private void encontrarTodosLosCaminosRec(Integer actual, Integer destino, LinkedList<Integer> caminoActual) {
        // Condicion de corte
        if (actual == destino) { // si se cumple corta la recursividad
            List<Integer> solucion = new LinkedList<>(caminoActual);
            soluciones.add(solucion);
        } else {
            Iterator<Integer> itAdy = grafo.obtenerAdyacentes(actual);
            while (itAdy.hasNext()) {  // por cada posible decision
                Integer adyacente = itAdy.next();

                // Determinar si el tramo actual -> adyacente ES el tramo cortado
                boolean esTramoCortado = (actual == VERTICE_LAS_FLORES && adyacente == VERTICE_RAUCH) ||
                        (actual == VERTICE_RAUCH && adyacente == VERTICE_LAS_FLORES);

                // Solo procesar si el tramo NO está cortado
                if (!esTramoCortado) {
                    // Y si el vértice adyacente no ha sido visitado en el camino actual (es "blanco")
                    if (colores.get(adyacente).equals("blanco")) {
                        colores.put(adyacente, "amarillo"); // Marcar como visitado en este camino
                        caminoActual.add(adyacente);       // Agregar al camino actual
                        encontrarTodosLosCaminosRec(adyacente, destino, caminoActual); // Explorar recursivamente
                        //Backtracking: revertir los cambios para explorar otras ramas. Cuando se llega a destino o cuando no quedan adyacentes por visitar
                        colores.put(adyacente, "blanco");   // deshacer los cambios
                        caminoActual.removeLast();          // Quitar del camino actual
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        // Crear grafo no dirigido (simula rutas de Buenos Aires)
        GrafoNoDirigido<String> grafo = new GrafoNoDirigido<>();

        // Agregar vértices (ciudades)
        for (int i = 1; i <= 8; i++) {
            grafo.agregarVertice(i);
        }

        // Agregar arcos (rutas bidireccionales con etiqueta null)
        grafo.agregarArco(1, 3, null);  // Buenos Aires <-> Brandsen
        grafo.agregarArco(1, 5, null);  // Buenos Aires <-> Las Flores
        grafo.agregarArco(3, 4, null);  // Brandsen <-> Belgrano
        grafo.agregarArco(7, 2, null);  // Ayacucho <-> Tandil
        grafo.agregarArco(5, 8, null);  // Las Flores <-> Azul
        grafo.agregarArco(4, 7, null);  // Belgrano <-> Ayacucho
        grafo.agregarArco(8, 6, null);  // Azul <-> Rauch
        grafo.agregarArco(4, 6, null);  // Belgrano <-> Rauch
        grafo.agregarArco(6, 2, null);  // Rauch <-> Tandil
        grafo.agregarArco(5, 6, null);  // Las Flores <-> Rauch

        // Buscar caminos alternativos
        Ejercicio7 buscador = new Ejercicio7(grafo);
        Set<List<Integer>> caminos = buscador.encontrarTodosLosCaminos(1, 2);

        // Mostrar resultados
        System.out.println("Caminos alternativos de Buenos Aires (1) a Tandil (2) considerando que en el tramo Las Flores(5)-Rauch está cortado :");
        for (List<Integer> camino : caminos) {
            System.out.println(camino);
        }
    }
}
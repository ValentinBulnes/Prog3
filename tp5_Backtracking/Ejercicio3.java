package ProgramacionIII.tp5_Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio3 {
    private int M = 10; //Objetivo
    private List<List<Integer>> soluciones;

    public List<List<Integer>> backSubconjuntos(List<Integer> conjunto) {
        soluciones = new ArrayList<>();
        ArrayList<Integer> auxiliar = new ArrayList<>();
        backSubconjuntos(conjunto, 0, 0, auxiliar);
        return soluciones;
    }

    private void backSubconjuntos(List<Integer> conjunto, Integer actual, Integer suma, ArrayList<Integer> auxiliar) {
        if (suma == M) { //Caso Base, condicion de corte
            soluciones.add(new ArrayList<>(auxiliar));
        } else {
            for (Integer i = actual; i < conjunto.size(); i++) {
                auxiliar.add(conjunto.get(i));

                if (suma + conjunto.get(i) <= M) { //Poda
                    backSubconjuntos(conjunto, i + 1, suma + conjunto.get(i), auxiliar); //Recursion
                }
                auxiliar.remove(auxiliar.size() - 1); //Backtrack
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> conjunto = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Ejercicio3 ej3 = new Ejercicio3();
        List<List<Integer>> resultado = ej3.backSubconjuntos(conjunto);

        System.out.println("Subconjuntos que suman 10: " + resultado);
    }
}
package ProgramacionIII.tp5_Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio4 {
    private List<List<List<Integer>>> soluciones;

    public List<List<List<Integer>>> backSubconjuntos(List<Integer> conjunto) {
        soluciones = new ArrayList<>();
        List<Integer> subconjunto1 = new ArrayList<>();
        List<Integer> subconjunto2 = new ArrayList<>();

        int sumaTotal = calcularSumaTotal(conjunto);

        if (sumaTotal % 2 != 0) {
            return soluciones; // No hay solución si la suma es impar
        }

        backtrack(conjunto, 0, subconjunto1, subconjunto2, 0, 0, sumaTotal / 2);
        return soluciones;
    }

    private void backtrack(List<Integer> conjunto, int index, List<Integer> subconjunto1, List<Integer> subconjunto2, int suma1, int suma2, int objetivo) {
        if (index == conjunto.size()) {
            if (suma1 == objetivo && suma2 == objetivo) { //Caso Base, condicion de corte
                soluciones.add(List.of(new ArrayList<>(subconjunto1), new ArrayList<>(subconjunto2)));
            }
        } else {
            // Poda: si alguna suma excede el objetivo
            if (suma1 > objetivo || suma2 > objetivo) {
                return;
            }

            int num = conjunto.get(index);

            // Opción 1: agregar al primer subconjunto (si no excede el objetivo)
            subconjunto1.add(num);
            backtrack(conjunto, index + 1, subconjunto1, subconjunto2, suma1 + num, suma2, objetivo);
            subconjunto1.remove(subconjunto1.size() - 1);


            // Opción 2: agregar al segundo subconjunto (si no excede el objetivo)
            subconjunto2.add(num);
            backtrack(conjunto, index + 1, subconjunto1, subconjunto2, suma1, suma2 + num, objetivo);
            subconjunto2.remove(subconjunto2.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> conjunto = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Ejercicio4 ej4 = new Ejercicio4();
        List<List<List<Integer>>> resultado = ej4.backSubconjuntos(conjunto);

        int objetivo = ej4.calcularSumaTotal(conjunto) / 2;
        System.out.println("Subconjuntos que suman " + objetivo + ":\n");

        int contador = 1;
        for (List<List<Integer>> par : resultado) {
            System.out.println("Solución " + contador + ":");
            System.out.println("  Subconjunto 1: " + par.get(0));
            System.out.println("  Subconjunto 2: " + par.get(1));
            System.out.println();
            contador++;
        }
    }

    private int calcularSumaTotal(List<Integer> conjunto) {
        int suma = 0;
        for (int num : conjunto) {
            suma += num;
        }
        return suma;
    }
}

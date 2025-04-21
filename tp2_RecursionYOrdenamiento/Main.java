package ProgramacionIII.tp2_RecursionYOrdenamiento;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        //ejercicio 1
        int [] array = {1,2,3,4,5,6,7,8,9,10};
        if(estaOrdenado(array,0)){
            System.out.println("esta ordenado");
        }
        else{
            System.out.println("no esta ordenado");
        }

        // Ejercicio 2
        int[] C = {1, 4, 5, 8, 10, 15, 19, 23, 25, 26, 27, 33, 40, 41, 44, 48, 50, 51, 55, 58};

        // ¿El número 42 está en el array?
        int X = 42, inicio = 0, fin = C.length - 1;
        System.out.println("========== Ejercicio 2 ==========");
        System.out.println("Esta en la posicion: " + BusquedaBinariaRecursiva(C, X, inicio, fin));

        // Ejercicio 3
        int xNotacionDecimal = 26;

        System.out.println("========== Ejercicio 3 ==========");
        System.out.println(conversorDecimalABinario(xNotacionDecimal));

        //Ejercicio 4
        ArrayList<Integer> D = new ArrayList<>();
        int N = 6;

        fibonacci(D, N);

        System.out.println("========== Ejercicio 4 ==========");
        System.out.println(D);

        //Ejercicio 5
        int [] A = {-3,-1,0,2,4,6,10};
        System.out.println("========== Ejercicio 5 ==========");
        System.out.println(valorIgualPosicion(A,0));

        //Ejercicio 6
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("========== Ejercicio 6 ==========");
        System.out.println("Arreglo original:");
        imprimirArreglo(arr);

        selectionSort(arr);
        //bubbleSort(arr);

        System.out.println("\nArreglo ordenado:");
        imprimirArreglo(arr);



    }

    /* Ejercicio 1
   Implemente un algoritmo recursivo que determine si un arreglo de tamaño N está ordenado y
   responda:

   1. ¿Qué complejidad O tiene? (La complejidad en el peor caso)
   La complejidad en el peor caso es O(N), donde N es el tamaño del arreglo.
   Esto se debe a que el algoritmo recursivo recorre el arreglo una vez,
   realizando una comparación por cada llamada recursiva.

   2. ¿Trae algún problema hacerlo recursivo? ¿Cuál?
   La recursión crea una pila de llamadas, lo que significa que para arreglos grandes,
   podríamos agotar la pila de llamadas (desbordamiento de pila).
   Esto puede causar un StackOverflowError en Java si el tamaño del arreglo es demasiado grande.

   3. ¿Qué cambiaría si la estructura fuera una lista en lugar de un arreglo?
   Al usar una lista, podríamos tener una ligera penalización de desempeño en comparación con los arreglos,
   ya que las listas en Java no permiten acceso directo a los elementos por índice de manera tan eficiente como los arreglos. */
    public static boolean estaOrdenado(int [] array, int n){//n es inicio del arreglo debe valer cero al principio siempre
        if (n >= array.length - 1) {
            return true;
        }

        if(array[n] > array[n + 1]){
            return false;
        }

        return estaOrdenado(array, n + 1);
    }

    /* Ejercicio 2
    En el peor de los casos la complejidad es O(log₂ n) */
    public static int BusquedaBinariaRecursiva(int[] A, int X, int inicio, int fin){
        int medio = (inicio + fin) / 2;
        if (inicio>fin)
            return -1;
        else {
            if (X > A[medio] )
                return BusquedaBinariaRecursiva(A, X, medio+1, fin);
            if (X < A[medio] )
                return BusquedaBinariaRecursiva(A, X, inicio, medio-1);
            else return medio;
        }
    }

    /* Ejercicio 3
    Complejidad es O(log X) siendo X el número a convertir */
    public static ArrayList<Integer> conversorDecimalABinario(int X) {
        ArrayList<Integer> xNotacionBinaria = new ArrayList<>();

        convertirRecursivo(xNotacionBinaria, X);
        Collections.reverse(xNotacionBinaria);
        return xNotacionBinaria;
    }

    private static void convertirRecursivo(ArrayList<Integer> resultado, int X) {
        if (X < 2) {
            resultado.add(X);
        } else {
            resultado.add(X % 2);
            X = X / 2;

            convertirRecursivo(resultado, X);
        }
    }

    //ejercicio 4
    public static void fibonacci(ArrayList<Integer> D, int N){
        if (D.size() == N) {
            return;
        }

        if (D.size() == 0) {
            D.add(0);
        }
        else if (D.size() == 1) {
            D.add(1);
        }
        else {
            D.add(D.get(D.size() - 1) + D.get(D.size() - 2));
        }
        fibonacci(D,N);
    }

    //Ejercicio 5
    public static boolean valorIgualPosicion(int[] A, int pos){
        boolean existe = false;

        if (existe || pos == A.length - 1) {
            return existe;
        }
        else {
            if(A[pos] == pos){
                existe = true;
                return existe;
            }
        }
        return valorIgualPosicion(A,pos+1);
    }

    //Ejercicio 6
    // O(n²) ordenamiento por seleccion
    public static void selectionSort(int[] arr){
        int n = arr.length;

        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    // O(n²) ordenamieto por burbujeo
    public static void bubbleSort(int[] A){
        int i, j, aux;
        for ( i=0; i < A.length - 1; i++)
            for ( j=0; j < A.length-i-1; j++)
        if (A[ j ] > A[ j+1 ]) {
            aux = A[ j+1 ];
            A[ j+1 ] = A[ j ];
            A[ j ] = aux;
        }

    }

    // QuickSort -> No requiere memoria adicional! MergeSort si
    // Complejidad en el peor de los casos O(n²)
    public static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            // Encontrar el índice de partición
            int pivotIndex = partition(A, low, high);

            // Llamadas recursivas para las dos sublistas
            quickSort(A, low, pivotIndex - 1); // Sublista izquierda
            quickSort(A, pivotIndex + 1, high); // Sublista derecha
        }
    }

    // Método para realizar la partición del arreglo
    public static int partition(int[] A, int low, int high) {
        // Tomar el último elemento como pivote
        int pivot = A[high];
        int i = low - 1;

        // Reordenar el arreglo poniendo los menores que el pivote a la izquierda
        // y los mayores a la derecha
        for (int j = low; j < high; j++) {
            if (A[j] <= pivot) {
                i++;
                // Intercambiar arr[i] y arr[j]
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }

        // Intercambiar el pivote con el elemento en arr[i + 1]
        int temp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = temp;

        // Retornar el índice del pivote
        return i + 1;
    }







    // Método auxiliar para imprimir el arreglo
    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

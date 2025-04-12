package ProgramacionIII.tp1;

public class Main {
    public static void main(String[] args) {
        MySimpleLinkedList<Integer> lista = new MySimpleLinkedList<>();

        lista.insertFront(1); //pos3
        lista.insertFront(2); //pos2
        lista.insertFront(3); //pos1
        lista.insertFront(4); //pos0

//        System.out.println(lista);
//        System.out.println(lista.isEmpty());
//        System.out.println(lista.size());
//
//        lista.extractFront();
//        System.out.println(lista);
//        System.out.println(lista.size());

        System.out.println(lista.get(4));
    }
}

package ProgramacionIII.tp1;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MySimpleLinkedList<Integer> myList = new MySimpleLinkedList<Integer>();

        myList.insertFront(22); //pos4
        myList.insertFront(12); //pos3
        myList.insertFront(1); //pos2
        myList.insertFront(8); //pos1
        myList.insertFront(7); //pos0

        Iterator<Integer> it = myList.iterator();

        /*
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        */

        for (Integer i : myList) {
            System.out.println(i);
        }


        // System.out.println(myList);
        // System.out.println(myList.isEmpty());
        // System.out.println(myList.size());

        // myList.extractFront();
        // System.out.println(myList);
        // System.out.println(myList.size());

        // System.out.println(myList.indexOf(8));
    }
}

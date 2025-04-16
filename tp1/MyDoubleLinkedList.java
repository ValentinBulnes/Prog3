package ProgramacionIII.tp1;

import java.util.Iterator;
import java.util.Objects;

public class MyDoubleLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private NodeDouble<T> first;
    private NodeDouble<T> last;
    private int size; // Para mantener el tamaño en O(1)

    public MyDoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void insertFront(T info) {
        NodeDouble<T> nuevo = new NodeDouble<>(info, this.first, null);

        if (this.first == null) {
            this.last = nuevo; // Si la lista estaba vacía, first y last apuntan al mismo nodo
        } else {
            this.first.setPrev(nuevo);
        }

        this.first = nuevo;
        this.size++;
    }

    public void insertLast(T info) {
        NodeDouble<T> nuevo = new NodeDouble<>(info, null, this.last);

        if (this.last == null) {
            this.first = nuevo; // Si la lista estaba vacía
        } else {
            this.last.setNext(nuevo);
        }

        this.last = nuevo;
        this.size++;
    }

    public T extractFront() {
        if (this.first == null) {
            return null;
        }

        T info = this.first.getInfo();
        this.first = this.first.getNext();

        if (this.first != null) {
            this.first.setPrev(null);
        } else {
            this.last = null; // Si la lista queda vacía
        }

        this.size--;
        return info;
    }

    public T extractLast() {
        if (this.last == null) return null;

        T info = this.last.getInfo();
        this.last = this.last.getPrev();

        if (this.last != null) {
            this.last.setNext(null);
        } else {
            this.first = null; // Si la lista queda vacía
        }

        this.size--;
        return info;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public int size() {
        return this.size;
    }

    public int indexOf(T info) {
        int index = 0;
        NodeDouble<T> tmp = this.first;

        while (tmp != null) {
            if (Objects.equals(tmp.getInfo(), info)) {
                return index;
            }

            tmp = tmp.getNext();
            index++;
        }

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        NodeDouble<T> actual = this.first;

        while (actual != null) {
            sb.append(actual.getInfo());
            if (actual.getNext() != null) {
                sb.append(" <-> ");
            }
            actual = actual.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyDoubleListIterator<T>(this.first);
    }
}

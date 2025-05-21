package ProgramacionIII.tp1_EstructurasDeDatos;

import java.util.Iterator;

public class MySimpleLinkedList<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> first;
	private Node<T> last;
	private int size;

	public MySimpleLinkedList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	public void insertarOrdenado(T info) {
		Node<T> newNode = new Node<T>(info,null);

		if (first == null || info.compareTo(first.getInfo())<0) {
			this.insertFront(newNode.getInfo());
		}
		else {
			Node<T> aux = first;

			while (aux.getNext()!=null && info.compareTo(aux.getNext().getInfo())>=0) {
				aux = aux.getNext();
			}

			// Actualizar last si el nuevo nodo es el último
			if (newNode.getNext() == null) {
				last = newNode;
			}

			// Solo incrementar size si no se llamó a insertFront()
			this.size++;
		}
	}

	
	public void insertFront(T info) {  // O(1)
		Node<T> tmp = new Node<T>(info,null);
		tmp.setNext(this.first);
		this.first = tmp;
		if (size == 0) {
			last = first;
		}
		size++;  // incremento el tamaño de la lista en 1
	}

	public void insertLast(T info) {  // O(1)}
		if (size == 0) {
			this.insertFront(info);
		}else {
			last.setNext(new Node<T>(info,null));
			size++;
			last = last.getNext();
		}
	}
	
	public T extractFront() {
		if(this.isEmpty()) {
			return null;
		}
		Node<T> tmp = this.first;
		this.first = tmp.getNext();
		if(this.first == null) { //si al pasar al siguiente es null, el ultimo tambien es null
			this.last = null;
		}
		this.size = this.size - 1;
		return tmp.getInfo();
	}

	public boolean isEmpty() {
		if(this.first == null) {
			return true;
		}
		return false;
	}
	
	public T get(int index) {     // O(n)
		if (this.isEmpty() || index < 0 || index >= this.size) {  //si la posicion no existe en la lista devuelvo null
			return null;
		}
		Node<T> tmp = this.first;
		for(int i = 0; i < index; i++) {
			tmp = tmp.getNext();
		}
		return tmp.getInfo();
	}
	
	public int size() {
		return this.size;
	}

	public int indexOf(T element) {
		Node<T> tmp = this.first;
		for(int i = 0; i < this.size; i++) {
			if(tmp.getInfo().equals(element)) {
				return i;
			}
			tmp = tmp.getNext();
		}
		return -1;
	}


	
	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "[]";
		}
		String result = "[";
		Node<T> current = this.first;

		while (current != null) {
			result += current.getInfo();
			current = current.getNext();
			if (current != null) {
				result += ", ";
			}
		}

		return result + "]";
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator<>(this.first);
	}
}

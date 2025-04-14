package ProgramacionIII.tp1;

import java.util.Iterator;

public class MySimpleLinkedList<T> implements Iterable<T> {

	private Node<T> first;
	private int size;


	public MySimpleLinkedList() {
		this.first = null;
		this.size = 0;
	}
	
	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info,null);
		tmp.setNext(this.first);
		this.first = tmp;
		this.size = this.size +1;
	}
	
	public T extractFront() {
		if(this.isEmpty()) {
			return null;
		}
		Node<T> tmp = this.first;
		this.first = tmp.getNext();
		this.size = this.size - 1;
		return tmp.getInfo();
	}

	public boolean isEmpty() {
		if(this.first == null) {
			return true;
		}
		return false;
	}
	
	public T get(int index) {
		if(this.isEmpty() || index < 0 || index >= this.size) {
			return null;
		}
		if(index == 0) {
			return this.first.getInfo();
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

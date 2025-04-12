package ProgramacionIII.tp1;

public class MySimpleLinkedList<T> {
	
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
		// TODO
		return null;
	}

	public boolean isEmpty() {
		// TODO
		return false;
	}
	
	public T get(int index) {
		// TODO
		return null;
	}
	
	public int size() {
		return this.size;
	}
	
	@Override
	public String toString() {
		// TODO
		return "";
	}
	
}

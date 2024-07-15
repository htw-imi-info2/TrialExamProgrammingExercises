package assignment_01_list;

/**
 * A List Node.
 * Nothing to do here.
 *
 */
public class Node<E> {
	   protected E data;
	   protected Node<E> next;
	  
	   public Node (E data, Node<E> next) { 
	     this.data = data; 
	     this.next = next;
	   }

	public E getData() {
		return data;
	}

	public Node<E> getNext() {
		return next;
	}
}

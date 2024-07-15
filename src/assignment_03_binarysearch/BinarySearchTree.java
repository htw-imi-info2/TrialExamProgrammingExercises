package assignment_03_binarysearch;

/**
 * Implement the find/search within the Binary Tree.
 * The Binary Tree is implemented in the class Tree,
 * all you have to do is give an implementation for find() below. 
 * @author kleinen
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchTree<K extends Comparable<K>,V> extends Tree<K,V>{

	@Override
	public Node<K,V> find(K key) {
		
		// all you know about the keys is that they implement the
		// comparable interface - thus:
		
		// int c = key1.compareTo(key2);
		// c == 0 => key1 and key2 are equal
		// c < 0  => key1 < key2
		// c > 0  => key2 > key2

		// Start at the root....

		Node<K,V> node = root;

		return null;
	}

}

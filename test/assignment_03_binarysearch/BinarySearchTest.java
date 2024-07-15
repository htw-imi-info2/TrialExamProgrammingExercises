package assignment_03_binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchTest {
	BinarySearchTree<String, Object> tree;

	@BeforeEach
	public void setUp() throws Exception {
		tree = new BinarySearchTree<String, Object>();
		tree.insert("b");
		tree.insert("c");
		tree.insert("a");
	}

	@Test
	public void testFind() {
		assertEquals("a, b, c", tree.toString());
		Node<String, Object> a = tree.find("a");
		Node<String, Object> b = tree.find("b");
		Node<String, Object> c = tree.find("c");

		assertEquals(b, a.parent);
		assertEquals(b, c.parent);
		assertEquals(null, b.parent);
	}

	@Test
	public void findNonExistent() {
		Node<String, Object> d = tree.find("d");
		assertEquals(null, d);
	}

	@Test
	public void testFindInMidTree() {
		String[] alphabet = "a b c d e".split(" ");
		for (int i = 0; i < alphabet.length; i++) {
			tree.insert(alphabet[i]);
		}
		String[] searchFor = "a b c d e".split(" ");
		for (String key : searchFor) {
			Node<String, Object> foundNode = tree.find(key);
			assertEquals(key, foundNode.getKey());
		}
	}
	@Test
	public void testFindInBiggerTree() {
		String[] alphabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" ");
		for (int i = 0; i < alphabet.length; i++) {
			tree.insert(alphabet[i]);
		}
		String[] searchFor = "p a z".split(" ");
		for (String key : searchFor) {
			Node<String, Object> foundNode = tree.find(key);
			assertEquals(key, foundNode.getKey());
		}
	}

}

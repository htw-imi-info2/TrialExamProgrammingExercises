package assignment_03_binarysearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;


public class BinarySearchTest {

	BinarySearchTree<String, Object> tree;

	@BeforeEach
	public void setUp() {
		tree = new BinarySearchTree<>();
		tree.insert("b");
		tree.insert("c");
		tree.insert("a");
	}

	@ParameterizedTest(name = "{3}")
	@MethodSource("allTestCases")
	void testCases(String[] insertKeys, String[] searchKeys, String[] expectedParents, String description) {
		BinarySearchTree<String, Object> tree = new BinarySearchTree<>();
		for (String key : insertKeys) {
			tree.insert(key);
		}

		for (int i = 0; i < searchKeys.length; i++) {
			String key = searchKeys[i];
			Node<String, Object> node = tree.find(key);
			String actualParent = (node == null || node.parent == null) ? null : node.parent.getKey();
			assertEquals(expectedParents[i], actualParent);
		}
	}

	private static Stream<Arguments> testCases() {
		int limit = 1; 	// Just the first test case to start
		// limit = 2; 	// all up to 3 elements
		// limit = 3; 	// all up to 5 elements
		// limit = 4; 	// all

		return allTestCases().limit(limit);
	}


	private static Stream<Arguments> allTestCases() {
		return Stream.of(
				// === non-existent node ===
				Arguments.of(
						new String[]{"b", "c", "a"},
						new String[]{"d"},
						new String[]{null},
						"Search for non-existent key 'd'"
				),

				// === three-node tree ===
				Arguments.of(
						new String[]{"b", "c", "a"},       	// insert
						new String[]{"a", "b", "c"},       				// search
						new String[]{"b", null, "b"},      				// expected parents
						"Basic tree: verify parent links for a, b, c"
				),

				// === five-node tree ===
				Arguments.of(
						new String[]{"a", "b", "c", "d", "e"},
						new String[]{"a", "b", "c", "d", "e"},
						new String[]{null, "a", "b", "c", "d"},
						"Small tree: left-to-right insertions"
				),

				// === large tree ===
				Arguments.of(
						"a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" "),
						new String[]{"p", "a", "z"},
						new String[]{"o", "b", "y"},  // Adjust depending on tree structure
						"Large tree: search for a, p, and z"
				)
		);
	}
}

package assignment_03_binarysearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import settings.TestParameter;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BinarySearchTreeTest {

    @ParameterizedTest(name = "{4} {1}")
    @MethodSource("selectedTestCases")
    void testBinaryTreeSearch(String[] insertKeys, String key, String expectedKey, String expectedParent, String description) {
        BinarySearchTree<String, Object> tree = new BinarySearchTree<>();
        for (String k : insertKeys) {
            tree.insert(k);
        }


        Node<String, Object> node = tree.find(key);
        String actualParent = (node == null || node.parent == null) ? null : node.parent.getKey();
        if (expectedKey == null)
            assertEquals(null,node);
        else
            assertEquals(key, node.getKey());
        // check parent to ensure that the correct node was found
        assertEquals(expectedParent, actualParent);

    }

    private static Stream<Arguments> selectedTestCases() {
        if (TestParameter.ALWAYS_RUN_ALL_TESTS)
            return allTestCases();
        else {
            int limit = 1;    // empty tree
             //	limit = 3; 	   // one element tree
             //	limit = 7;  	// Basic tree
            //	limit = 13; 	// five-node tree
            //	limit = 19; 	// balanced seven
            //	limit = 23; 	// all

            return allTestCases().limit(limit);
        }
    }


    private static Stream<Arguments> allTestCases() {
        return Stream.of(

                // === empty tree ===
                Arguments.of(new String[]{}, "d", null, null, "Search in empty tree - "),

                // === one element tree ===
                Arguments.of(new String[]{"x"}, "x", "x", null, "one element tree - "),
                Arguments.of(new String[]{"x"}, "y", null, null, "one element tree - "),

                // === non-existent node ===
                Arguments.of(new String[]{"b", "c", "a"}, "d", null, null, "Search for non-existent key "),

                // === three-node tree ===
                Arguments.of(new String[]{"b", "c", "a"},"a","a", "b", "Basic tree: verify parent links for"),      // insert
                Arguments.of(new String[]{"b", "c", "a"},"b","b", null, "Basic tree: verify parent links for"),      // insert
                Arguments.of(new String[]{"b", "c", "a"},"c","c", "b", "Basic tree: verify parent links for"),      // insert


                // === five-node tree ===
                Arguments.of(new String[]{"a", "b", "c", "d", "e"},"a","a",null, "five-node tree - searching for "),
                Arguments.of(new String[]{"a", "b", "c", "d", "e"},"b","b","a", "five-node tree - searching for "),
                Arguments.of(new String[]{"a", "b", "c", "d", "e"},"c","c","b", "five-node tree - searching for "),
                Arguments.of(new String[]{"a", "b", "c", "d", "e"},"d","d","c", "five-node tree - searching for "),
                Arguments.of(new String[]{"a", "b", "c", "d", "e"},"e","e","d", "five-node tree - searching for "),
                Arguments.of(new String[]{"a", "b", "c", "d", "e"},"g",null, null, "five-node tree - not in tree / searching for "),

                // === balanced seven-node tree ===
                Arguments.of(new String[]{"d", "b", "a", "c", "f", "e", "g"},"a","a","b", "balanced seven-node tree - searching for "),
                Arguments.of(new String[]{"d", "b", "a", "c", "f", "e", "g"},"d","d",null,"balanced seven-node tree - searching for "),
                Arguments.of(new String[]{"d", "b", "a", "c", "f", "e", "g"},"b","b","d", "balanced seven-node tree - searching for "),
                Arguments.of(new String[]{"d", "b", "a", "c", "f", "e", "g"},"e","e","f", "balanced seven-node tree - searching for "),
                Arguments.of(new String[]{"d", "b", "a", "c", "f", "e", "g"},"g","g","f", "balanced seven-node tree - searching for "),
                Arguments.of(new String[]{"d", "b", "a", "c", "f", "e", "g"},"f","f","d", "balanced seven-node tree - searching for "),



                // === large tree ===
                Arguments.of("a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" "),"p","p","o","Large tree: search for "),
                Arguments.of("a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" "),"a","a", null,"Large tree: search for "),
                Arguments.of("a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" "),"z","z","y","Large tree: search for "));

    }
}

package assignment_01_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import settings.TestParameter;
import static org.junit.jupiter.api.Assertions.*;


public class ListTest {
	List<String> list;

	@BeforeEach
	public void setup() {
		list = new List<>();
	}

	@ParameterizedTest(name = "{index}: {3}")
	@MethodSource("selectedTestCases")
	void testGetIndex(String[] itemsToAdd, String target, int expectedIndex, String description) {
		for (String item : itemsToAdd) {
			list.add(item);
		}
		int actual = list.getIndex(target);
		assertEquals(expectedIndex, actual, "Failed test: " + description);
	}

	private static Stream<Arguments> selectedTestCases() {
		if (TestParameter.ALWAYS_RUN_ALL_TESTS)
			return allTestCases();
		else {
			int limit = 1;    // Just the first test case to start (Empty List)
			//limit = 2;   	// all up to 1 element
			//limit = 4;   	// empty, one, middle, last elements
			//limit = 5;  	// all
			limit = 5;
			return allTestCases().limit(limit);
		}
	}

	private static Stream<Arguments> allTestCases() {
		return Stream.of(
				// === Empty list ===
				Arguments.of(
						new String[]{},     // no elements
						"a",                // search target
						-1,                 // expected result
						"Empty list: getIndex('a') should return -1"
				),

				// === One element ===
				Arguments.of(new String[]{"a"}, "a", 1, "One element list: getIndex('a') should return 1"),
				Arguments.of(new String[]{"a"}, "b", -1, "one element, not in list"),
				Arguments.of(new String[]{"b","a"}, "a", 1, "two elements, first element"),
				Arguments.of(new String[]{"b","a"}, "b", 2, "two elements, second element"),
				Arguments.of(new String[]{"b","a"}, "c", -1, "two elements, not in list"),
				Arguments.of(new String[]{"c","b","a"}, "d", -1, "three elements, not in list"),
				Arguments.of(new String[]{"c","b","a"}, "b", 2, "three elements,  in list"),
				Arguments.of(new String[]{"c","b","a"}, "c", 3, "three elements,  in list"),

				// === Middle element ===
				Arguments.of(
						new String[]{"e", "d", "c", "b", "a"},
						"d",
						4, // from the back!
						"Middle element: getIndex('d') should return 4"
				),

				// === Last element ===
				Arguments.of(
						new String[]{"e", "d", "c", "b", "a"},
						"e",
						5,
						"Last element: getIndex('e') should return 5"
				),

				// === Element not in list ===
				Arguments.of(
						new String[]{"e", "d", "c", "b", "a"},
						"x",
						-1,
						"Element not present: getIndex('x') should return -1"
				)
		);
	}
}

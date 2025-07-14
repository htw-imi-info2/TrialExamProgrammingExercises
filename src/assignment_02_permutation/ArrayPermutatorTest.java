package assignment_02_permutation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import settings.TestParameter;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayPermutatorTest {

	public static final int SEED = 422;
	ArrayPermutator<Integer> integerPermutator;
	ArrayPermutator<String> stringPermutator;

	@BeforeEach
	public void setUp() {
		integerPermutator = new ArrayPermutator<>();
		stringPermutator = new ArrayPermutator<>();
		integerPermutator.random = new Random(SEED);
		stringPermutator.random = new Random(SEED);
	}

	@ParameterizedTest(name = "{index}: {4}")
	@MethodSource("selectedTestCases")
	<T> void testShuffle(T[] input, T[] expected_b, T[] expected_f, Class<?> type, String description) {
		T[] array = Arrays.copyOf(input, input.length);
		T[] original = Arrays.copyOf(input, input.length);

		if (type == Integer.class) {
			integerPermutator.shuffle((Integer[]) array);
		} else if (type == String.class) {
			stringPermutator.shuffle((String[]) array);
		} else {
			fail("Unsupported type: " + type);
		}

		if (expected_b == null && expected_f == null) {
			assertArrayEquals(input, array, "Expected no change for " + description);
		} else {
			assertShuffle(array, original, expected_b, expected_f, description);
		}
	}

	private static Stream<Arguments> selectedTestCases() {
		if (TestParameter.ALWAYS_RUN_ALL_TESTS)
			return allTestCases();
		else {
			int limit = 1; // Just the first test case to start (Empty Array)
			//limit = 2; // all up to 1 element
			//limit = 3; // all up to 2 elements
			//limit = 4; // all up to 3 elements
			//limit = 6; // all

			return allTestCases().limit(limit);
		}
	}

	private static Stream<Arguments> allTestCases() {
		return Stream.of(
				// === String arrays ===
				// Empty array, should remain unchanged
				Arguments.of(new String[]{}, null, null, String.class, "Empty Array"),
				// One element, no shuffle possible
				Arguments.of(new String[]{"a"}, null, null, String.class, "One Element"),

				// === Integer arrays ===
				// Two elements, shuffle may swap or not
				Arguments.of(new Integer[]{7, 4}, new Integer[]{4, 7}, new Integer[]{7, 4}, Integer.class, "Two Elements"),
				// Three elements
				Arguments.of(new Integer[]{84, 12, 9}, new Integer[]{12, 9, 84}, new Integer[]{9, 12, 84}, Integer.class, "Three Elements"),
				// Ten elements
				Arguments.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
						new Integer[]{9, 10, 5, 3, 6, 4, 8, 7, 2, 1},
						new Integer[]{7, 10, 9, 1, 2, 4, 6, 5, 3, 8},
						Integer.class, "Shuffle Integers"),
				// String list of a–p
				Arguments.of("a b c d e f g h i j k l m n o p".split(" "),
						"m, p, d, l, j, g, f, h, e, k, i, c, n, o, b, a".split(", "),
						"l, m, n, o, j, c, d, p, i, h, g, f, e, a, k, b".split(", "),
						String.class, "Shuffle Strings")
		);
	}

	private <T> void assertShuffle(T[] a, T[] original, T[] expected_b, T[] expected_f, String methodName) {
		String message = "";
		boolean fail = false;

		if (Arrays.equals(expected_f, a)) {
			message = "You shuffled forward.";
		} else if (Arrays.equals(expected_b, a)) {
			message = "You shuffled backward.";
		} else if (Arrays.equals(original, a)) {
			message = "Your array hasn't changed.";
			fail = true;
		} else {
			message = "Your array was changed, but not in the way the algorithm presented in class would have changed it.";
		}

		System.out.println(methodName + ":");
		System.out.println("Original Array: " + Arrays.toString(original));
		System.out.println("Shuffled Array: " + Arrays.toString(a));
		System.out.println(message);
		System.out.println();

		if (fail) fail(message);
	}
}

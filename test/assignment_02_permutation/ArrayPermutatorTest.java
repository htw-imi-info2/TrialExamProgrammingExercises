package assignment_02_permutation;


import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayPermutatorTest {

	public static final int SEED = 422;
	ArrayPermutator<Integer> integerPermutator;
	ArrayPermutator<String> stringPermutator;
	@BeforeEach
	public void setUp() {

		integerPermutator = new ArrayPermutator<>();
		stringPermutator = new ArrayPermutator<>();
		// inject a random generator with a specific seed to
		// get a pseudorandom sequence to make the Permutator
		// testable
		integerPermutator.random = new Random(SEED);
		stringPermutator.random = new Random(SEED);
	}


	@Test
	public void test1OneElement() {
		String[] a = { "a" };
		
		stringPermutator.shuffle(a);
		
		String[] expected = { "a" };
		assertArrayEquals(expected, a);
	}
	@Test
	public void test0EmptyArray() {
		String[] a = { };

		stringPermutator.shuffle(a);
		
		String[] expected = { };
		assertArrayEquals(expected, a);
	}

	@Test
	public void test2TwoElements() {
		Integer[] a = new Integer[] { 4, 7 };
		Integer[] original = Arrays.copyOf(a, a.length);
		
		Integer[] expected_b = new Integer[] { 4, 7 };
		Integer[] expected_f = new Integer[] { 7, 4 };
	
		integerPermutator.shuffle(a);
		assertShuffle(a, original, expected_b, expected_f, "testTwoElements");
	}

	@Test
	public void test3ThreeElements() {
		Integer[] a = new Integer[] { 84, 12, 9 };
		Integer[] original = Arrays.copyOf(a, a.length);

		Integer[] expected_b = new Integer[] { 12, 9, 84 };
		Integer[] expected_f = new Integer[] { 9, 12, 84 };

		integerPermutator.shuffle(a);
		assertShuffle(a, original, expected_b, expected_f, "testTwoElements");
	}

	@Test
	public void test8ShuffleInts() {
		Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Integer[] original = Arrays.copyOf(a, a.length);

		integerPermutator.shuffle(a);

		// there are two ways to implement the shuffle - starting from the start
		// and starting from the beginning. Both are ok, these are the two possible
		// results (with the fixed random generator:
		Integer[] expected_b = new Integer[] { 9, 10, 5, 3, 6, 4, 8, 7, 2, 1 };
		Integer[] expected_f = new Integer[] { 7, 10, 9, 1, 2, 4, 6, 5, 3, 8};

		// assertShuffle is a convenience method providing richer feedback
		assertShuffle(a, original, expected_b, expected_f, "testShuffleInts");
	}

	@Test
	public void test9ShuffleStrings() {
		String[] a = "a b c d e f g h i j k l m n o p".split(" ");

		String[] original = Arrays.copyOf(a, a.length);

		stringPermutator.shuffle(a);

		String[] expected_f = "l, m, n, o, j, c, d, p, i, h, g, f, e, a, k, b".split(", ");
		String[] expected_b = "m, p, d, l, j, g, f, h, e, k, i, c, n, o, b, a".split(", ");

		assertShuffle(a, original, expected_b, expected_f, "testShuffleStrings");
	}
	

	private void assertShuffle(Object[] a, Object[] original, Object[] expected_b, Object[] expected_f,
			String methodName) {
		String message = "";
		boolean fail = false;

		if (Arrays.equals(expected_f, a)) {
			message += "You shuffled forward.";
		} else if (Arrays.equals(expected_b, a)) {
			message += "You shuffled backwards.";
		} else if (Arrays.equals(original, a)) {
			message += "Your array hasn't changed";
			fail = true;
		} else {
			message += "Your array was changed, but not in the way the algorithm presented in class would have changed it";
		}
		System.out.println(methodName+":");
		System.out.println("Original Array: "+Arrays.toString(original));
		System.out.println("Shuffled Array: "+Arrays.toString(a));
		System.out.println(message);
		System.out.println();
		
		if (fail) fail(message);
	}
}

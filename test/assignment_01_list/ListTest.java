package assignment_01_list;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class ListTest {
	List<String> list;

	@BeforeEach
	public void setup(){
		list = new List<>();
	}

	@Test
	public void T01EmptyList() {
		assertEquals(-1,list.getIndex("a"));
	}

	@Test
	public void T01OneElement() {
		list.add("a");
		assertEquals(1,list.getIndex("a"));
	}

	@Test
	public void T02MiddleElement() {
		list.add("e");
		list.add("d");
		list.add("c");
		list.add("b");
		list.add("a");
		assertEquals(4,list.getIndex("d"));
	}
	@Test
	public void T03LastElement() {
		list.add("e");
		list.add("d");
		list.add("c");
		list.add("b");
		list.add("a");
		assertEquals(5,list.getIndex("e"));
	}
	@Test
	public void T04NonExistingElement() {
		list.add("e");
		list.add("d");
		list.add("c");
		list.add("b");
		list.add("a");
		assertEquals(-1,list.getIndex("x"));
	}

}

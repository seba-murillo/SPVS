package model;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;


class InputTest{

	@Test
	void test(){
		System.setIn(new ByteArrayInputStream("6".getBytes()));
		System.setIn(new ByteArrayInputStream("6".getBytes()));
		System.setIn(new ByteArrayInputStream("6".getBytes()));
		System.setIn(new ByteArrayInputStream("6".getBytes()));
		System.setIn(new ByteArrayInputStream("6".getBytes()));
		System.setIn(new ByteArrayInputStream("6".getBytes()));
		System.setIn(new ByteArrayInputStream("6".getBytes()));
		System.setIn(new ByteArrayInputStream("6".getBytes()));
	}
}

package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.State;


class test_position{

	@BeforeEach
	void setUp() throws Exception{
	}

	@AfterEach
	void tearDown() throws Exception{
	}

	@Test
	void test(){
		State state = new State("pos_1");
		state.print();
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception{
	}

}

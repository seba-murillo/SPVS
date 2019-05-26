package tests;

import org.junit.jupiter.api.Test;
import model.Game;
import model.State;


class test_FFFFF{

	@Test
	void test(){
		Game.grid_rows = 5;
		Game.grid_cols = 5;

		State state = new State("exp_1");
		state.print();
		log("");

		state = state.tick();
		state.print();
		log("");
		state = state.tick();
		state.print();
		log("");
		state = state.tick();
		state.print();
		log("");
		state = state.tick();
		state.print();
		log("");
		state = state.tick();
		state.print();
		log("");
		log("");
		log("");
		log("");
		log("");
		State.printList();
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

}

package tests;

import org.junit.jupiter.api.Test;
import model.Game;
import model.State;


class test_FFFBBFFF{

	@Test
	void test(){
		Game.grid_rows = 3;
		Game.grid_cols = 3;
		State state = new State("exp_1");
		state.print();
		log("");
		state = state.tick();
		state.print();
		log("");
		state = state.tick();
		state.print();
		log("");
		log("BACK");
		log("");
		Game.loadLastState();
		state = Game.getCurrentState();
		state.print();
		log("");
		Game.loadLastState();
		state = Game.getCurrentState();
		state.print();
		log("");
		log("FORWARD");
		log("");
		state = state.tick();
		state.print();
		log("");
		state = state.tick();
		state.print();
		log("");
		state = state.tick();
		state.print();
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

}

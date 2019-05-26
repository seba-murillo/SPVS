package tests;

import org.junit.jupiter.api.Test;
import model.Game;
import model.State;


class test_tick{

	@Test
	void test(){
		Game.grid_rows = 3;
		Game.grid_cols = 3;
		State state = new State("tst_1");
		state.tick();
		state.tick();
		state.tick();
		state.tick();
		State.printList();
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

}

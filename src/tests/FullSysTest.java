
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;
import model.Game;
import model.State;


class FullSysTest{

	@Test
	void test(){
		new Game(20, 20);
		State state = Game.getCurrentState();
		assert(state.addEntity(new Rabbit(), 5, 5));
		assert(state.addEntity(new Rabbit(), 0, 3));
		assert(state.addEntity(new Rabbit(), 3, 8));
		assert(state.addEntity(new Wolf(), 7, 8));
	}
}

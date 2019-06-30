package tests;

import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;
import model.Game;
import model.Rabbit;
import model.State;


public class EntityAddTest{

	@Test
	void test(){
		Game.MAX_X = 10;
		Game.MAX_Y = 10;
		State state = new State();
		assert (state.addEntity(new Rabbit(), 0, 0));
		assertFalse(state.addEntity(new Rabbit(), -5, -9));
		assertFalse(state.addEntity(new Rabbit(), 9999, 9999));
	}
}

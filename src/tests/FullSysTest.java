
package tests;

import org.junit.jupiter.api.Test;
import model.Game;
import model.Rabbit;
import model.State;
import model.Wolf;
import view.Screen;


class FullSysTest{

	@Test
	void test(){
		new Game(20, 20);
		State state = State.getCurrent();
		assert (state.addEntity(new Rabbit(), 5, 5));
		assert (state.addEntity(new Rabbit(), 0, 3));
		assert (state.addEntity(new Rabbit(), 3, 8));
		assert (state.addEntity(new Wolf(), 7, 8));
		new Screen(20, 20);
	}
}

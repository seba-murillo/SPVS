import org.junit.jupiter.api.Test;
import controller.Controller;
import model.Game;
import model.Rabbit;
import model.State;


public class EntityAddTest{

	@Test
	void test(){
		Controller.MAX_X = 10;
		Controller.MAX_Y = 10;
		State state = new State(0);
		assert (state.addEntity(new Rabbit(), 0, 0));
		assert(state.addEntity(new Rabbit(), -5, -9) == false);
		assert(state.addEntity(new Rabbit(), 9999, 9999) == false);
	}
}

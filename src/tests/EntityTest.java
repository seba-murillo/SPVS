package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Bear;
import model.Game;
import model.Plant;
import model.Rabbit;
import model.State;
import model.Stone;
import model.Tree;
import model.Wolf;


class EntityTest{
	@Test
	void test(){
		Game.MAX_X = 10;
		Game.MAX_Y = 10;
		State state = new State();
		assert(state.addEntity(new Plant(), 0, 0));
		assert(state.addEntity(new Tree(), 1, 1));
		assert(state.addEntity(new Stone(), 2, 2));
		assert(state.addEntity(new Rabbit(), 3, 3));
		assert(state.addEntity(new Wolf(), 4, 4));
		assert(state.addEntity(new Bear(), 5, 5));		
	}
}

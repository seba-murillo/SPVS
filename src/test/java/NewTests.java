import model.*;
import org.junit.jupiter.api.Test;
import utils.SPVSutils;
import view.Screen;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


public class NewTests{

	void delay(int ms){
		try{
			Thread.sleep(ms);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@Test
	void test_animal_settergetters(){
		int X = 7;
		int Y = 5;
		int F = 100;
		Entity bear = new Bear();
		bear.setX(X);
		bear.setY(Y);
		bear.setFood(F);
		assertEquals(bear.getX(), X);
		assertEquals(bear.getY(), Y);
		assertEquals(bear.getFood(), F);
	}

	@Test
	void test_remove_entity(){
		State.initialize(10, 10, 1000);
		Screen.create(10, 10);
		State state = State.getCurrent();
		state.addEntity(new Stone(), 3, 3);
		state.removeEntityAt(3, 3);
		assertNull(state.getEntityAt(3 , 3));
	}

	@Test
	void test_state_forward_backwards(){
		State.initialize(10, 10, 1000);
		Screen.create(10, 10);
		State state = State.getCurrent();
		state.addEntity(new Rabbit(), 3, 3);
		state.addEntity(new Plant(), 8, 3);
		int[][] rabbit_pos = new int[3][2];
		rabbit_pos[0] = State.getCurrent().getClosestEntityType(Entity.TYPE_RABBIT, 0, 0);
		State.next();
		rabbit_pos[1] = State.getCurrent().getClosestEntityType(Entity.TYPE_RABBIT, 0, 0);
		State.prev();
		rabbit_pos[2] = State.getCurrent().getClosestEntityType(Entity.TYPE_RABBIT, 0, 0);
		assertEquals(rabbit_pos[0][0], 3);
		assertEquals(rabbit_pos[0][1], 3);
		assertFalse(rabbit_pos[1][0] == 3 && rabbit_pos[1][1] == 3);
		assertEquals(rabbit_pos[2][0], 3);
		assertEquals(rabbit_pos[2][1], 3);
	}

	@Test
	void test_forced_movement(){
		State.initialize(10, 10, 1000);
		Screen.create(10, 10);
		State state = State.getCurrent();
		state.addEntity(new Stone(), 2, 2);
		state.addEntity(new Stone(), 2, 3);
		state.addEntity(new Stone(), 2, 4);
		state.addEntity(new Stone(), 3, 2);
		state.addEntity(new Stone(), 3, 4);
		state.addEntity(new Stone(), 4, 2);
		state.addEntity(new Stone(), 4, 4);
		state.addEntity(new Rabbit(), 3, 3);
		state.addEntity(new Plant(), 8, 3);
		int[][] rabbit_pos = new int[2][2];
		rabbit_pos[0] = State.getCurrent().getClosestEntityType(Entity.TYPE_RABBIT, 0, 0);
		State.next();
		rabbit_pos[1] = State.getCurrent().getClosestEntityType(Entity.TYPE_RABBIT, 0, 0);
		assertEquals(rabbit_pos[0][0], 3);
		assertEquals(rabbit_pos[0][1], 3);
		assertEquals(rabbit_pos[1][0], 4);
		assertEquals(rabbit_pos[1][1], 3);
	}

	@Test
	void test_entity_movement(){
		State.initialize(10, 10, 1000);
		Screen.create(10, 10);
		State state = State.getCurrent();
		Entity stone = new Stone();
		Entity tree = new Tree();
		Entity plant = new Plant();
		Entity rabbit = new Rabbit();
		Entity wolf = new Wolf();
		Entity bear = new Bear();

		state.addEntity(stone, 0, 0);
		state.addEntity(tree, 0, 3);
		state.addEntity(plant, 0, 6);
		state.addEntity(rabbit, 3, 0);
		state.addEntity(wolf, 3, 3);
		state.addEntity(bear, 3, 6);

		int rabbit_move = rabbit.move();
		int wolf_move = wolf.move();
		int bear_move = bear.move();

		assertEquals(stone.move(), 0);
		assertEquals(tree.move(), 0);
		assertEquals(plant.move(), 0);
		assertTrue(rabbit_move >= 0);
		assertTrue(rabbit_move < 10);
		assertTrue(wolf_move >= 0);
		assertTrue(wolf_move < 10);
		assertTrue(bear_move >= 0);
		assertTrue(bear_move < 10);
	}

	@Test
	void test_show_endscreen(){
		State.initialize(10, 10, 10);
		Screen.create(10, 10);
		//State state = State.getCurrent();
		for(int i = 0;i < 10;i++){
			State.next();
		}
		// TODO assert popup exists
	}

	@Test
	void test_screenshot(){
		State.initialize(10, 10, 10);
		Screen.create(10, 10);
		State state = State.getCurrent();
		for(int i = 0;i < 10;i++){
			state.addEntity(new Stone(), i, i);
			state.addEntity(new Stone(), i, 10 - i);
		}
		Screen.screenshot();
		// TODO assert screenshot OK
	}

	@Test
	void test_start_game(){
		Game game = Game.start(10, 10, 10);
		assertNotNull(game);
		Game another = Game.start(10, 10, 10);
		assertSame(game, another);
	}
}

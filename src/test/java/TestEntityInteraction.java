import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Screen;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntityInteraction{

	@BeforeEach
	void setup(){
		State.initialize(10, 10, 1000);
		Screen.create(10, 10);
	}

	@Test
	void test_pathfinding(){
		assertEquals(Entity.pathfind(3, 3, 3, 3), 0);
		assertEquals(Entity.pathfind(3, 3, 1, 1), 1);
		assertEquals(Entity.pathfind(3, 3, 3, 1), 2);
		assertEquals(Entity.pathfind(3, 3, 5, 1), 3);
		assertEquals(Entity.pathfind(3, 3, 5, 3), 4);
		assertEquals(Entity.pathfind(3, 3, 1, 3), 8);
		assertEquals(Entity.pathfind(3, 3, 1, 5), 7);
		assertEquals(Entity.pathfind(3, 3, 3, 5), 6);
		assertEquals(Entity.pathfind(3, 3, 5, 5), 5);
	}

	@Test
	void test_rabbit_vs_plants(){
		//Game.start(10, 10, 1000);
		State state = State.getCurrent();
		Plant[] P = new Plant[4];
		for(int i = 0;i < 4;i++){
			P[i] = new Plant();
		}
		state.addEntity(P[0], 0, 0);
		state.addEntity(P[1], 9, 0);
		state.addEntity(P[2], 9, 9);
		state.addEntity(P[3], 0, 9);
		state.addEntity(new Rabbit(), 4, 4);
		for(int i = 0;i < 50;i++){
			State.next();
		}
		for(int i = 0;i < 4;i++){
			assertTrue(P[i].getTimesEaten() > 0);
		}
	}

	@Test
	void test_wolf_vs_rabbits(){
		//Game.start(10, 10, 1000);
		State state = State.getCurrent();
		Rabbit[] R = new Rabbit[4];
		for(int i = 0;i < 4;i++){
			R[i] = new Rabbit();
		}
		state.addEntity(R[0], 4, 4);
		state.addEntity(R[1], 7, 2);
		state.addEntity(R[2], 2, 7);
		state.addEntity(R[3], 7, 7);
		state.addEntity(new Wolf(), 8, 8);
		for(int i = 0;i < 40;i++){
			State.next();
		}
		for(int i = 0;i < 4;i++){
			//SPVSutils.log("R[%d] alive? " + R[i].isAlive(), i);
			assertFalse(R[i].isAlive());
		}
	}
}

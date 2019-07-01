package tests;

import org.junit.jupiter.api.Test;
import controller.Controller;
import model.Entity;
import model.FixedRabbit;
import model.Fluffy;
import model.Game;
import model.Plant;
import model.State;
import model.Stone;
import model.Tree;


class StateSurroundingsTest{

	@Test
	void test(){
		int start_pos = 3;
		new Game(2 * start_pos + 1, 2 * start_pos + 1, -1);
		State state = Controller.getCurrentState();
		Fluffy fluffy = new Fluffy();
		state.addEntity(fluffy, start_pos, start_pos);
		Entity P1 = new Plant();
		Entity P2 = new Plant();
		Entity S1 = new Stone();
		Entity S2 = new Stone();
		Entity T1 = new Tree();
		Entity T2 = new Tree();
		Entity R1 = new FixedRabbit();
		Entity R2 = new FixedRabbit();
		state.addEntity(P1, start_pos + 0, start_pos - 1);
		state.addEntity(P2, start_pos + 0, start_pos + 1);
		state.addEntity(S1, start_pos - 1, start_pos + 0);
		state.addEntity(S2, start_pos + 1, start_pos + 0);
		state.addEntity(T1, start_pos - 1, start_pos + 1);
		state.addEntity(T2, start_pos + 1, start_pos + 1);
		state.addEntity(R1, start_pos - 1, start_pos - 1);
		state.addEntity(R2, start_pos + 1, start_pos - 1);

		Entity[][] surroundings = Controller.getCurrentState().getSurroundings(fluffy.getX(), fluffy.getY());
		log(surroundings[0][0]);
		log(surroundings[0][1]);
		log(surroundings[0][2]);
		log(surroundings[1][0]);
		log(surroundings[1][1]);
		log(surroundings[1][2]);
		log(surroundings[2][0]);
		log(surroundings[2][1]);
		log(surroundings[2][2]);
		assert (surroundings[0][0] == R1);
		assert (surroundings[0][1] == S1);
		assert (surroundings[0][2] == T1);
		assert (surroundings[1][0] == P1);
		assert (surroundings[1][1] == null);
		assert (surroundings[1][2] == P2);
		assert (surroundings[2][0] == R2);
		assert (surroundings[2][1] == S2);
		assert (surroundings[2][2] == T2);
		delay(3000);
	}

	private void delay(int time){
		try{
			Thread.sleep(time);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public static void log(Object message){
		if(message == null) return;
		System.out.println(message.toString());
	}

}

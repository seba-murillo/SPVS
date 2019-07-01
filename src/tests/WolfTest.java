package tests;

import org.junit.jupiter.api.Test;
import model.FixedRabbit;
import model.Game;
import model.State;
import model.Wolf;


class WolfTest{

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Test
	void test(){
		int start_pos = 4;
		new Game(10, 10, -1);
		State state = State.getCurrent();
		Wolf wolf = new Wolf();
		FixedRabbit P1 = new FixedRabbit();
		FixedRabbit P2 = new FixedRabbit();
		FixedRabbit P3 = new FixedRabbit();
		FixedRabbit P4 = new FixedRabbit();
		state.addEntity(wolf, start_pos, start_pos);
		state.addEntity(P1, 0, 0);
		state.addEntity(P2, 9, 0);
		state.addEntity(P3, 9, 9);
		state.addEntity(P4, 0, 9);
		delay(1000);
		while(P1.isAlive() || P2.isAlive() || P3.isAlive() || P4.isAlive()){
			state.tick();
			State.update_observers();
			delay(500);
		}
	}

	private void delay(int time){
		try{
			Thread.sleep(time);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}

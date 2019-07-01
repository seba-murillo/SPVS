package tests;

import org.junit.jupiter.api.Test;
import controller.Controller;
import model.Game;
import model.Plant;
import model.Rabbit;
import model.State;


class RabbitTest{

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Test
	void test(){
		int start_pos = 4;
		Game.start(10, 10, -1);
		State state = Controller.getCurrentState();
		Rabbit rabbit = new Rabbit();
		Plant P1 = new Plant();
		Plant P2 = new Plant();
		Plant P3 = new Plant();
		Plant P4 = new Plant();
		state.addEntity(rabbit, start_pos, start_pos);
		state.addEntity(P1, 0, 0);
		state.addEntity(P2, 9, 0);
		state.addEntity(P3, 9, 9);
		state.addEntity(P4, 0, 9);
		delay(1000);
		for(int i = 0;i < 30;i++){
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

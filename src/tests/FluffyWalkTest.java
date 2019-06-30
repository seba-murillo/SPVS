package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Fluffy;
import model.Game;
import model.State;
import model.Stone;


class FluffyWalkTest{

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Test
	void test(){
		int start_pos = 4;
		new Game(2 * start_pos + 1, 2 * start_pos + 1, -1);
		State state = State.getCurrent();
		Fluffy fluffy = new Fluffy();
		state.addEntity(fluffy, start_pos, start_pos);
		delay(1000);
		for(int i = 0;i < 8;i++){
			state.tick();
			delay(500);
		}
		delay(2000);
		assert (fluffy.getX() == start_pos);
		assert (fluffy.getY() == start_pos);
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

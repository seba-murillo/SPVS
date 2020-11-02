import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import controller.Controller;
import model.FixedRabbit;
import model.Fluffy;
import model.Game;
import model.State;


class FluffyAttackTest{

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Test
	void test(){
		int start_pos = 4;
		Game.start(2 * start_pos + 1, 2 * start_pos + 1, -1);
		State state = Controller.getCurrentState();
		Fluffy fluffy = new Fluffy();
		FixedRabbit R1 = new FixedRabbit();
		FixedRabbit R2 = new FixedRabbit();
		FixedRabbit R3 = new FixedRabbit();
		state.addEntity(fluffy, start_pos, start_pos);
		state.addEntity(R1, start_pos - 2, start_pos - 1);
		state.addEntity(R2, start_pos, start_pos - 3);
		state.addEntity(R3, start_pos + 2, start_pos - 1);
		delay(1000);
		for(int i = 0;i < 8;i++){
			state.tick();
			State.update_observers();
			delay(500);
		}
		assert (fluffy.getX() == start_pos);
		assert (fluffy.getY() == start_pos);
		assertFalse(R1.isAlive());
		assertFalse(R2.isAlive());
		assertFalse(R3.isAlive());
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

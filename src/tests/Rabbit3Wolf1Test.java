package tests;

import org.junit.jupiter.api.Test;
import controller.Controller;
import model.Game;
import model.Rabbit;
import model.State;
import model.Wolf;


class Rabbit3Wolf1Test{

	@Test
	void test(){
		new Game(10, 10, -1);
		State state = Controller.getCurrentState();
		state.addEntity(new Rabbit(), 4, 4);
		state.addEntity(new Rabbit(), 7, 2);
		state.addEntity(new Rabbit(), 2, 7);
		state.addEntity(new Wolf(), 8, 8);
		for(int i = 0;i < 50;i++){
			state.tick();
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

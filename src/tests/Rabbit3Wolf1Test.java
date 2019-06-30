package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Game;
import model.Rabbit;
import model.State;
import model.Wolf;
import view.Screen;


class Rabbit3Wolf1Test{

	@Test
	void test(){
		new Game(10, 10, -1);
		State state = State.getCurrent();
		state.addEntity(new Rabbit(), 4, 4);
		state.addEntity(new Rabbit(), 7, 2);
		state.addEntity(new Rabbit(), 2, 7);
		state.addEntity(new Wolf(), 8, 8);
		for(int i = 0;i < 50;i++){
			state.tick();
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

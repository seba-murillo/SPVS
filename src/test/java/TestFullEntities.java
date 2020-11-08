import org.junit.jupiter.api.Test;
import controller.Controller;
import model.Bear;
import model.Game;
import model.Plant;
import model.Rabbit;
import model.State;
import model.Stone;
import model.Tree;
import model.Wolf;


class TestFullEntities {

	@Test
	void test(){
		Game.start(30, 30, -1);
		State state = Controller.getCurrentState();
		state.addEntity(new Stone(), 3, 4);
		state.addEntity(new Stone(), 4, 3);
		state.addEntity(new Stone(), 4, 4);
		state.addEntity(new Stone(), 5, 3);
		state.addEntity(new Stone(), 5, 4);
		state.addEntity(new Stone(), 5, 5);
		state.addEntity(new Stone(), 5, 6);
		state.addEntity(new Stone(), 6, 5);
		state.addEntity(new Stone(), 6, 6);
		// stone B
		state.addEntity(new Stone(), 12, 17);
		state.addEntity(new Stone(), 13, 17);
		state.addEntity(new Stone(), 13, 18);
		// stone C
		state.addEntity(new Stone(), 20, 15);
		state.addEntity(new Stone(), 21, 13);
		state.addEntity(new Stone(), 21, 14);
		state.addEntity(new Stone(), 21, 15);
		state.addEntity(new Stone(), 22, 13);
		state.addEntity(new Stone(), 22, 14);
		state.addEntity(new Stone(), 22, 15);
		state.addEntity(new Stone(), 23, 14);
		state.addEntity(new Stone(), 23, 15);
		state.addEntity(new Stone(), 23, 16);
		state.addEntity(new Stone(), 24, 15);
		// Tree A
		state.addEntity(new Tree(), 6, 11);
		state.addEntity(new Tree(), 7, 10);
		state.addEntity(new Tree(), 7, 11);
		state.addEntity(new Tree(), 7, 12);
		state.addEntity(new Tree(), 8, 11);
		state.addEntity(new Tree(), 8, 12);
		state.addEntity(new Tree(), 8, 13);
		state.addEntity(new Tree(), 9, 13);
		// Tree B
		state.addEntity(new Tree(), 11, 15);
		state.addEntity(new Tree(), 12, 16);
		state.addEntity(new Tree(), 13, 15);
		state.addEntity(new Tree(), 13, 16);
		state.addEntity(new Tree(), 14, 16);
		state.addEntity(new Tree(), 14, 17);
		// Tree C			
		state.addEntity(new Tree(), 17, 10);
		state.addEntity(new Tree(), 17, 11);
		state.addEntity(new Tree(), 18, 10);
		state.addEntity(new Tree(), 18, 11);
		state.addEntity(new Tree(), 19, 11);
		// Tree D
		state.addEntity(new Tree(), 21, 5);
		state.addEntity(new Tree(), 23, 7);
		state.addEntity(new Tree(), 24, 3);
		state.addEntity(new Tree(), 25, 3);
		state.addEntity(new Tree(), 26, 3);
		state.addEntity(new Tree(), 27, 4);
		// Plants
		state.addEntity(new Plant(), 1, 1);
		state.addEntity(new Plant(), 4, 24);
		state.addEntity(new Plant(), 4, 28);
		state.addEntity(new Plant(), 5, 20);
		state.addEntity(new Plant(), 5, 22);
		state.addEntity(new Plant(), 7, 17);
		state.addEntity(new Plant(), 7, 18);
		state.addEntity(new Plant(), 8, 5);
		state.addEntity(new Plant(), 8, 6);
		state.addEntity(new Plant(), 9, 20);
		state.addEntity(new Plant(), 9, 25);
		state.addEntity(new Plant(), 13, 4);
		state.addEntity(new Plant(), 13, 5);
		state.addEntity(new Plant(), 13, 6);
		state.addEntity(new Plant(), 13, 7);
		state.addEntity(new Plant(), 13, 22);
		state.addEntity(new Plant(), 14, 6);
		state.addEntity(new Plant(), 15, 4);
		state.addEntity(new Plant(), 17, 20);
		state.addEntity(new Plant(), 19, 16);
		state.addEntity(new Plant(), 21, 10);
		state.addEntity(new Plant(), 25, 4);
		state.addEntity(new Plant(), 25, 18);
		state.addEntity(new Plant(), 25, 5);
		state.addEntity(new Plant(), 26, 15);
		state.addEntity(new Plant(), 26, 18);
		state.addEntity(new Plant(), 28, 5);
		state.addEntity(new Plant(), 28, 7);
		state.addEntity(new Plant(), 28, 9);
		// rabbits
		state.addEntity(new Rabbit(), 6, 24);
		state.addEntity(new Rabbit(), 13, 10);
		state.addEntity(new Rabbit(), 18, 8);
		state.addEntity(new Rabbit(), 19, 3);
		state.addEntity(new Rabbit(), 24, 21);
		state.addEntity(new Rabbit(), 24, 24);
		state.addEntity(new Rabbit(), 27, 22);
		// wolfs
		state.addEntity(new Wolf(), 1, 14);
		state.addEntity(new Wolf(), 29, 15);
		// bear
		state.addEntity(new Bear(), 19, 22);
		for(int i = 0;i < 10;i++){
			state.tick();
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

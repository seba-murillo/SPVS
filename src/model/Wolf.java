package model;

import java.io.File;
import java.util.Random;
import controller.Controller;


public class Wolf extends Entity{

	private static int count = 1;

	public Wolf(){
		this("Wolf " + count++);
	}

	public Wolf(String name){
		setName(name);
		setIcon(new File("img/wolf.png"));
		type = Entity.TYPE_WOLF;
	}

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Override
	public int move(){
		if(!alive) return 0;
		move_cooldown -= 1;
		if(move_cooldown > 0) return 0;
		// move
		food--;
		if(food < 0){
			this.kill("starvation");
			return 0;
		}
		if(check_surroundings()) return 0;
		int[] dest = Controller.getCurrentState().getClosestEntityType(Entity.TYPE_RABBIT, getX(), getY());
		if(dest[0] == -1 && dest[1] == -1) return (new Random().nextInt((8 - 0) + 1));
		int dir = Entity.pathfind(getX(), getY(), dest[0], dest[1]);
		move_cooldown += move_rest_needed;
		if(check_surroundings()) return 0;
		return getRandom(dir);
	}

	private boolean check_surroundings(){
		Entity[][] surr = Controller.getCurrentState().getSurroundings(getX(), getY());
		for(int x = 0;x < 3;x++){
			for(int y = 0;y < 3;y++){
				if(surr[x][y] == null) continue;
				if(!surr[x][y].isAlive()) continue;
				if(surr[x][y].getType() == Entity.TYPE_RABBIT){
					surr[x][y].kill(this.toString());
					this.food += FOOD_GAIN;
					return true;
				}
			}
		}
		return false;
	}

	private static int getRandom(int dir){
		int result = new Random().nextInt(3) + (dir - 1); // dir +/- 1
		result = (result == 0) ? 8 : result;
		return result;
	}
}

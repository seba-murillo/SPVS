package model;

import java.io.File;
import java.util.Random;


public class Bear extends Entity{

	private static int count = 1;

	public Bear(){
		this("Bear " + count++);
	}

	public Bear(String name){
		setName(name);
		setIcon(new File("img/bear.png"));
		type = Entity.TYPE_BEAR;
	}

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
		int[] dest = State.getCurrent().getClosestEntityType(Entity.TYPE_PLANT, getX(), getY());
		if(dest[0] == -1 && dest[1] == -1) return (new Random().nextInt((8 - 0) + 1));
		int dir = Entity.pathfind(getX(), getY(), dest[0], dest[1]);
		move_cooldown += move_rest_needed;
		// check_proximity();
		return getRandom(dir);
	}

	private static int getRandom(int dir){
		int result = new Random().nextInt(3) + (dir - 1); // dir +/- 1
		result = (result == 0) ? 8 : result;
		return result;
	}

	@Override
	public void kill(String reason){
		if("starvation".equals(reason)) log(this.toString() + " died from starvation");
		else
			log(this.toString() + " was eaten by " + reason);
		food = -1;
		alive = false;
		type = Entity.INEX;
		setName(name + " (dead)");
		setIcon(Entity.FILE_DEAD);
	}
}
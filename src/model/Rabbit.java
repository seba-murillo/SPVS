package model;

import java.io.File;


public class Rabbit extends Entity{

	private static int count = 1;

	public Rabbit(){
		this("Rabbit " + count++);
	}

	public Rabbit(String name){
		setName(name);
		setIcon(new File("img/rabbit.png"));
		type = Entity.TYPE_RABBIT;
	}

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[5] [6] [7]
	 *       +
	 */
	@Override
	public int move(){
		if(!alive) return 0;
		move_cooldown -= 1;
		if(move_cooldown > 0) return 0;
		// move
		int[] dest = State.getCurrent().getClosestEntityType(Entity.TYPE_PLANT, getX(), getY());
		int dir = Entity.pathfind(getX(), getY(), dest[0], dest[1]);
		move_cooldown += move_rest_needed;
		// check_proximity();
		return dir;
	}

	@Override
	public void die(){
		food = -1;
		setIcon(Entity.FILE_DEAD);
	}
}

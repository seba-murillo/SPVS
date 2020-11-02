package model;

import java.io.File;


public class FixedRabbit extends Entity{

	private static int count = 1;

	public FixedRabbit(){
		setName("Rabbit " + count++);
		setIcon(new File(Entity.RESOURCE + "rabbit.png"));
		type = Entity.TYPE_RABBIT;
	}

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Override
	public int move(){
		return 0;
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

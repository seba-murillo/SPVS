package model;

import java.io.File;


public class Fluffy extends Entity{

	private int step = 0;

	public Fluffy(){
		setName("Fluffy");
		setIcon(new File(Entity.RESOURCE + "wolf.png"));
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
		int res = move_fluffy();
		check_surroundings();
		return res;
	}

	private void check_surroundings(){
		Entity[][] surr = State.getCurrent().getSurroundings(getX(), getY());
		outer:for(int x = 0;x < 3;x++){
			for(int y = 0;y < 3;y++){
				if(surr[x][y] == null) continue;
				if(surr[x][y].getType() == Entity.TYPE_RABBIT){
					surr[x][y].kill(this.toString());
					this.food += FOOD_GAIN;
					break outer;
				}
			}
		}
		// print surroundings
		/*
		log("\n\n");
		log("fluffy is @ (" + getX() + ", " + getY() + ")");
		for(int x = 0;x < 3;x++){
			for(int y = 0;y < 3;y++){
				log("surr[" + x + "][" + y + "]: " + surr[x][y]);
			}
		}
		*/
	}

	private int move_fluffy(){
		step += 2;
		step = (step == 10) ? 1 : step;
		step = (step == 9) ? 2 : step;
		return step;
	}

	@Override
	public void kill(String reason) {

	}
}
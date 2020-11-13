package model;

import java.io.File;


public class Plant extends Entity{

	private static int count = 1;
	private int eaten = 0;

	public Plant(){
		this("Plant " + count++);
	}

	public Plant(String name){
		setName(name);
		setIcon(new File(Entity.RESOURCE + "plant.png"));
		type = Entity.TYPE_PLANT;
	}

	// plant respawns after 8 days
	@Override
	public void move(){
		if(alive) return;
		if(move_rest_needed > 0){
			move_rest_needed--;
			return;
		}
		setIcon(new File(Entity.RESOURCE + "plant.png"));
		alive = true;
	}

	@Override
	public void kill(String reason){
		log(this.toString() + " was eaten by " + reason);
		eaten++;
		alive = false;
		setIcon(new File(Entity.RESOURCE + "plant_dead.png"));
		move_rest_needed = 15; // turns for respawn
	}

	public int getTimesEaten(){
		return eaten;
	}
}
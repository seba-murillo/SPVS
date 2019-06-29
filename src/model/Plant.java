package model;

import java.io.File;


public class Plant extends Entity{

	private static int count = 1;

	public Plant(){
		this("Plant " + count++);
	}

	public Plant(String name){
		setName(name);
		setIcon(new File("img/plant.png"));
	}

	// plant respawns after 8 days
	@Override
	public int move(){
		if(alive) return 0;
		if(move_rest_needed > 0){
			move_rest_needed--;
			return 0;
		}
		setIcon(new File("img/plant.png"));
		alive = true;
		return 0;
	}

	@Override
	public void die(){
		alive = false;
		icon = null;
		move_rest_needed = 8; // turns for respawn
	}
}
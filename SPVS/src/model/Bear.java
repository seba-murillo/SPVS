package model;

import java.io.File;

public class Bear extends Entity{
	
	private static int count = 1;
	
	public Bear(){
		this("Bear " + count++);
	}
	
	public Bear(String name) {
		setName(name);
		setIcon(new File("img/bear.png"));
	}
	
	@Override
	public void move(){
		move_cooldown -= 1;
		if(move_cooldown > 0) return;
		// move
		move_cooldown += move_rest_needed;		
	}	
	

	@Override
	public void die(){
		food = -1;
		setIcon(Entity.FILE_DEAD);
	}
}
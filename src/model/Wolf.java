package model;

import java.io.File;


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

	@Override
	public int move(){
		return 0;
		/*
		// TODO MOVEMENT
		if(!alive) return;
		move_cooldown -= 1;
		if(move_cooldown > 0) return;
		// move
		move_cooldown += move_rest_needed;
		*/
	}

	@Override
	public void die(){
		food = -1;
		setIcon(Entity.FILE_DEAD);
	}
}
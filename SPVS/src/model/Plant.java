package model;

import java.io.File;

public class Plant extends Entity{
	
	private static int count = 1;
	
	public Plant(){
		this("Plant " + count++);
	}
	
	public Plant(String name) {
		setName(name);
		setIcon(new File("img/plant.png"));
	}
	
	@Override
	public void move(){
		return;
	}	
	

	@Override
	public void die(){
		food = -1;
		setIcon(Entity.FILE_DEAD);
	}
}
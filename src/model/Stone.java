package model;

import java.io.File;

public class Stone extends Entity{
	
	private static int count = 1;
	
	public Stone(){
		this("Stone " + count++);
	}
	
	public Stone(String name) {
		setName(name);
		setIcon(new File("img/stone.png"));
		alive = false;
	}
	
	@Override
	public void move(){
		return;
	}	
	

	@Override
	public void die(){
	}
}
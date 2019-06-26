package model;

import java.io.File;

public class Tree extends Entity{
	
	private static int count = 1;
	
	public Tree(){
		this("Tree " + count++);
	}
	
	public Tree(String name) {
		setName(name);
		setIcon(new File("img/tree.png"));
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
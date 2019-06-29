package model;

import java.io.File;


public class Stone extends Entity{

	private static int count = 1;

	public Stone(){
		this("Stone " + count++);
	}

	public Stone(String name){
		setName(name);
		setIcon(new File("img/stone.png"));
		alive = false;
		type = Entity.TYPE_STONE;
	}

	@Override
	public int move(){
		return 0;
	}

	@Override
	public void die(){
	}
}
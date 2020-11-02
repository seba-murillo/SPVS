package model;

import java.io.File;


public class Tree extends Entity{

	private static int count = 1;

	public Tree(){
		this("Tree " + count++);
	}

	public Tree(String name){
		setName(name);
		setIcon(new File(Entity.RESOURCE + "tree.png"));
		alive = true;
		type = Entity.TYPE_TREE;
	}

	@Override
	public int move(){
		return 0;
	}

	@Override
	public void kill(String reason){
	}
}
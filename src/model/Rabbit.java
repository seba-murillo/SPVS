package model;
import java.io.File;

public class Rabbit extends Entity{
	
	private static int count = 1;
	
	public Rabbit(){
		this("Rabbit " + count++);
	}
	
	public Rabbit(String name) {
		setName(name);
		setIcon(new File("img/rabbit.png"));
	}
	
	@Override
	public void move(){
		if(!alive) return;
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

package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public abstract class Entity{

	public static final String RESOURCE = "src/main/resources/images/";

	public static final File FILE_DEAD = new File(Entity.RESOURCE + "skull.png");

	public static final int	INEX		= -1;
	public static final int	TYPE_STONE	= 1;
	public static final int	TYPE_PLANT	= 2;
	public static final int	TYPE_TREE	= 3;
	public static final int	TYPE_RABBIT	= 4;
	public static final int	TYPE_WOLF	= 5;
	public static final int	TYPE_BEAR	= 6;

	public static final int FOOD_GAIN = 10;

	protected int			X		= -1;
	protected int			Y		= -1;
	protected String		name	= null;
	protected int			type;
	protected BufferedImage	icon;
	protected int			food	= 40;
	protected int			move_rest_needed;
	protected int			move_cooldown;
	protected boolean		alive	= true;

	public abstract int move();

	public boolean isAlive(){
		return alive;
	}

	public void addFood(int food){
		this.food += food;
	}

	public void setFood(int food){
		this.food = food;
	}

	public int getFood(){
		return food;
	}

	public void setX(int x){
		this.X = x;
	}

	public void setY(int y){
		this.Y = y;
	}

	public void setP(int x, int y){
		this.X = x;
		this.Y = y;
	}

	public int getX(){
		return X;
	}

	public int getY(){
		return Y;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getType(){
		return type;
	}

	@Override
	public String toString(){
		return name;
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

	public void setIcon(File file){
		try{
			icon = ImageIO.read(file);
		}
		catch(IOException e){
			System.err.println("Failed icon for creating " + this);
			System.err.println("PATH: " + file.getAbsolutePath());
			e.printStackTrace();
		}
	}

	public BufferedImage getIcon(){
		return icon;
	}

	/*       -
	 * 	   -[1] [4] [7]+
	 * 		[2] [0] [6]
	 * 		[3] [8] [5]
	 *       +
	 */
	public static int pathfind(int from_x, int from_y, int to_x, int to_y){
		int dir;
		if(to_x > from_x){ // go right
			if(to_y > from_y) dir = 5; // go down
			else if(to_y < from_y) dir = 3; // go up
			else
				dir = 4;
		}
		else if(to_x < from_x){ // go left
			if(to_y > from_y) dir = 7; // go down
			else if(to_y < from_y) dir = 1; // go up
			else
				dir = 8;
		}
		else{
			if(to_y > from_y) dir = 6;
			else if(to_y < from_y) dir = 2;
			else
				return 0;
		}
		//log("@pathfind dir: " + dir);
		//dir = ((int) (byte) dir) + 1;
		return dir;
	}

	public void kill(String reason){
		if("starvation".equals(reason)) log(this.toString() + " died from starvation");
		else
			log(this.toString() + " was eaten by " + reason);
		food = -1;
		alive = false;
		type = Entity.INEX;
		setName(name + " (dead)");
		setIcon(Entity.FILE_DEAD);
	}

	public Entity copy_entity(){
		Entity entity;
		switch(this.type){
			case TYPE_STONE:{
				entity = new Stone();
				break;
			}
			case TYPE_PLANT:{
				entity = new Plant();
				break;
			}
			case TYPE_TREE:{
				entity = new Tree();
				break;
			}
			case TYPE_RABBIT:{
				entity = new Rabbit();
				break;
			}
			case TYPE_WOLF:{
				entity = new Wolf();
				break;
			}
			case TYPE_BEAR:{
				entity = new Bear();
				break;
			}
			default:{
				return null;
			}
		}
		entity.X = this.X;
		entity.Y = this.Y;
		entity.name = this.name;
		entity.type = this.type;
		entity.icon = this.icon;
		entity.food = this.food;
		entity.move_rest_needed = this.move_rest_needed;
		entity.move_cooldown = this.move_cooldown;
		entity.alive = this.alive;
		return entity;
	}

	public String getInfo(){
		String tooltip = "<html>" + name + ":<br />comida: " + food + "<br />vivo: " + alive;
		return tooltip;
	}
}

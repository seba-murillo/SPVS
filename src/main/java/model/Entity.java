package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


public abstract class Entity implements Cloneable{
	//TODO ver si corresponde acoplar la lóigica de imágenes en Entidad
	public static final String RESOURCE = "resources/images/";
	//TODO debería ser función de view lo de manejo de imágenes
	public static final File FILE_DEAD = new File(Entity.RESOURCE + "skull.png");

	public static final int INEX = -1;
	public static final int TYPE_STONE = 1;
	public static final int TYPE_PLANT = 2;
	public static final int TYPE_TREE = 3;
	public static final int TYPE_RABBIT = 4;
	public static final int TYPE_WOLF = 5;
	public static final int TYPE_BEAR = 6;

	public static final int FOOD_GAIN = 10;

	protected int x = -1;
	protected int y = -1;
	protected int type;
	protected int food = 40;
	protected int move_rest_needed;
	protected int move_cooldown;
	protected String name = null;
	protected boolean alive = true;
	protected BufferedImage icon;

	public abstract void move();

	public boolean isAlive(){
		return alive;
	}

	@SuppressWarnings({})
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
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setP(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
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
		}catch(IOException e){
			System.err.println("Failed icon for creating " + this);
			System.err.println("PATH: " + file.getAbsolutePath());
			e.printStackTrace();
		}
	}

	public BufferedImage getIcon(){
		return icon;
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

	public String getInfo(){
		return "<html>" + name + ":<br />comida: " + food + "<br />vivo: " + alive;
	}

	public Entity copy() throws CloneNotSupportedException{
		return (Entity) this.clone();
	}

	public static void log(String format, Object... args){
		System.out.println(String.format(format, args));
	}


	protected Entity[][] getAvailableSpaces(Entity[][] nearby){
		Entity[][] around = new Entity[3][3];
		int offset = State.SURROUNDING_RADIUS / 2 + 1;
		for(int i = 0;i < 3;i++){
			for(int j = 0;j < 3;j++){
				around[i][j] = nearby[i + offset][j + offset];
			}
		}
		around[1][1] = null;
		return around;
	}

	protected int[] findFood(Entity[][] nearby, int foodtype){
		int max = 2 * State.SURROUNDING_RADIUS; // 0 -> 6
		for(int i = 0;i < max;i++){
			for(int j = 0;j < max;j++){
				if(nearby[i][j].getType() == foodtype){
					return new int[]{nearby[i][j].getX(), nearby[i][j].getY()};
				}
			}
		}
		return new int[]{-1, -1};

	}

	// investigate surroundings
	// if food -> if possible -> move towards randomly
	// move randomly
	protected int[] findpath(Entity[][] around, int from_x, int from_y, int to_x, int to_y){
		double chance = Math.random();
		if(to_x < 0 || to_y < 0){
			getRandom(-1, 1);
			return new int[]{0, 0};// do not move
		}
		if(to_x > from_x){ // go right
			if(to_y > from_y){// go down
				if(chance < 0.25){

				}else if(chance > 0.75){

				}else{
					return new int[]{1, 1};
				}
			}else if(to_y < from_y) dir = 3; // go up
			else
				dir = 4;
		}else if(to_x < from_x){ // go left
			if(to_y > from_y) dir = 7; // go down
			else if(to_y < from_y) dir = 1; // go up
			else
				dir = 8;
		}else{
			if(to_y > from_y) dir = 6;
			else if(to_y < from_y) dir = 2;
			else
				return 0;
		}
		//log("@pathfind dir: " + dir);
		//dir = ((int) (byte) dir) + 1;


		move_cooldown += move_rest_needed;
	}

	private int getRandom(int min, int max){
		return new Random().nextInt(max + 1 - min) + min;
	}
}
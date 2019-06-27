package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Entity{	
	public static final File FILE_DEAD = new File("img/skull.png");
	
	protected int X, Y;
	protected String name = null;
	protected int type;
	protected BufferedImage icon;
	protected int food = 10;
	protected int move_rest_needed;
	protected int move_cooldown;
	protected boolean alive = true;
	
	public abstract void move();
	
	public void addFood(int food) {
		this.food += food;
	}
	
	public void setFood(int food) {
		this.food = food;
	}
	
	public int getFood() {
		return food;
	}
	
	public void die() {
		alive = false;
		setIcon(Entity.FILE_DEAD);
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
	
	@Override
	public String toString() {
		return name;
	}
	
	public static void log(Object message) {
		System.out.println(message.toString());
	}
	
	public void setIcon(File file) {
		try{
			icon = ImageIO.read(file);
		}
		catch(IOException e){
			System.err.println("Failed icon for creating " + this);
			System.err.println("PATH: " + file.getAbsolutePath());
			e.printStackTrace();
		}
	}
	
	public BufferedImage getIcon() {
		return icon;
	}
}

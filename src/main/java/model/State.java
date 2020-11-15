package model;

import utils.SPVSutils;
import view.Screen;

import java.util.*;


public class State implements Cloneable{
	private static final TreeMap<Integer, State> states = new TreeMap<>();
	private static final HashSet<Observer> observers = new HashSet<>();

	private static int ID = 0;
	private static State current = null;
	private static int width = 0;
	private static int height = 0;
	public static int initialDuration = 0;

	private int duration;
	private List<Entity> entities = new ArrayList<>();
	private Entity[][] grid = new Entity[State.width][State.height];

	private State(int duration){
		this.duration = duration;
	}

	public static void initialize(int width, int height, int duration){
		ID = 0;
		initialDuration = duration;
		State.width = width;
		State.height = height;
		current = new State(duration);
	}

	public static int getWidth(){
		return State.width;
	}

	public static int getHeight(){
		return State.height;
	}

	public static Entity[][] getGrid(){
		return current.grid;
	}

	public static List<Entity> getEntities(){
		return current.entities;
	}

	public boolean addEntity(Entity entity, int x, int y){
		//log("adding " + entity + " to pos (" + x + ", " + y + ")");
		if(!setEntityPos(entity, x, y)) return false;
		entities.add(entity);
		updateObservers();
		return true;
	}

	private boolean setEntityPos(Entity entity, int x, int y){
		if(x < 0 || x >= width) return false;
		if(y < 0 || y >= height) return false;
		if(grid[x][y] != null){
			if(grid[x][y] == entity) return true;
			//log("@State - cannot place [" + entity + "] at (" + x + ", " + y + "): space already in use by " + grid[x][y].toString());
			return false;
		}
		int prev_x = entity.getX();
		int prev_y = entity.getY();
		// TODO FIX invert grid coords
		if(prev_x != Entity.INEX && prev_y != Entity.INEX) grid[prev_x][prev_y] = null; // remove from previous pos
		grid[x][y] = entity;
		entity.setP(x, y);
		return true;
	}

	private boolean setEntityPos(Entity entity, int dir){
		switch(dir){
			case 0:{
				return true;
			}
			case 1: // 1 = 9
			case 9:{
				return (setEntityPos(entity, entity.getX() - 1, entity.getY() - 1));
			}
			case 2:{
				return (setEntityPos(entity, entity.getX() + 0, entity.getY() - 1));
			}
			case 3:{
				return (setEntityPos(entity, entity.getX() + 1, entity.getY() - 1));
			}
			case 4:{
				return (setEntityPos(entity, entity.getX() + 1, entity.getY() + 0));
			}
			case 5:{
				return (setEntityPos(entity, entity.getX() + 1, entity.getY() + 1));
			}
			case 6:{
				return (setEntityPos(entity, entity.getX() + 0, entity.getY() + 1));
			}
			case 7:{
				return (setEntityPos(entity, entity.getX() - 1, entity.getY() + 1));
			}
			case 8:{
				return (setEntityPos(entity, entity.getX() - 1, entity.getY() + 0));
			}
			default:{
				return false;
			}
		}
	}

	public int[] getClosestEntityType(int type, int x, int y){
		int pos[] = {-1, -1};
		double dist;
		double min_dist = 999;
		for(Entity ent : entities){
			if(ent.getType() != type) continue;
			if(!ent.isAlive()) continue;
			dist = Math.sqrt(Math.pow(ent.getX() - x, 2) + Math.pow(ent.getY() - y, 2));
			if(dist < min_dist){
				min_dist = dist;
				pos[0] = ent.getX();
				pos[1] = ent.getY();
			}
		}
		return pos;
	}

	public Entity[][] getSurroundings(int pos_x, int pos_y){
		//log("@getSurroundings(" + pos_x + ", " + pos_y + ")");
		Entity[][] surroundings = new Entity[3][3];
		int start_x = pos_x - 1; // 2
		int start_y = pos_y - 1; // 2
		for(int x = start_x;x <= (pos_x + 1);x++){ // 2 -> 4
			if(x < 0 || x >= width) continue;
			for(int y = start_y;y <= (pos_y + 1);y++){// 2 -> 4
				if(y < 0 || y >= height) continue;
				if(pos_x == x && pos_y == y) continue;
				if(x - start_x < 0 || x - start_x >= width) continue;
				if(y - start_y < 0 || y - start_y >= height) continue;
				surroundings[x - start_x][y - start_y] = grid[x][y];
			}
		}
		return surroundings;
	}

	public static State getCurrent(){
		return current;
	}
    /*
    ===========================================================
    =   miscellaneous methods
    ===========================================================
    */

	@Override
	public String toString(){
		return String.format("State [%d]", ID);
	}

    /*
    ===========================================================
    =   state change
    ===========================================================
    */

	private void tick(){
		if(--duration < 1){
			Screen.showEndScreen(initialDuration);
			return;
		}
		SPVSutils.log(this + ": ticking state %d", ID);
		try{
			save();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		ID++;
		SPVSutils.log(this + ": moving new entities");
		for(Entity entity : entities){
			SPVSutils.log(entity + ": moving");
			for(int attempt = 0;attempt < 10;attempt++){
				if(setEntityPos(entity, entity.move())){
					break; // continue outer
				}
			}
		}
		updateObservers();
	}

	private void save() throws CloneNotSupportedException{
		//SPVSutils.log(this + ": saving");
		List<Entity> tmp = entities;
		List<Entity> newlist = new ArrayList<>();
		for(Entity entity : entities) newlist.add(entity.copy());
		entities = newlist;
		states.put(ID, (State) this.clone());
		entities = tmp;
		updateGrid();
	}

	private static State load(int stateID){
		if(stateID < 0) stateID = 0;
		//SPVSutils.log("- loading state %d of %d", ID, states.size());
		ID = stateID;
		return states.get(ID);
	}

	public static void prev(){
		current = State.load(ID - 1);
		updateObservers();
	}

	public static void next(){
		current.tick();
	}

	public Entity getEntityAt(int x, int y){
		return grid[x][y];
	}

	public void removeEntityAt(int x, int y){
		entities.remove(grid[x][y]);
		grid[x][y] = null;
		updateObservers();
	}

	/*
	===========================================================
	=   update methods
	===========================================================
	*/

	private void updateGrid(){
		for(int x = 0;x < width;x++){
			for(int y = 0;y < height;y++){
				grid[x][y] = null;
			}
		}
		for(Entity entity : entities){
			grid[entity.getX()][entity.getY()] = entity;
		}
	}

	public static void updateObservers(){
		SPVSutils.log(current + ": updating observer");
		Screen.updateTitle(ID);
		current.updateGrid();
		for(Observer obs : observers)
			obs.update();
	}

	public static void register(Observer observer){
		SPVSutils.log("registered");
		observers.add(observer);
	}

	@SuppressWarnings("unused")
	public static void unregister(Observer observer){
		observers.remove(observer);
	}
}

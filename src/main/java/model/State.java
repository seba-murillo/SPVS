package model;

import utils.SPVSLog;
import view.Screen;

import java.util.*;


public class State implements Cloneable{
	public final static int SURROUNDING_RADIUS = 3;

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
		updateGrid();
		if(x < 0 || x >= State.width) return false;
		if(y < 0 || y >= State.height) return false;
		if(grid[x][y] != null) return (grid[x][y] == entity);
		grid[x][y] = entity;
		entities.add(entity);
		updateObservers();
		return true;
	}
	// 15x15
	public Entity[][] getSurroundings(int x, int y){ // (x,y) = (9,9)
		Entity[][] nearby = new Entity[2 * SURROUNDING_RADIUS + 1][2 * SURROUNDING_RADIUS + 1];
		//int min_x = (x - SURROUNDING_RADIUS < 0) ? 0 : x - SURROUNDING_RADIUS;
		//int min_y = (y - SURROUNDING_RADIUS < 0) ? 0 : y - SURROUNDING_RADIUS;
		int min_x = Math.max(x - SURROUNDING_RADIUS, 0);
		int min_y = Math.max(y - SURROUNDING_RADIUS, 0);
		int max_x = (x + SURROUNDING_RADIUS > width) ? width : x + SURROUNDING_RADIUS + 1;
		int max_y = (y + SURROUNDING_RADIUS > height) ? height : y + SURROUNDING_RADIUS + 1;
		for(int i = 0;i < 3;i++){
			for(int j = 0;j < 3;j++){
				nearby[i][j] = new Void();
			}
		}
		for(int i = min_x;i < max_x;i++){ // 6 -> 12
			for(int j = min_y;j < max_y;j++){ // 6 -> 12
				nearby[i - min_x][j - min_y] = grid[i][j]; // 0->6
			}
		}
		return nearby;
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
		//Global.log("- ticking state %d", ID);
		try{
			save();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		ID++;
		updateGrid();
		//Global.log("- moving new entities");
		for(Entity entity : entities){
			entity.move();
		}
		updateObservers();
	}

	private void updateGrid(){
		for(int x = 0; x < width;x++){
			for(int y = 0;y < height;y++){
				grid[x][y] = null;
			}
		}
		for(Entity entity : entities){
			grid[entity.getX()][entity.getY()] = entity;
		}
	}

	private void save() throws CloneNotSupportedException{
		//Global.log("- saving state %d", ID);
		states.put(ID, (State) this.clone());
		List<Entity> newlist = new ArrayList<>(entities);
		for(Entity entity : entities) newlist.add(entity.copy());
		entities = newlist;
	}

	private static State load(int stateID){
		if(stateID < 0) stateID = 0;
		SPVSLog.log("- loading state %d of %d", ID, states.size());
		ID = stateID;
		State state = states.get(ID);
		state.print();
		return state;
	}
/*
    public static void loadState(int ID) {
        current = State.load(ID);
        updateObservers();
    }*/

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
	=   subject interface methods
	===========================================================
	*/
	public static void updateObservers(){
		Screen.updateTitle(ID);
		for(Observer obs : observers)
			obs.update();
	}

	public static void register(Observer observer){
		observers.add(observer);
	}

	@SuppressWarnings("unused")
	public static void unregister(Observer observer){
		observers.remove(observer);
	}

    /*
    ===========================================================
    =   test & debug functions
    ===========================================================
    */

	public void print(){
		SPVSLog.log("> %s:", this.toString());
		for(Entity entity : entities){
			SPVSLog.log("    %s [%d]: (%d,%d)", entity.toString(), entity.hashCode(), entity.getX(), entity.getX());
		}
		SPVSLog.log("\n");
	}

	public static void printall(){
		SPVSLog.log("PRINTING ALL");
		states.forEach((key, value) -> value.print());
		SPVSLog.log("\n");
		SPVSLog.log("PRINTED ALL");
	}

	public static void set(State newstate){
		current = newstate;
		updateObservers();
	}

	public State copy(){
		try{
			return (State) current.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		SPVSLog.log("FK FK FK");
		return null;
	}

}

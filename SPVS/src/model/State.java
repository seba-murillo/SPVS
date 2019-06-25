package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;


public class State implements Cloneable{

	public static final boolean				show_neighbours	= false;
	private static TreeMap<Integer, State>	states			= new TreeMap<Integer, State>();
	private static HashSet<Observer> observers = new HashSet<Observer>();
	private static int ID = 0;

	private ArrayList<Entity> entities = new ArrayList<Entity>();	
	private Entity[][] grid = new Entity[Game.MAX_X][Game.MAX_Y];

	public State() {
	}

	public State(String pattern){
		set(pattern);
	}

	private void set(String pattern){
		if("W1R3".equals(pattern)){ // 1 wolf, 3 rabbit
			addEntity(new Rabbit(), 4, 4);
			addEntity(new Rabbit(), 7, 2);
			addEntity(new Rabbit(), 2, 7);
			addEntity(new Wolf(), 8, 8);
		}
	}
	
	public boolean addEntity(Entity entity, int x, int y) {
		log("adding " + entity + " to pos (" + x + ", " + y + ")");
		if(x < 0 || x >= Game.MAX_X) return false;
		if(y < 0 || y >= Game.MAX_Y) return false;
		if(grid[x][y] != null) return false;
		grid[x][y] = entity;
		entities.add(entity);
		entity.setP(x, y);
		return true;
	}

	public void print(){
		log(String.format("> printing %s (%d states total)]:", this, states.size()));
		for(int x = 0;x < Game.MAX_X;x++){
			for(int y = 0;y < Game.MAX_Y;y++){
				if(grid[x][y] == null) continue;				
				log(String.format("(%d, %d) - %s", x, y, grid[x][y].toString()));
			}
		}
	}

	public static void printList(){
		log("@printList() - State:");
		for(Map.Entry<Integer, State> entry : states.entrySet()){
			entry.getValue().print();
		}
	}

	public State tick(){
		save();
		ID++;
		print();
		for(Entity entity : entities) {			
			entity.move();
		}
		update_observers();
		return this;
	}
	
	public Entity[][] getSurroundings(int pos_x, int pos_y){
		Entity[][] surrounding = new Entity[5][5];
		for(int x = pos_x - 2; x < (pos_x + 2); x++) {
			if(x < 0 || x > Game.MAX_X) continue;
			for(int y = pos_y - 2;y < (pos_y + 2); y++) {
				if(y < 0 || y > Game.MAX_Y) continue;
				if(pos_x == x && pos_y == y) continue;
				surrounding[x][y] = grid[x][y];
			}
		}
		return surrounding;
	}

	private void save(){
		log(String.format("State ID: %d saved", ID));
		try{
			states.put(ID, (State) this.clone());// save copy of state
		}
		catch(CloneNotSupportedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static State load(int state){
		if(Game.verbose) log(String.format("@load: loading ID: %d (%d states total)", ID, states.size()));		
		if(state < 0) state = 0;
		ID = state;
		log(String.format("State ID: %d loaded", ID));
		return states.get(ID);
	}

	@Override
	public String toString(){
		return String.format("State [%d]", ID);
	}

	public static State loadLast(){
		return State.load(ID - 1);
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}
	
	private void update_observers() {
		for(Observer o : observers)
			o.onUpdate(grid);
	}
	
	public void register_observer(Observer obj) {
		observers.add(obj);
	}
}






















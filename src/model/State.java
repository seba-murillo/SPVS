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
		else if("full_test".equals(pattern)){
			// stone A
			addEntity(new Stone(), 3, 4);
			addEntity(new Stone(), 4, 3);
			addEntity(new Stone(), 4, 4);
			addEntity(new Stone(), 5, 3);
			addEntity(new Stone(), 5, 4);
			addEntity(new Stone(), 5, 5);
			addEntity(new Stone(), 5, 6);
			addEntity(new Stone(), 6, 5);
			addEntity(new Stone(), 6, 6);
			// stone B
			addEntity(new Stone(), 12, 17);
			addEntity(new Stone(), 13, 17);
			addEntity(new Stone(), 13, 18);
			// stone C
			addEntity(new Stone(), 20, 15);
			addEntity(new Stone(), 21, 13);
			addEntity(new Stone(), 21, 14);
			addEntity(new Stone(), 21, 15);
			addEntity(new Stone(), 22, 13);
			addEntity(new Stone(), 22, 14);
			addEntity(new Stone(), 22, 15);
			addEntity(new Stone(), 23, 14);
			addEntity(new Stone(), 23, 15);
			addEntity(new Stone(), 23, 16);
			addEntity(new Stone(), 24, 15);
			// Tree A
			addEntity(new Tree(), 6, 11);
			addEntity(new Tree(), 7, 10);
			addEntity(new Tree(), 7, 11);
			addEntity(new Tree(), 7, 12);
			addEntity(new Tree(), 8, 11);
			addEntity(new Tree(), 8, 12);
			addEntity(new Tree(), 8, 13);
			addEntity(new Tree(), 9, 13);
			// Tree B
			addEntity(new Tree(), 11, 15);
			addEntity(new Tree(), 12, 16);
			addEntity(new Tree(), 13, 15);
			addEntity(new Tree(), 13, 16);
			addEntity(new Tree(), 14, 16);
			addEntity(new Tree(), 14, 17);
			// Tree C			
			addEntity(new Tree(), 17, 10);
			addEntity(new Tree(), 17, 11);
			addEntity(new Tree(), 18, 10);
			addEntity(new Tree(), 18, 11);
			addEntity(new Tree(), 19, 11);
			// Tree D
			addEntity(new Tree(), 21, 5);
			addEntity(new Tree(), 23, 7);
			addEntity(new Tree(), 24, 3);
			addEntity(new Tree(), 25, 3);
			addEntity(new Tree(), 26, 3);
			addEntity(new Tree(), 27, 4);
			// Plants
			addEntity(new Plant(), 4, 24);
			addEntity(new Plant(), 4, 28);
			addEntity(new Plant(), 5, 20);
			addEntity(new Plant(), 5, 22);
			addEntity(new Plant(), 7, 17);
			addEntity(new Plant(), 7, 18);
			addEntity(new Plant(), 8, 5);
			addEntity(new Plant(), 8, 5);
			addEntity(new Plant(), 9, 20);
			addEntity(new Plant(), 9, 25);
			addEntity(new Plant(), 13, 4);
			addEntity(new Plant(), 13, 4);
			addEntity(new Plant(), 13, 6);
			addEntity(new Plant(), 13, 7);
			addEntity(new Plant(), 13, 22);
			addEntity(new Plant(), 14, 6);
			addEntity(new Plant(), 15, 4);
			addEntity(new Plant(), 17, 20);
			addEntity(new Plant(), 19, 16);
			addEntity(new Plant(), 21, 10);
			addEntity(new Plant(), 25, 4);
			addEntity(new Plant(), 25, 18);
			addEntity(new Plant(), 25, 5);
			addEntity(new Plant(), 26, 15);
			addEntity(new Plant(), 26, 18);
			addEntity(new Plant(), 28, 5);
			addEntity(new Plant(), 28, 7);
			addEntity(new Plant(), 28, 9);
			// rabbits
			addEntity(new Rabbit(), 6, 24);
			addEntity(new Rabbit(), 13, 10);
			addEntity(new Rabbit(), 18, 8);
			addEntity(new Rabbit(), 19, 3);
			addEntity(new Rabbit(), 24, 21);
			addEntity(new Rabbit(), 24, 24);
			addEntity(new Rabbit(), 27, 22);
			// wolfs
			addEntity(new Wolf(), 1, 14);
			addEntity(new Wolf(), 29, 15);
			// bear
			addEntity(new Bear(), 19, 22);
		}
	}
	
	public boolean addEntity(Entity entity, int x, int y) {
		//log("adding " + entity + " to pos (" + x + ", " + y + ")");
		if(x < 0 || x >= Game.MAX_X) return false;
		if(y < 0 || y >= Game.MAX_Y) return false;
		if(grid[x][y] != null) return false;
		grid[x][y] = entity;
		entities.add(entity);
		entity.setP(x, y);
		return true;
	}

	public void print(){
		//log(String.format("> printing %s (%d states total)]:", this, states.size()));
		for(int x = 0;x < Game.MAX_X;x++){
			for(int y = 0;y < Game.MAX_Y;y++){
				if(grid[x][y] == null) continue;				
				log(String.format("(%d, %d) - %s", x, y, grid[x][y].toString()));
			}
		}
	}

	public static void printList(){
		//log("@printList() - State:");
		for(Map.Entry<Integer, State> entry : states.entrySet()){
			entry.getValue().print();
		}
	}

	public State tick(){
		save();
		ID++;
		//print();
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
		//log(String.format("State ID: %d saved", ID));
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
	
	public void update_observers() {
		for(Observer o : observers)
			o.onUpdate(grid);
	}
	
	public void register_observer(Observer obj) {
		observers.add(obj);
	}
}






















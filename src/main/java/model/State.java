package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.Controller;


public class State implements Cloneable{

	public static final boolean	show_neighbours		= false;
	public static final int		MAX_MOVE_ATTEMPT	= 10;

	public static int		MAX_DURATION;
	public static boolean	done	= false;

	private static State current = null;

	private static TreeMap<Integer, State>	states		= new TreeMap<Integer, State>();
	private static HashSet<Observer>		observers	= new HashSet<Observer>();
	private static int						ID			= 0;

	private ArrayList<Entity>	entities	= new ArrayList<Entity>();
	private Entity[][]			grid		= new Entity[Controller.MAX_X][Controller.MAX_Y];

	public State(int duration){
		MAX_DURATION = duration;
		if(current == null) current = this;
		if(Game.debug){
			addEntity(new Wolf(), 3, 3);
		}
	}

	public static void reset(){
		current = null;
		new State(1000);
	}

	public boolean addEntity(Entity entity, int x, int y){
		//log("adding " + entity + " to pos (" + x + ", " + y + ")");
		if(!setEntityPos(entity, x, y)) return false;
		entities.add(entity);
		return true;
	}

	private boolean setEntityPos(Entity entity, int x, int y){
		if(x < 0 || x >= Controller.MAX_X) return false;
		if(y < 0 || y >= Controller.MAX_Y) return false;
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
		update_observers();
		return true;
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

	/*       -
	 * 	   -[1] [4] [7]+
	 * 		[2] [0] [6]
	 * 		[3] [8] [5]
	 *       +
	 */
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

	public void print(){
		//log(String.format("> printing %s (%d states total)]:", this, states.size()));
		log(this.toString() + ":");
		for(int x = 0;x < Controller.MAX_X;x++){
			for(int y = 0;y < Controller.MAX_Y;y++){
				if(grid[x][y] == null) continue;
				log(String.format("\t(%d, %d) - %s", x, y, grid[x][y].toString()));
			}
		}
	}

	public static void printList(){
		log("@printList()");
		int z = 0;
		for(Map.Entry<Integer, State> entry : states.entrySet()){
			System.out.print("ID " + z++ + " - ");
			entry.getValue().print();
		}
	}

	public State tick(){
		if(ID >= MAX_DURATION && MAX_DURATION > 0){
			JPanel message = new JPanel();
			message.add(new JLabel("Simulacion terminada (" + MAX_DURATION + " turnos)"));
			JOptionPane.showMessageDialog(null, message, "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
			done = true;
			return this;
		}
		save();
		ID++;
		for(Entity entity : entities){
			for(int attempt = 0;attempt < MAX_MOVE_ATTEMPT;attempt++){
				if(setEntityPos(entity, entity.move())){
					break; // aka continue outer
				}
			}
		}
		update_observers();
		return this;
	}

	public Entity[][] getSurroundings(int pos_x, int pos_y){
		//log("@getSurroundings(" + pos_x + ", " + pos_y + ")");
		Entity[][] surroundings = new Entity[3][3];
		int start_x = pos_x - 1; // 2
		int start_y = pos_y - 1; // 2
		for(int x = start_x;x <= (pos_x + 1);x++){ // 2 -> 4
			if(x < 0 || x >= Controller.MAX_X) continue;
			for(int y = start_y;y <= (pos_y + 1);y++){// 2 -> 4
				if(y < 0 || y >= Controller.MAX_Y) continue;
				if(pos_x == x && pos_y == y) continue;
				if(x - start_x < 0 || x - start_x >= Controller.MAX_X) continue;
				if(y - start_y < 0 || y - start_y >= Controller.MAX_Y) continue;
				surroundings[x - start_x][y - start_y] = grid[x][y];
			}
		}
		return surroundings;
	}

	private void save(){
		//log(String.format("State ID: %d saved", ID));
		try{
			//State copy = (State) this.clone();
			State copy = this.copy_state();
			states.put(ID, copy); // save copy of state
		}
		catch(Exception e){
			log("ERROR @ State.save() : failed clonning");
		}
	}

	public static State load(int state){
		log(String.format("@load: loading ID: %d (%d states total)", ID, states.size()));
		if(state < 0) state = 0;
		ID = state;
		//log(String.format("State ID: %d loaded", ID));
		current = states.get(ID);
		update_observers();
		return current;
	}

	public static State getCurrent(){
		//log("current = " + current + "\n");
		return current;
	}

	@Override
	public String toString(){
		return String.format("State [%d]", ID);
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

	public static void update_observers(){
		for(Observer o : observers)
			o.onUpdate(current.grid);
	}

	public static void register_observer(Observer obj){
		observers.add(obj);
		update_observers();
	}

	public static void loadState(int ID){
		current = State.load(ID);
		State.update_observers();
	}

	public static void loadLast(){
		current = State.load(ID - 1);
		log("current state is now " + current);
		State.update_observers();
		//current.print();
	}

	public static void nextState(){
		current = current.tick();
		log("current state is now " + current);
		//current.print();
	}

	private State copy_state(){
		State copy = new State(MAX_DURATION);
		copy.entities = new ArrayList<>();
		copy.grid = new Entity[Controller.MAX_X][Controller.MAX_Y];
		for(Entity ent : entities){
			copy.entities.add(ent.copy_entity());
		}
		for(int x = 0;x < Controller.MAX_X;x++){
			for(int y = 0;y < Controller.MAX_Y;y++){
				//copy.grid[x][y] = null;
				if(this.grid[x][y] != null){
					copy.grid[x][y] = this.grid[x][y].copy_entity();
				}
			}
		}
		return copy;
	}

	public Entity getEntityAt(int x, int y){
		return grid[x][y];
	}

	public void removeEntityAt(int x, int y){
		entities.remove(grid[x][y]);
		grid[x][y] = null;
		State.update_observers();
	}
}

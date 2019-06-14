package model;

import java.util.Map;
import java.util.TreeMap;


public class State{

	public static final boolean				show_neighbours	= false;
	private static TreeMap<Integer, State>	states			= new TreeMap<Integer, State>();

	private static int state_count = 0;

	public Cell[][] cell_array = new Cell[Game.grid_rows][Game.grid_cols];

	private int		ID;
	private String	pattern	= "none";

	public State(Cell[][] CELL){
		this.ID = state_count++;
		// copy cell array state
		for(int x = 0;x < Game.grid_rows;x++){
			for(int y = 0;y < Game.grid_cols;y++){
				this.cell_array[x][y] = new Cell(CELL[x][y].getState());
			}
		}
		config_neighbours();
		save();
	}

	public State(String pattern){
		this.ID = state_count++;
		this.pattern = pattern;
		set(pattern);
		fill_cell_array();
		config_neighbours();
		save();
	}

	private void set(String pattern){
		if("pos_1".equals(pattern)){
			cell_array[0][0] = new Cell(Game.ALIVE);
			cell_array[1][0] = new Cell(Game.ALIVE);
			cell_array[2][1] = new Cell(Game.ALIVE);
		}
		if("row_1".equals(pattern)){
			cell_array[Game.grid_rows / 2][Game.grid_cols / 2 - 1] = new Cell(Game.ALIVE);
			cell_array[Game.grid_rows / 2][Game.grid_cols / 2 + 0] = new Cell(Game.ALIVE);
			cell_array[Game.grid_rows / 2][Game.grid_cols / 2 + 1] = new Cell(Game.ALIVE);
		}
		//CREACION DE POSICIONES
		if("exp_1".equals(pattern)){
			int x = Game.grid_rows / 2;
			int y = Game.grid_cols / 2;
			cell_array[x + 0][y - 1] = new Cell(Game.ALIVE);
			cell_array[x + 0][y + 0] = new Cell(Game.ALIVE);
			cell_array[x + 0][y + 1] = new Cell(Game.ALIVE);
			cell_array[x - 1][y + 0] = new Cell(Game.ALIVE);
		}
		if("tst_1".equals(pattern)){
			cell_array[1][0] = new Cell(Game.ALIVE);
			cell_array[1][1] = new Cell(Game.ALIVE);
			cell_array[1][2] = new Cell(Game.ALIVE);
		}
		if("count".equals(pattern)){
			cell_array[0][0] = new Cell(Game.ALIVE);
		}
	}

	public void print(){
		log(String.format("> printing %s (%d states total)]:", this, states.size()));
		for(int x = 0;x < Game.grid_rows;x++){
			for(int y = 0;y < Game.grid_cols;y++){
				int cell_state = cell_array[x][y].getState();
				if(cell_state == 0) System.out.print("   ");
				else
					System.out.print(String.format("%3d", cell_state));
			}
			Game.log("\n");
		}
	}

	public static void printList(){
		log("@printList() - State:");
		for(Map.Entry<Integer, State> entry : states.entrySet()){
			entry.getValue().print();
		}
	}

	public State tick(){
		return new State(cell_array).evolve();
	}

	public State evolve(){
		if("count".equals(pattern)){
			cell_array[ID][ID].setNext(Game.ALIVE);
			return this;
		}
		for(int x = 0;x < Game.grid_rows;x++){
			for(int y = 0;y < Game.grid_cols;y++){
				cell_array[x][y].getNext();
			}
		}
		for(int x = 0;x < Game.grid_rows;x++){
			for(int y = 0;y < Game.grid_cols;y++){
				cell_array[x][y].tick();
			}
		}
		return this;
	}

	private void save(){
		if(Game.verbose) log(String.format("@save() - saved ID: %d", ID));
		states.put(ID, this);
	}

	// TODO this will throw exception for invalid ID
	public static State load(int ID){
		if(Game.verbose) log(String.format("@load: loading ID: %d (%d states total)", ID, states.size()));
		//Game.log(String.format("\tstate_count: %d\n", state_count));
		if(ID < 0) ID = 0;
		State result = states.get(ID);
		state_count = ID + 1;
		return result;
	}

	@Override
	public String toString(){
		return String.format("State [%d]", ID);
	}

	public static State loadLast(){
		return State.load(state_count - 2);
	}

	public Cell[][] getState(){
		return cell_array;
	}

	public int getState(int x, int y){
		return cell_array[x][y].getState();
	}

	private void fill_cell_array(){
		// fill cell array
		for(int x = 0;x < Game.grid_rows;x++){
			for(int y = 0;y < Game.grid_cols;y++){
				if(cell_array[x][y] == null) cell_array[x][y] = new Cell(Game.DEAD);
			}
		}
	}

	private void config_neighbours(){
		// configure neighbours
		for(int x = 0;x < Game.grid_rows;x++){
			for(int y = 0;y < Game.grid_cols;y++){
				int pos = -1;
				Cell[] neighbours = new Cell[Cell.MAX_NEIGHBOURS];
				for(int dx = x - 1;dx < x + 2;dx++){
					for(int dy = y - 1;dy < y + 2;dy++){
						pos++;
						if(dx < 0 || dy < 0) continue;
						if(dx >= Game.grid_rows || dy >= Game.grid_cols) continue;
						neighbours[pos] = cell_array[dx][dy];
					}
				}
				cell_array[x][y].setNeighbours(neighbours);

				if(show_neighbours){
					int count = 0;
					log(String.format("@setNeighbours of Cell[%d][%d]:", x, y));
					for(int i = 0;i < Cell.MAX_NEIGHBOURS;i++){
						if(neighbours[i] == null){
							log(String.format("neighbours[%d] = NULL", i));
						}
						else{
							log(String.format("neighbours[%d] = OK", i));
							count++;
						}
					}
					log(String.format("total: %d neighbours\n\n", count - 1));
				}
			}
		}
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}
}

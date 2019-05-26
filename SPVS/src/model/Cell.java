package model;

public class Cell{

	public static final int MAX_NEIGHBOURS = 9;

	public static final boolean show_events = false;

	private Cell[] neighbours = new Cell[MAX_NEIGHBOURS];

	private int state, next_state;

	private String rule = Rule.STANDARD;

	public Cell(int state){
		this.state = state;
	}

	public Cell(int state, String rule){
		this.state = state;
		this.rule = rule;
	}

	public void setNeighbours(Cell[] neighbours){

		this.neighbours = neighbours;
	}

	public void setState(int state){
		if(this.state == state) return;
		if(this.state == Game.ALIVE){
			this.state = Game.DEAD;
			if(show_events) log(this + " has been killed\n");
		}
		else if(this.state == Game.DEAD){
			this.state = Game.ALIVE;
			if(show_events) log(this + " has been created\n");
		}
	}

	public int getState(){
		return state;
	}

	public void getNext(){
		next_state = Rule.destiny(neighbours, rule);
	}

	//TODO delete, used for testing
	public void setNext(int next){
		next_state = next;
	}

	public void tick(){
		this.setState(next_state);
	}

	public static void log(Object message){
		System.out.print(message.toString());
	}
}

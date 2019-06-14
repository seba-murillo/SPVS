package model;

import view.Screen;

public class Game{

	public static final boolean verbose = false;
	public static final int	DEAD	= 0;
	public static final int	ALIVE	= 1;
	public  static int	grid_rows;
	public  static int	grid_cols;
	private static State current;
	
	public Game(int ancho, int largo) {
		// TODO Auto-generated constructor stub
		grid_rows = ancho;
		grid_cols = largo;
		current = new State("exp_1");
		//current = new State("count");
		new Screen();
		new Updater();
	}

	public static void error(Object message){
		System.err.print(message.toString());
	}

	public static State getCurrentState(){
		//log("current = " + current + "\n");
		return current;
	}

	public static void loadLastState(){
		current = State.loadLast();
		//log("current state is now " + current + "\n");
	}

	public static void loadState(int ID){
		current = State.load(ID);
	}

	public static void log(Object message){
		System.out.print(message.toString());
	}

	public static void nextState(){
		current = current.tick();
	}

	public static void setCurrent(State st){
		current = st;
	}
}

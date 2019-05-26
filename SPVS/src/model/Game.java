package model;

import view.Screen;


public class Game{

	public static final boolean verbose = false;
// mota
	public static final int	DEAD	= 0;
	public static final int	ALIVE	= 1;

	//	public static int	grid_rows	= 20;
	//	public static int	grid_cols	= 40;

	public static int	grid_rows	= 20;
	public static int	grid_cols	= 40;

	private static State current;

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

	public static void main(String[] args){
		current = new State("exp_1");
		//current = new State("count");
		new Screen();
		new Updater();
	}

	public static void nextState(){
		current = current.tick();
	}

	public static void setCurrent(State st){
		current = st;
	}
}

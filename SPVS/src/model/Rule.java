package model;

public class Rule{

	public static final String STANDARD = "standard";

	public Rule(String string){
		// TODO Auto-generated constructor stub
	}

	/* 
	 * STANDARD RULES
	 * 
	 * if(adyacent < 2)  KILL
	 * if(adyacent > 3)  KILL
	 * if(adyacent == 2) STAY ALIVE
	 * if(adyacent == 3) CREATE
	 */
	public static int destiny(Cell[] neighbours, String rule){
		if("standard".equals(rule)){
			int alive = getAliveNeighbours(neighbours);
			if(alive < 2) return Game.DEAD;
			else if(alive > 3) return Game.DEAD;
			else if(alive == 3) return Game.ALIVE;
			else
				return neighbours[4].getState();
		}
		return Game.DEAD;
	}

	private static int getAliveNeighbours(Cell[] neighbours){
		int result = 0;
		for(int i = 0;i < 9;i++){
			if(i == 4) continue; // skip center
			if(neighbours[i] == null) continue;
			if(neighbours[i].getState() == Game.ALIVE) result++;
		}
		return result;
	}
}

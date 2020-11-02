package controller;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import model.Bear;
import model.Entity;
import model.Plant;
import model.Rabbit;
import model.State;
import model.Stone;
import model.Tree;
import model.Wolf;


public class Controller{
	
	public static int MAX_X;
	public static int MAX_Y;

	public static void showMenu(int x, int y, JPanel panel){ // right click
		State state = getCurrentState();
		Entity entity = state.getEntityAt(x, y);
		JPopupMenu add_menu = new JPopupMenu("menu");
		if(entity == null){
			add_menu.add("add plant").addActionListener(e-> state.addEntity(new Plant(), x, y));
			add_menu.add("add rabbit").addActionListener(e-> state.addEntity(new Rabbit(), x, y));
			add_menu.add("add wolf").addActionListener(e-> state.addEntity(new Wolf(), x, y));
			add_menu.add("add bear").addActionListener(e-> state.addEntity(new Bear(), x, y));
			add_menu.add("add stone").addActionListener(e-> state.addEntity(new Stone(), x, y));
			add_menu.add("add tree").addActionListener(e-> state.addEntity(new Tree(), x, y));
		}
		else{
			add_menu.add("remove").addActionListener(e-> state.removeEntityAt(x, y));
		}
		add_menu.show(panel, 20, 10);
	}
	
	public static State getCurrentState() {
		return (State.getCurrent());
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}
}

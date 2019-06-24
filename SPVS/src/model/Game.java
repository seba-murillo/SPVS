package model;

import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.JOptionPane;
import view.Screen;


public class Game{

	public static final boolean	verbose	= false;
	public static final int		DEAD	= 0;
	public static final int		ALIVE	= 1;
	public static int			grid_rows;
	public static int			grid_cols;
	private static State		current;

	public static void main(String[] args){		
		JTextField field_width = new JTextField(3);
		JTextField field_height = new JTextField(3);	
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
		input.add(new JLabel("Ingrese:"));
		input.add(new JLabel("ancho: "));
		input.add(field_width);
		input.add(new JLabel("alto: "));
		input.add(field_height);
		int width = 5, height = 5;
		JOptionPane.showMessageDialog(null, input, "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
		while(true) {
			try {
				width = Integer.parseInt(field_width.getText());
				height = Integer.parseInt(field_height.getText());
				break;
			}catch(Exception e) {				
				JOptionPane.showMessageDialog(null, input, "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		width = (width < 10) ? 10 : width;
		height = (height < 10) ? 10 : height;
		new Game(width, height);
	}

	private Game(int width, int height){
		grid_cols = width;
		grid_rows = height;
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

package model;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.Controller;
import view.Screen;


public class Game{

	public static final boolean DEBUG = false;
	private static final int DEFAULT_DURATION = 1000;
	private static final int MIN_WIDTH = 10;
	private static final int MAX_WIDTH = 50;
	private static final int MIN_HEIGHT = 10;
	private static final int MAX_HEIGHT = 30;

	private static Game current = null;

	public static void main(String[] args){
		JTextField field_width = new JTextField(3);
		JTextField field_height = new JTextField(3);
		JTextField field_duration = new JTextField(3);
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
		input.add(new JLabel("Ingrese:"));
		input.add(new JLabel("ancho (min 10): "));
		input.add(field_width);
		input.add(new JLabel("alto (min 10): "));
		input.add(field_height);
		input.add(new JLabel("duracion: "));
		input.add(field_duration);
		int width, height, duration;
		JOptionPane.showMessageDialog(null, input, "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
		while(true){
			try{
				width = Integer.parseInt(field_width.getText());
				height = Integer.parseInt(field_height.getText());
				duration = Integer.parseInt(field_duration.getText());
				break;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, input, "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// check min/max width
		width = Math.max(width, MIN_WIDTH);
		width = Math.min(width, MAX_WIDTH);
		// check min/max height
		height = Math.max(height, MIN_HEIGHT);
		height = Math.min(height, MAX_HEIGHT);
		// check valid duration
		duration = (duration < 0) ? DEFAULT_DURATION : duration;
		// small 'help'
		JOptionPane.showMessageDialog(null, "Utilice click derecho para agregar animales", "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
		Game.start(width, height, duration);
	}

	private Game(int width, int height, int duration){
		Controller.MAX_Y = width;
		Controller.MAX_X = height;
		State.initialize(width, height, duration);
		Screen.create(width, height);
	}
	
	public static Game start(int width, int height, int duration) {
		if(current != null) return current;
		current = new Game(width, height, duration);
		return current;
	}
}

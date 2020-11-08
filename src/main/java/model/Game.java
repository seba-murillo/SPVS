package model;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.Controller;
import view.Screen;


public class Game{

	public static final boolean debug = false;
	private static boolean started = false;

	public static void main(String[] args){
		if(debug){
			new Game(10, 10, 10);
			return;
		}
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
		width = Math.max(width, 10);
		height = Math.max(height, 10);
		width = Math.min(width, 50);
		height = Math.min(height, 50);
		duration = (duration < 0) ? 99999 : duration;
		JOptionPane.showMessageDialog(null, "Utilice click derecho para agregar animales", "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
		new Game(width, height, duration);
	}

	private Game(int width, int height, int duration){
		Controller.MAX_Y = width;
		Controller.MAX_X = height;
		new State(duration);
		new Screen(width, height);
		new Updater();
	}
	
	public static void start(int width, int height, int duration) {
		if(started) return;
		started = true;
		new Game(width, height, duration);
	}

	public static void error(Object message){
		System.err.print(message.toString());
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

}

package view;

import model.Observer;
import model.State;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Screen{

	public static boolean showGrid = true;

	private static JFrame frame = null;

	private static JPanel	board;
	private static JPanel	control;

	private Screen(int width, int height){
		frame = new JFrame("Simulador de Vida Salvaje");
		frame.setLocationRelativeTo(null); // center
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		//setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new GamePanel(width, height);
		control = new ControlPanel();
		frame.add(board);
		frame.add(control);
		frame.pack();
		frame.setVisible(true);
	}


	public static Screen create(int width, int height){
		return new Screen(width, height);
	}

	public static void draw(){
		frame.repaint();
	}

	@SuppressWarnings("unused")
	private static void error(Object message){
		System.err.println(message.toString());
	}

	@SuppressWarnings("unused")
	private static void log(Object message){
		System.out.println(message.toString());
	}

	public static void screenshot(){
		LocalTime time = LocalTime.now();
		String filename = String.format("screenshot_%d-%d-%d.png", time.getHour(), time.getMinute(), time.getSecond());
		BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
		//Container container = frame.getContentPane();
		//container.paint(image.getGraphics());
		frame.paintAll(image.getGraphics());
		try{
			ImageIO.write(image, "png", new File(filename));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void showEndScreen(int duration) {
		JPanel message = new JPanel();
		message.add(new JLabel("Simulacion terminada (" + duration + " turnos)"));
		JOptionPane.showMessageDialog(null, message, "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void updateTitle(int id){
		frame.setTitle(String.format("SPVS - [%d]", id));
	}
}

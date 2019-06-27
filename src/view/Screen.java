package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Screen{

	public static boolean showGrid = true;

	private static final JFrame frame = new JFrame("Simulador de Vida Salvaje");

	private static JPanel	board;
	private static JPanel	control;

	/*public Screen(Timeout reloj){
		//init
		frame.setLocationRelativeTo(null); // center
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		//setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new GamePanel();
		control = new ControlPanel(reloj);
		frame.add(board);
		frame.add(control);
		frame.pack();
		frame.setVisible(true);
		//Game.log("Screen created\n");
		//screenshot();
	}*/

	public Screen(Timeout reloj) {
		// TODO Auto-generated constructor stub
		frame.setLocationRelativeTo(null); // center
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		//setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new GamePanel();
		control = new ControlPanel(reloj);
		frame.add(board);
		frame.add(control);
		frame.pack();
		frame.setVisible(true);
		//Game.log("Screen created\n");
		//screenshot();
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
	/*
	public static void screenshot() {
		LocalTime time = LocalTime.now();
		String filename = String.format("screenshot_v2_%d-%d-%d.png", time.getHour(), time.getMinute(), time.getSecond());
		try{
			Robot robot = new Robot();
			BufferedImage Image = robot.createScreenCapture(frame.getBounds());
			ImageIO.write(Image, "png", new File(filename));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
}

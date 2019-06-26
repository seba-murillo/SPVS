package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CellPanel extends JPanel{

	public static final boolean show_border = true;
	//public static final boolean	show_events		= false;
	public static final boolean show_painting = false;

	public static final Color	COLOR_ALIVE		= Color.RED;
	public static final Color	COLOR_DEAD		= Color.BLUE;
	public static final Color	COLOR_BORDER	= Color.BLACK;
	public static final Color 	COLOR_ESP1		= Color.ORANGE;
	public static final Color 	COLOR_ESP2		= Color.GREEN;
	public static final int	height	= 15;
	public static final int	width	= 15;
	

	private BufferedImage img;

	public CellPanel(){
		super();
		if(show_border) this.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
		setBackground(COLOR_BORDER);
		try{
			img = ImageIO.read(new File("img/null.png"));
		}
		catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void log(Object message){
		System.out.print(message.toString());
	}
	
	public void setIcon(BufferedImage icon) {
		if(icon != null) {
			log("panel changed");
		}
		img = icon;
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(CellPanel.width, CellPanel.height);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, width, height, null, null);
		/*
		int state = getState();
		if(state == Game.ALIVE) g.setColor(COLOR_ALIVE);
		else if(state == Game.DEAD) g.setColor(COLOR_DEAD);
		else
			g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
		if(show_painting) log(String.format("@paintComponent() %s @ (%d, %d)\n", this, getX(), getY()));
		*/
		
	}

	@SuppressWarnings("unused")
	private Color getRandomColor(){
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		return new Color(r, g, b);
	}
}
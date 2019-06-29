package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CellPanel extends JPanel{

	public static final boolean	show_border			= true;
	public static final boolean	show_events			= false;
	public static final boolean	show_painting		= false;
	public static final Color	COLOR_BACKGROUND	= new Color(15, 90, 0);
	public static final Color	COLOR_BORDER		= Color.BLACK;
	public static final int		height				= 30;
	public static final int		width				= 30;

	private BufferedImage img;

	public CellPanel(){
		super();
		if(show_border) this.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
		setBackground(COLOR_BACKGROUND);
		img = null;
	}

	public static void log(Object message){
		System.out.print(message.toString());
	}

	public void setIcon(BufferedImage icon){
		img = icon;
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(CellPanel.width, CellPanel.height);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(img != null) g.drawImage(img, 0, 0, width, height, null, null);
	}
}
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import Controller.Controller;


@SuppressWarnings("serial")
public class CellPanel extends JPanel implements MouseListener{

	public static final boolean	show_border			= true;
	public static final boolean	show_events			= false;
	public static final boolean	show_painting		= false;
	public static final Color	COLOR_BACKGROUND	= new Color(15, 90, 0);
	public static final Color	COLOR_BORDER		= Color.BLACK;
	public static final int		height				= 30;
	public static final int		width				= 30;

	private int				x, y;
	private BufferedImage	img;

	public CellPanel(int x, int y){
		super();
		if(show_border) this.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));
		setBackground(COLOR_BACKGROUND);
		img = null;
		this.x = x;
		this.y = y;
		addMouseListener(this);
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

	@Override
	public void mouseClicked(MouseEvent event){
		int button = event.getButton();
		//log(String.format("@CellPanel Mouse Event @ panel (%d, %d) - ID: %d\n", x, y, button));
		if(button == MouseEvent.BUTTON1){ // left click
			Controller.showMenu1(x, y, this);
		}
		else if(button == MouseEvent.BUTTON3){ // right click
			Controller.showMenu(x, y, this);
		}
		// panels[y][x].setBackground(new Color(100,100,100));
	}

	@Override
	public void mouseEntered(MouseEvent arg0){
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0){
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0){
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0){
		// TODO Auto-generated method stub

	}
}
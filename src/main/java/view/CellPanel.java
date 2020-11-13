package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import model.*;


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
		/*
		log(String.format("@CellPanel Mouse Event @ panel (%d, %d) - ID: %d\n", x, y, button));		
		if(button == MouseEvent.BUTTON1){ // left click
			Controller.showMenu1(x, y, this);
		}
		else if(button == MouseEvent.BUTTON3){ // right click
		*/
		if(button == MouseEvent.BUTTON3){ // right click
			showMenu(x, y);
		}
		// panels[y][x].setBackground(new Color(100,100,100));
	}

	private void showMenu(int x, int y){ // right click
		State state = State.getCurrent();
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
		add_menu.show(this, 20, 10);
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
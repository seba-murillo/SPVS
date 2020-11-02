package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import model.Entity;
import model.Observer;
import model.State;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Observer{

	CellPanel[][] panels;

	public GamePanel(int width, int height){
		super();
		panels = new CellPanel[width][height];

		for(int x = 0;x < width;x++){
			for(int y = 0;y < height;y++){
				panels[x][y] = new CellPanel(x, y);
				this.add(panels[x][y]);
			}
		}
		setLayout(new GridLayout(width, height));
		State.register_observer(this);
		//addMouseListener(this);
		log("GamePanel created\n");
	}

	/*
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(Game.MAX_X * width, Game.MAX_Y * height);
	}
	*/

	public static void log(Object message){
		System.out.print(message.toString());
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	@Override
	public void onUpdate(Entity[][] state){
		for(int x = 0;x < panels.length;x++){
			for(int y = 0;y < panels[0].length;y++){
				if(state[x][y] == null){
					panels[x][y].setIcon(null);
					panels[x][y].setToolTipText(null);
					continue;
				}
				panels[x][y].setIcon(state[x][y].getIcon());
				panels[x][y].setToolTipText(state[x][y].getInfo());
			}
		}
		repaint();
	}
	/*	
	@Override
	public void mouseClicked(MouseEvent event){
		int x = event.getX() / CellPanel.width;
		int y = event.getY() / CellPanel.height;
		int button = event.getButton();
		log(String.format("@GamePanel Mouse Event @ panel (%d, %d) - ID: %d\n", x, y, button));
		
		if(button == MouseEvent.BUTTON1) { // left click
			Controller.showMenu1(x, y, panels[y][x]);
		}
		else if(button == MouseEvent.BUTTON3) { // right click
			Controller.showMenu2(x, y, panels[y][x]);
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
	*/
}

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
				panels[x][y] = new CellPanel();
				this.add(panels[x][y]);
			}
		}		
		setLayout(new GridLayout(width, height));
		State.getCurrent().register_observer(this);
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
				if(state[x][y] == null) continue;
				panels[x][y].setIcon(state[x][y].getIcon());
			}
		}
		repaint();
	}
}

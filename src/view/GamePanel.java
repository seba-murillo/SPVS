package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import model.Entity;
import model.Game;
import model.Observer;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Observer{
	
	CellPanel[][] panels = new CellPanel[Game.MAX_X][Game.MAX_Y];
	
	public GamePanel(){
		super();
		Game.getCurrentState().register_observer(this);
		for(int x = 0;x < Game.MAX_X;x++){
			for(int y = 0;y < Game.MAX_Y;y++){
				panels[x][y] = new CellPanel();
				this.add(panels[x][y]);
			}
		}
		Game.log("GamePanel created\n");
		// cells
		setLayout(new GridLayout(Game.MAX_X, Game.MAX_Y));
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

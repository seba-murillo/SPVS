package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.List;
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
		State.register(this);
		log("GamePanel created\n");
	}

	public static void log(Object message){
		System.out.print(message.toString());
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	@Override
	public void update(){
		// TODO use entities list instead of grid
		Entity[][] grid = State.getGrid();
		List<Entity> entities = State.getEntities();
		for(int x = 0;x < panels.length;x++){
			for(int y = 0;y < panels[0].length;y++){
				if(grid[x][y] == null){
					panels[x][y].setIcon(null);
					panels[x][y].setToolTipText(null);
					continue;
				}
				panels[x][y].setIcon(grid[x][y].getIcon());
				panels[x][y].setToolTipText(grid[x][y].getInfo());
			}
		}
		repaint();
	}
}

package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import model.Game;


@SuppressWarnings("serial")
public class GamePanel extends JPanel{

	public GamePanel(){
		super();
		Game.log("GamePanel created\n");
		// cells
		setLayout(new GridLayout(Game.grid_rows, Game.grid_cols));
		//Cell[][] cell_array = Game.getCurrentState().getState();
		for(int x = 0;x < Game.grid_rows;x++){
			for(int y = 0;y < Game.grid_cols;y++){
				CellPanel cell_panel = new CellPanel(x, y);
				add(cell_panel);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}

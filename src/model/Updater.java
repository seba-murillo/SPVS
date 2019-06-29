package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import view.Screen;


public class Updater implements ActionListener{

	public static boolean		start_ON	= false;
	private static final int	DELAY		= 250;

	private static boolean	RUN	= true;
	private static Updater	updater;

	Timer update_timer = new Timer(DELAY, this);

	public Updater(){
		Updater.updater = this;
		update_timer.setRepeats(false);
		if(start_ON) Updater.play();
	}

	public static void play(){
		//updater.update_timer.start();
		RUN = true;
		updater.update();
	}

	public static void pause(){
		RUN = false;
	}

	public void actionPerformed(ActionEvent e){
		if(!RUN) return;
		update();
	}

	private void update(){
		State.getCurrent().tick();
		Screen.draw();
		update_timer.start();
	}
}
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import controller.Controller;


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

	@Override
	public void actionPerformed(ActionEvent e){
		if(!RUN) return;
		update();
	}

	private void update(){
		Controller.getCurrentState().tick();
		if(!State.done) update_timer.start();
	}
}
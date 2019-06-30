package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.State;
import model.Updater;


@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements ActionListener{

	Timeout	reloj;
	int		cont	= 0;

	private static final String	STR_PLAY	= "▶";
	private static final String	STR_PAUSE	= "▮▮";
	private static final String	STR_PREV	= "⧏";
	private static final String	STR_NEXT	= "⧐";

	private JButton	btn_play	= new JButton(STR_PAUSE);
	private JButton	btn_prev	= new JButton(STR_PREV);
	private JButton	btn_next	= new JButton(STR_NEXT);

	public ControlPanel(){
		super();
		//Game.log("ControlPanel created\n");
		setLayout(new FlowLayout());
		if(Updater.start_ON == false) btn_play.setText(STR_PLAY);
		add(btn_prev);
		add(btn_play);
		add(btn_next);
		btn_play.setBackground(Color.GREEN);
		btn_prev.addActionListener(this);
		btn_play.addActionListener(this);
		btn_next.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btn_play){
			if(STR_PLAY.equals(btn_play.getText())){ // play
				btn_play.setText(STR_PAUSE);
				btn_play.setBackground(Color.RED);
				Updater.play();
				cont++;
				if(cont == 1 && reloj != null) reloj.start();
			}
			else if(STR_PAUSE.equals(btn_play.getText())){ // pause
				btn_play.setText(STR_PLAY);
				btn_play.setBackground(Color.GREEN);
				Updater.pause();
			}
		}
		else if(e.getSource() == btn_prev){
			State.loadLast();
		}
		else if(e.getSource() == btn_next){
			State.nextState();
		}
	}
}

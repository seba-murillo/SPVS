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

	private static final String	STR_PLAY	= "▶";
	private static final String	STR_PAUSE	= "▮▮";
	private static final String	STR_PREV	= "⧏";
	private static final String	STR_NEXT	= "⧐";

	private JButton	btn_play	= new JButton(STR_PLAY);
	private JButton	btn_prev	= new JButton(STR_PREV);
	private JButton	btn_next	= new JButton(STR_NEXT);

	public ControlPanel(){
		super();
		this.setName("panel_control");
		setLayout(new FlowLayout());
		btn_prev.setName("btn_prev");
		btn_play.setName("btn_play");
		btn_next.setName("btn_next");
		add(btn_prev);
		add(btn_play);
		add(btn_next);
		btn_play.setBackground(Color.GREEN);
		btn_prev.addActionListener(this);
		btn_play.addActionListener(this);
		btn_next.addActionListener(this);
		btn_play.setToolTipText("auto-play");
		btn_prev.setToolTipText("cargar estado anterior");
		btn_next.setToolTipText("cargar siguiente estado");
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btn_play){
			if(STR_PLAY.equals(btn_play.getText())){
				btn_play.setText(STR_PAUSE);
				btn_play.setBackground(Color.RED);
				Updater.play();
				btn_play.setToolTipText("pausar simulacion");
			}
			else if(STR_PAUSE.equals(btn_play.getText())){ // pause
				btn_play.setText(STR_PLAY);
				btn_play.setBackground(Color.GREEN);
				Updater.pause();
				btn_play.setToolTipText("continuar simulacion");
			}
		}
		else if(e.getSource() == btn_prev){
			State.prev();
		}
		else if(e.getSource() == btn_next){
			State.next();
		}
	}
}

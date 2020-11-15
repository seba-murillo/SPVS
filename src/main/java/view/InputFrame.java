package view;

import model.Game;
import model.State;
import model.Updater;
import utils.SPVSutils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputFrame extends JFrame{

	JTextField field_width = new JTextField(5);
	JTextField field_height = new JTextField(5);
	JTextField field_duration = new JTextField(5);
	JButton button_OK = new JButton("start");

	public InputFrame(){
		super("SPVS");
		this.setName("frame_input");
		this.setLocationRelativeTo(null); // center
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// config button
		button_OK.addActionListener(e -> {
			validateInput();
		});
		// add to frame
		field_width.setName("field_width");
		field_height.setName("field_height");
		field_duration.setName("field_duration");
		button_OK.setName("button_start");
		this.add(new JLabel("Ingrese:"));
		this.add(new JLabel("ancho (min 10): "));
		this.add(field_width);
		this.add(new JLabel("alto (min 10): "));
		this.add(field_height);
		this.add(new JLabel("duracion: "));
		this.add(field_duration);
		this.add(button_OK);
		// show frame
		this.pack();
		this.setVisible(true);
	}

	private void validateInput(){
		SPVSutils.log("button_OK!");
		int width = 0, height = 0, duration = 0;
		try{
			width = Integer.parseInt(field_width.getText());
			height = Integer.parseInt(field_height.getText());
			duration = Integer.parseInt(field_duration.getText());
		}catch(Exception exception){
			tryAgainMessage();
			return;
		}
		if(width < Game.MIN_WIDTH || width > Game.MAX_WIDTH){
			tryAgainMessage();
			return;
		}
		if(height < Game.MIN_HEIGHT || height > Game.MAX_HEIGHT){
			tryAgainMessage();
			return;
		}
		if(duration < 0){
			tryAgainMessage();
			return;
		}
		// start game
		Game.start(width, height, duration);
		this.dispose();
		remove_components();
	}

	private void tryAgainMessage(){
		JOptionPane.showMessageDialog(null, "Números inválidos, intente nuevamente...");
		field_width.setText("");
		field_height.setText("");
		field_duration.setText("");
	}

	void remove_components(){

	}
}
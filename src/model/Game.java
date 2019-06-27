package model;

import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.JOptionPane;
import view.Screen;
import view.Timeout;


public class Game{

	public static final boolean	verbose	= false;
	public static final boolean	debug	= true;
	
	public static int			MAX_X;
	public static int			MAX_Y;
	private static State		current;

	public static void main(String[] args){
		
		//if(debug) {
		//	new Game(30, 30);
		//	return;
		//}
		
		//JTextField minutos = new JTextField(3);
		//JTextField segundos = new JTextField(3);	
		//JPanel input = new JPanel();
		
		JOptionPane.showMessageDialog(null, "BIENVENIDO AL SIMULADOR DE VIDA SALVAJE");
		Object especie1 = JOptionPane.showInputDialog(null,"Seleccione Una Especie1", "ESPECIE 1", 
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Seleccione","Conejo", "Oso", "Lobo" },"Seleccione");
		Object especie2 = JOptionPane.showInputDialog(null,"Seleccione Una Especie2", "ESPECIE 2", 
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Seleccione","Conejo", "Oso", "Lobo" },"Seleccione");
		
		//input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
		//input.add(new JLabel("Ingrese el tamaño del Terreno:"));
		//input.add(new JLabel("ANCHO: "));
		//input.add(field_width);
		//input.add(new JLabel("ALTO: "));
		//input.add(field_height);
		//int width = 5, height = 5;
		
		int ancho =Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ancho del terreno: "));
		int alto =Integer.parseInt(JOptionPane.showInputDialog("Ingrese el alto del terreno: "));
		
		//JOptionPane.showMessageDialog(null, input, "Simulador de Vida Salvaje", JOptionPane.INFORMATION_MESSAGE);
		
		while(alto < 10) {
			alto =Integer.parseInt(JOptionPane.showInputDialog("Ingrese un alto del terreno mayor a 10: "));}
		
		while(ancho < 10) {
			ancho =Integer.parseInt(JOptionPane.showInputDialog("Ingrese un ancho del terreno mayor a 10: "));}
		
		int minutos =Integer.parseInt(JOptionPane.showInputDialog("Ingrese los minutos de simulacion: "));
		int segundos =Integer.parseInt(JOptionPane.showInputDialog("Ingrese los segundos de simulacion: "));
		
		while(minutos < 0 || minutos > 5) {
			minutos =Integer.parseInt(JOptionPane.showInputDialog("Ingrese minutos mayores a 0 y menores a 5: "));}
		
		while(segundos < 0 || segundos > 59) {
			segundos =Integer.parseInt(JOptionPane.showInputDialog("Ingrese segundos mayores a 0 y menores a 59: "));}
		
		Timeout reloj = new Timeout(minutos, segundos);
		new Game(ancho, alto, reloj);
		
	}

	//TODO CHANGE TO PRIVATE
	public Game(int width, int height, Timeout reloj){
		MAX_Y = width;
		MAX_X = height;
		if(debug) current = new State("full_test");
		else
			current = new State();
		new Screen(reloj);
		current.update_observers();
		new Updater();
	}

	public static void error(Object message){
		System.err.print(message.toString());
	}

	public static State getCurrentState(){
		//log("current = " + current + "\n");
		return current;
	}

	public static void loadLastState(){
		current = State.loadLast();
		//log("current state is now " + current + "\n");
	}

	public static void loadState(int ID){
		current = State.load(ID);
	}

	public static void log(Object message){
		System.out.println(message.toString());
	}

	public static void nextState(){
		current = current.tick();
	}

	public static void setCurrent(State st){
		current = st;
	}
}

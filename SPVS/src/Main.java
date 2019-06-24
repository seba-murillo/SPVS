import javax.swing.*;
import model.Game;

public class Main{

	public static void main(String[] args){
		
		Game game;
		Object [] especie1 = {"Seleccione Especie 1", "Zorro"};
		Object [] especie2 = {"Seleccione Especie 2","Conejo"};
		
		JOptionPane.showMessageDialog(null, "BIENVENIDO AL SIMULADOR DE VIDA SALVAJE", 
				"INICIO", JOptionPane.NO_OPTION);
		JOptionPane.showMessageDialog(null, "A CONTINUACION, INGRESE EL TAMAÑO DEL TERRENO", 
				"CONFIGURACION TERRENO", JOptionPane.NO_OPTION);
		int ancho = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL ANCHO: ", 
				"ANCHO", JOptionPane.INFORMATION_MESSAGE));
		int largo = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL LARGO: ",
				"LARGO", JOptionPane.INFORMATION_MESSAGE));
		Object opt_esp1 = JOptionPane.showInputDialog(null, "Especie de color AZUL", 
				"ESPECIE 1", JOptionPane.INFORMATION_MESSAGE, null, especie1, especie1[0]);
		Object opt_esp2 = JOptionPane.showInputDialog(null, "Especie de color ROJO", 
				"ESPECIE 2", JOptionPane.INFORMATION_MESSAGE, null, especie2, especie2[0]);
		
		game = new Game(ancho, largo);
		
	}
	
	public static void log(Object message) {
		System.out.println(message.toString());
	}
}

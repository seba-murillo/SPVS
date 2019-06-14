import javax.swing.JOptionPane;
import model.Game;

public class Main{
	
	public static void main(String[] args){
		
		Game game = null;
		
		JOptionPane.showMessageDialog(null, "BIENVENIDO AL SIMULADOR DE VIDA SALVAJE", 
				"INICIO", JOptionPane.NO_OPTION);
		JOptionPane.showMessageDialog(null, "A CONTINUACION, INGRESE EL TAMAÑO DEL TERRENO", 
				"CONFIGURACION TERRENO", JOptionPane.NO_OPTION);
		int ancho = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL ANCHO: ", 
				"ANCHO", JOptionPane.INFORMATION_MESSAGE));
		int largo = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL LARGO: ",
				"LARGO", JOptionPane.INFORMATION_MESSAGE));
		
		game = new Game(ancho, largo);
		
	}
	
	public static void log(Object message) {
		System.out.println(message.toString());
	}
}

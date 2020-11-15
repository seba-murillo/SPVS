package model;

import javax.swing.*;

import view.InputFrame;
import view.Screen;


public class Game{

	public static final int DEFAULT_DURATION = 1000;
	public static final int MIN_WIDTH = 10;
	public static final int MAX_WIDTH = 50;
	public static final int MIN_HEIGHT = 10;
	public static final int MAX_HEIGHT = 30;

	private static Game current = null;

	@SuppressWarnings("InstantiationOfUtilityClass")
	public static Game start(int width, int height, int duration){
		if(current != null) return current;
		current = new Game(width, height, duration);
		return current;
	}

	public static void main(String[] args){
		new InputFrame();
	}

	private Game(int width, int height, int duration){
		State.initialize(width, height, duration);
		Screen.create(width, height);
	}
}

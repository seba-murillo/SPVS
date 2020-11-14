package utils;

import java.util.Random;

public class SPVSutils{


	public static void log(String format, Object... args){
		System.out.println(String.format(format, args));
	}

	private int getRandom(int min, int max){
		return new Random().nextInt(max + 1 - min) + min;
	}
}

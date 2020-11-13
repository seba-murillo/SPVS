package utils;

public class SPVSLog{


	public static void log(String format, Object... args){
		System.out.println(String.format(format, args));
	}
}

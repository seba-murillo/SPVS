package model;


import java.util.TimerTask;
import java.util.Timer;


public abstract class Updater {

    private static final int DELAY = 500;
    private static Timer timer;


    public static void play() {
        TimerTask task = new TimerTask() {
            public void run() {
                State.next();
            }
        };
        timer = new Timer("Timer");
        timer.schedule(task, DELAY, DELAY);
    }

    public static void pause() {
        timer.cancel();
    }
}
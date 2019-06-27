package view;

import java.util.Timer;
import java.util.TimerTask;
 
public class Timeout extends Thread{
 
    //private int hour;
    private int minute;
    private int second;
    private Timer timer;
    private boolean isTimerRunning;
    //private MainGUI mainGui;
    private TimeFormat tf;
 
    public Timeout(/*MainGUI mainGui,*/int minute, int second) {
        timer = new Timer();
        tf = new TimeFormat();
       // this.mainGui = mainGui;
        this.minute = minute;
        this.second = second;
    }
 
        public void run() {
        	while(!(second==0 && minute==0)) {
            isTimerRunning = true;
            if(second > 0) {
                second--;
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                second = 59;
                if(minute > 0) {
                	minute--;
                	try {
    					Thread.sleep(1000);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
                    else {
                        isTimerRunning = false;
                        timer.cancel();
                        timer.purge();
                        try {
        					Thread.sleep(1000);
        				} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
                    }
                }
            if(isTimerRunning) {
                String time = tf.formatTime(minute, second);
                System.out.print(time + "\n");
                
                //mainGui.getTxtTime().setText(time);
            }
        }
        }
    }
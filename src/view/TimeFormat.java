package view;

public class TimeFormat {
    
    public String formatTime(int minute, int second) {
        String fullHour = "";
        fullHour += (minute > 9) ? ":" + minute : ":0" + minute;
        fullHour += (second > 9) ? ":" + second : ":0" + second;
 
        return fullHour;
    }
}
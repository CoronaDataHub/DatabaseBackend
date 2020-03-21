package de.coronadatahub.databasebackend.timer.model;

public class Time {

    private int hour;
    private int minute;

    public Time() {
    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static Time t(int hour, int minute){
        return new Time(hour, minute);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public static boolean isTimeSame(Time time1, Time time2){
        return time1.getHour() == time2.getHour() && time1.getMinute() == time2.getMinute();
    }
}

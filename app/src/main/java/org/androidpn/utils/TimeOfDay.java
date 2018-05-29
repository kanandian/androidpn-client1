package org.androidpn.utils;

public class TimeOfDay {

    private int hour;
    private int minute;

    public TimeOfDay() {
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

    @Override
    public String toString() {
        return this.hour+":"+this.minute;
    }
}

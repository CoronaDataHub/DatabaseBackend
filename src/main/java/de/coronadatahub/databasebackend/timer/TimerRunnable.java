package de.coronadatahub.databasebackend.timer;

import de.coronadatahub.databasebackend.timer.model.Time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimerRunnable implements Runnable{

    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Time currentTime;
        while (true){
            String[] date = simpleDateFormat.format(new Date()).split(":");
            currentTime = new Time(Integer.parseInt(date[0]), Integer.parseInt(date[1]));

            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

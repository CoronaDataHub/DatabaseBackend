package de.coronadatahub.databasebackend.timer;

import de.coronadatahub.databasebackend.timer.model.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerRunnable implements Runnable {

    private TimerTask timerTask;

    public TimerRunnable(TimerTask timerTask) {
        this.timerTask = timerTask;

    }

    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Time currentTime;
        while (true) {
            String[] date = simpleDateFormat.format(new Date()).split(":");
            currentTime = new Time(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
            Time finalCurrentTime = currentTime;
            timerTask.getTaskList().forEach((time, runnable) -> {
                if (Time.isTimeSame(time, finalCurrentTime)) {
                    runnable.run();
                }
            });
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

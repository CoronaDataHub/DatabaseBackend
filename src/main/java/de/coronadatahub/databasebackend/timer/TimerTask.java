package de.coronadatahub.databasebackend.timer;

import de.coronadatahub.databasebackend.timer.model.Time;

import java.util.HashMap;

public class TimerTask {

    private HashMap<Time, Runnable> taskList = new HashMap();

    public void addTask(Time time, Runnable runnable) {
        taskList.put(time, runnable);
    }


}

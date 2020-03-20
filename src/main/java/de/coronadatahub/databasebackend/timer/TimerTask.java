package de.coronadatahub.databasebackend.timer;

import de.coronadatahub.databasebackend.timer.model.Time;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimerTask {

    private HashMap<Time, Runnable> taskList;
    private ExecutorService executorService;

    public TimerTask() {
        taskList = new HashMap<>();
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new TimerRunnable(this));
    }

    public void addTask(Time time, Runnable runnable) {
        taskList.put(time, runnable);
    }

    public HashMap<Time, Runnable> getTaskList() {
        return taskList;
    }
}

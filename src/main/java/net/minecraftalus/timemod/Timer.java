package net.minecraftalus.timemod;

import java.util.concurrent.TimeUnit;

public class Timer {
    private long startTime;
    private long totalElapsedTime;
    public boolean isRunning;
    public boolean isPaused;

    public Timer() {
        isRunning = false;
        isPaused = false;
    }

    public void TimerStart() {
        if (!isRunning && !isPaused) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }

    public void TimerPause() {
        if (isRunning && !isPaused) {
            totalElapsedTime += System.nanoTime() - startTime;
            isRunning = false;
            isPaused = true;
        }
    }

    public void TimerResume() {
        if (isPaused) {
            startTime = System.nanoTime();
            isRunning = true;
            isPaused = false;
        }
    }

    public void TimerStop() {
        isRunning = false;
        isPaused = false;
        totalElapsedTime = 0;
    }

    public String getTime() {
        if (isRunning) {
            long elapsedTime = totalElapsedTime + System.nanoTime() - startTime;
            return formatTime(elapsedTime);
        } else {
            return formatTime(totalElapsedTime);
        }
    }

    private String formatTime(long nanoseconds) {
        long hours = TimeUnit.NANOSECONDS.toHours(nanoseconds);
        long minutes = TimeUnit.NANOSECONDS.toMinutes(nanoseconds) % 60;
        long seconds = TimeUnit.NANOSECONDS.toSeconds(nanoseconds) % 60;
        long milliseconds = TimeUnit.NANOSECONDS.toMillis(nanoseconds) % 1000;

        return String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds);
    }
}
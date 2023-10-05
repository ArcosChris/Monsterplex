package com.monsterplex.util;

import java.time.Duration;
import java.time.LocalTime;

public class Clock {
    private final int minutes;
    private LocalTime endTime;

    public Clock(int minutes) {
        this.minutes = minutes;
    }

    public void start() {
        LocalTime startTime = now();
        endTime = startTime.plusMinutes(minutes);
    }

    public String timeRemainingAsString() {
        // example: 250 seconds
        long totalSeconds = Duration.between(now(), endTime).getSeconds();

        // integer division: 250 / 60 = 4 minutes (with 10 seconds left over)
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds - (minutes * 60);

        String minutesString = String.valueOf(minutes);
        String secondsString = String.valueOf(seconds);
        if (seconds < 10) {
            secondsString = "0" + secondsString;  // 09 for 9 seconds
        }
        return String.format("%s minutes and %s seconds",minutesString, secondsString);
    }

    public boolean isTimeRemaining() {
        return now().isBefore(endTime);
    }

    private static LocalTime now() {
        return LocalTime.now().withNano(0);
    }

}
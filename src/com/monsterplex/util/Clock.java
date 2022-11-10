package com.monsterplex.util;

import java.time.Duration;
import java.time.LocalTime;

public class Clock {
    private final int minutes;
    private LocalTime startTime;
    private LocalTime endTime;

    public Clock(int minutes) {
        this.minutes = minutes;
    }

    public LocalTime start() {
        startTime = now();
        endTime   = startTime.plusMinutes(minutes);
        return endTime;  // for convenience, if the client wants it, else client can ignore it
    }

    public String timeRemainingAsString() {
        // example: 250 seconds
        long totalSeconds = Duration.between(now(), endTime).getSeconds();

        // integer division: 250 / 60 = 4 minutes (with 10 seconds left over)
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds - (minutes * 60);

        String secondsString = String.valueOf(seconds);
        if (seconds < 10) {
            secondsString = "0" + secondsString;  // 09 for 9 seconds
        }
        return String.valueOf(minutes + ":" + secondsString);
    }

    public boolean isTimeRemaining() {
        return now().isBefore(endTime);
    }

    // that "withNano(0)" shit is annoying all the time
    private static LocalTime now() {
        return LocalTime.now().withNano(0);
    }

}
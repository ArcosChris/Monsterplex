package com.monsterplex.util;

class ClockTest {

    public static void main(String[] args) {
        Clock clock1 = new Clock(2);
        clock1.start();

        while (clock1.isTimeRemaining()) {
            System.out.println(clock1.timeRemainingAsString());
            pause(randomPauseAmount());
        }
    }

    private static void pause(long duration) {
        try { Thread.sleep(duration); }
        catch (InterruptedException ignored) { }
    }

    private static long randomPauseAmount() {
        return (long) (Math.random() * 4000);
    }
}
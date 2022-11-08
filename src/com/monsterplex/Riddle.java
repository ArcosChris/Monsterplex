package com.monsterplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Riddle {
    private long id;
    private String riddle;
    private String answer;

    Riddle(long id, String riddle, String answer) {
        setId(id);
        setRiddle(riddle);
        setAnswer(answer);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getRiddle() {
        return riddle;
    }

    private void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getAnswer() {
        return answer;
    }

    private void setAnswer(String answer) {
        this.answer = answer;
    }

    public static class InMemoryRiddleHolder {

        private static final List<Riddle> riddleList = new ArrayList<>(List.of(
                new Riddle(1L, "What loses its head in the morning and gets it back at night?", "pillow"),
                new Riddle(2L, "What word is always spelled wrong?", "wrong"),
                new Riddle(3L, "What is brown and sticky?", "stick"),
                new Riddle(4L, "What invention lets you look right through a wall?", "window"),
                new Riddle(5L, " Where does today come before yesterday?", "dictionary"),
                new Riddle(6L, "You go at red and stop at green. What am I?", "watermelon"),
                new Riddle(7L, "I have hundreds of ears, but I can't hear a thing. What am I?", "cornfield")
        ));

        public static Riddle getRandomRiddle() {
            int randomRiddle = new Random().nextInt(riddleList.size());
            return riddleList.get(randomRiddle);
        }

    }
}
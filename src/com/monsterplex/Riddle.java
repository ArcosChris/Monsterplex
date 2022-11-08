package com.monsterplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Riddle {
    private long id;
    private String riddle;
    private String answer;

    Riddle(long id, String riddle, String answer){
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
}
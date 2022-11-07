package com.monsterplex.client;

import com.monsterplex.Map;
import com.monsterplex.app.MonsterplexApp;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        Map testing = new Map();
        testing.loadMap(1,2);
        MonsterplexApp app = new MonsterplexApp();
        app.execute();

    }
}
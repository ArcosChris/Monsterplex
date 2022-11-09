package com.monsterplex.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private Reader(){}

    public static List<StringBuilder> readFileToArrayList(String fileName){
        List<StringBuilder> lines = new ArrayList<>();

        try {
            Files.lines(Path.of(fileName))
                    .forEach(line -> lines.add(new StringBuilder(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
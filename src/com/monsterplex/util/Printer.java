package com.monsterplex.util;

import java.io.IOException;

public class Printer {

    public Printer() {
    }

    /*
     * Print file
     * @param fileName - the file that needs to be printed
     * @param pauseAtLine - how long to pause after each line print (long milliseconds)
     * */
    public void printFile(String fileName, long pauseAtLine) throws IOException {
        for(String line : FileHelper.readFileToStringArrayList(fileName)){
            System.out.println(line);
            Console.pause(pauseAtLine);
        }
    }
}

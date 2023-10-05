package com.monsterplex.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {
    private static final Printer printer = new Printer();

    /**
     * Loads the file using the ClassLoader by the fileName.
     *
     * @param fileName - the name of the file to load using the ClassLoader.
     * @return An InputStream representing the loaded file.
     * @throws IOException if an I/O error occurs while loading the file.
     */
    public static InputStream loadFile(String fileName) throws IOException {
        InputStream stream = FileHelper.class.getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new IllegalArgumentException(String.format("Could not get stream for file: %s", fileName));
        }
        return stream;
    }

    /**
     * Default loadAndPrint will call loadAndPrint(fileName, 0) to print file.
     *
     * @param fileName - the name of the file to print.
     * @throws IOException if an I/O error occurs while loading the file.
     */
    public static void loadAndPrint(String fileName) throws IOException {
        loadAndPrint(fileName, 0);
    }

    /**
     * Loads the contents of a file and sends them to the printer for output, with an optional pause between lines.
     *
     * @param fileName - the name of the file to load and print.
     * @param pause    - the duration of the pause between lines (in milliseconds).
     * @throws IOException if an I/O error occurs while loading the file.
     */
    public static void loadAndPrint(String fileName, long pause) throws IOException {
        printer.printFile(fileName, pause);
    }

    /**
     * Reads a text file and returns a List of StringBuilder instances, each containing a line from the file.
     *
     * @param fileName - the name of the file to load and process.
     * @return List of StringBuilder instances, each representing a line from the file.
     * @throws IOException if an I/O error occurs while loading the file.
     */
    public static List<StringBuilder> readFileToStringBuilderArrayList(String fileName) throws IOException {
        return readFileToStringArrayList(fileName).stream()
                .map(StringBuilder::new)
                .collect(Collectors.toList());
    }

    /**
     * Reads a text file and returns a List of String instances, with each element representing a line from the file.
     *
     * @param fileName - the name of the file to load and process.
     * @return List of String instances, each representing a line from the file.
     * @throws IOException if an I/O error occurs while loading the file.
     */
    public static List<String> readFileToStringArrayList(String fileName) throws IOException {
        try (InputStream stream = loadFile(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            List<String> stringLines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                stringLines.add(line);
            }
            return stringLines;
        }
    }
}

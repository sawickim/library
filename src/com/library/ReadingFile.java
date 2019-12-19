package com.library;

import java.io.*;
import java.util.*;


public class ReadingFile {

    public List<String> readingFile() throws IOException {

        List<String> lines = new ArrayList<>();

        BufferedReader plik2 = null;

        try {
            plik2 = new BufferedReader(new FileReader("C:\\Dokumenty\\Java\\FinalBook po zajeciach\\src\\com\\library\\booksLibrary.txt"));

            String line = plik2.readLine();

            while ((line = plik2.readLine()) != null) {
                lines.add(line);
            }
        }

        finally {
            if (plik2 != null) {
                plik2.close();
            }
        }

        return lines;
    }
}

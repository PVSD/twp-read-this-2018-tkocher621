package com.company;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = System.getProperty("user.dir") + "/src/Imports.txt";
        File file = new File(filePath);
        Scanner sf = new Scanner(file);

        int lineCount = 0;

        while (sf.hasNext())
            System.out.println(sf.next());



    }
}

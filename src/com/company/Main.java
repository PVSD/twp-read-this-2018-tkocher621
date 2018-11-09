package com.company;
import java.util.*;
import java.io.*;

public class Main {

    public static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

    public static int FindClosestMatch(List<String> list, String input)
    {
        int closestIndex = 0;
        int closestMatchLength = 99999;

        for (int i = 0; i < list.size(); i++)
        {
            String curr = list.get(i);
            int difference = distance(input, curr);
            if (difference < closestMatchLength)
            {
                closestIndex = i;
                closestMatchLength = difference;
            }
        }

        return closestIndex;
    }

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String filePath = System.getProperty("user.dir") + "/src/Imports.txt";
        File file = new File(filePath);
        Scanner sf = new Scanner(file);

        List<String> Names = new ArrayList<>();
        List<Integer> Averages = new ArrayList<>();
        List<Integer> Absences = new ArrayList<>();
        sf.nextLine();
        while (sf.hasNextLine()) {
            String temp = sf.nextLine().replace(" ", "");
            String[] list = temp.split("\\s");
            Names.add(list[0] + " " + list[1]);
            Averages.add(Integer.parseInt(list[2].replace("%", "")));
            Absences.add(Integer.parseInt(list[3]));
        }
        int A = 0, B = 0, C = 0, D = 0, F = 0;
        for (int a : Averages)
        {
            if (a >= 90)
                A++;
            else if (a >= 80)
                B++;
            else if (a >= 70)
                C++;
            else if (a >= 60)
                D++;
            else
                F++;
        }
        int average = 0;
        for (int a : Averages)
        {
            average += a;
        }
        System.out.println("Average class grade: " + average / Averages.size() + "%");
        System.out.println("A: " + A + "\n" + "B: " + B + "\n" + "C: " + C + "\n" + "D: " + D + "\n" + "F: " + F);
        System.out.println("Enter a student to view their record.");
        String input = scan.nextLine();
        int index = FindClosestMatch(Names, input);
        int rank = Averages.size();
        for (int a : Averages)
            if (a < Averages.get(index))
                rank--;
        System.out.println("Student " + Names.get(index) + ":");
        System.out.println("Average: " + Averages.get(index));
        System.out.println("Class rank: " + rank);
        int abs = Absences.get(index);
        System.out.println("Number of absences: " + abs);
        if (abs > 8)
            System.out.println("This students high absence count may be harming their grade.");
    }
}

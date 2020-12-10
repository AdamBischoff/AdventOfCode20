package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        first();
        second();
    }

    private static void first() {
        Line[] data = getData();

        int noValid = 0;

        for (Line line : data) {
            int occurrences = occurrencesOf(line.password, line.letter);

            if (occurrences >= line.min
                && occurrences <= line.max) {
                noValid++;
            }
        }

        System.out.println(noValid);
    }

    private static void second() {
        Line[] data = getData();

        int noValid = 0;

        for (Line line : data) {
            System.out.println(line);
            if (line.password.charAt(line.min - 1) == line.letter
                ^ //XOR
                line.password.charAt(line.max - 1) == line.letter) {
                noValid++;
            }
        }

        System.out.println(noValid);
    }

    private static int occurrencesOf(String text, char letter) {
        int res = 0;
        for (char c : text.toCharArray()) {
            if (c == letter)    res++;
        }
        return res;
    }

    private static Line[] getData() {
        List<Line> res = new ArrayList<>();
        File file = new File("src/Day2/data.txt");

        int lineNo = 0;

        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
//                System.out.println(lineNo);
                String readLine = scanner.nextLine();
                String min = readLine.substring(0, readLine.indexOf('-'));

                String max = readLine.substring(readLine.indexOf('-')+1, readLine.indexOf(' '));

                char letter = readLine.charAt(readLine.indexOf(':') - 1);

                String password = readLine.substring(readLine.indexOf(':') + 2);

                Line line = new Line(Integer.parseInt(min), Integer.parseInt(max), letter, password);
//                System.out.println(line);
                res.add(line);
                lineNo++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Line[] resArray = new Line[res.size()];

        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }

        return resArray;
    }

    private static class Line {
        public int min;
        public int max;
        public char letter;
        public String password;

        public Line(int min, int max, char letter, String password) {
            this.min = min;
            this.max = max;
            this.letter = letter;
            this.password = password;
        }

        public String toString() {
            return min + "-" + max + " " + letter + ": " + password;
        }
    }
}


package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        first();
        second();
    }

    private static void first() {
        String[] data = getDataFirst();
        List<Integer> counts = new ArrayList<>();

        for (String group : data) {
            int count = 0;

            for (char question = 'a'; question <= 'z'; question++) {
                if (group.indexOf(question) != -1) {
                    count++;
                }
            }
            counts.add(count);
        }

        int sum = 0;
        for (int i : counts) {
            sum += i;
        }
        System.out.println(sum);


    }

    private static void second() {
        List<List<String>> data = getDataSecond();
        List<Integer> counts = new ArrayList<>();

        for (List<String> group : data) {
            int groupSize = group.size();
            int count = 0;
            String combinedAnswer = "";
            for (String ans : group) {
                combinedAnswer += ans;
            }

            for (char question = 'a'; question <= 'z'; question++) {
                char finalQuestion = question;
                long questionCount = combinedAnswer.chars().filter(c -> c == finalQuestion).count();
                if (groupSize == questionCount) {
                    count++;
                }
            }
            counts.add(count);
        }

        int sum = 0;
        for (int i : counts) {
            sum += i;
        }
        System.out.println(sum);

    }

    private static String[] getDataFirst() {
        File file = new File("src/Day6/data.txt");
        List<String> res = new ArrayList<>();

        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder current = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("")) {
                res.add(current.toString());
                current = new StringBuilder();
                continue;
            }
            current.append(line);
        }
        res.add(current.toString());

        return res.toArray(new String[0]);
    }


    private static List<List<String>> getDataSecond() {
        File file = new File("src/Day6/data.txt");
        List<List<String>> res = new ArrayList<>();

        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        List<String> current = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("")) {
                res.add(current);
                current = new ArrayList<>();
                continue;
            }
            current.add(line);
        }
        res.add(current);


        return res;
    }
}

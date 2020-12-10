package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        first();

        second();

    }

    private static void first() {
        int[] data = getData();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i] + data[j] == 2020) {
                    System.out.println(data[i] + " * " + data[j] + " = " + (data[i]*data[j]));
                }
            }
        }
    }

    private static void second() {
        int[] data = getData();

        for (int a : data) {
            for (int b : data) {
                for (int c : data) {
                    if (a + b + c == 2020) {
                        System.out.println(a + " * " + b + " * " + c + " = " + (a * b * c));
                    }
                }


            }
        }
    }

    private static int[] getData() {
        List<Integer> res = new ArrayList<>();
        File file = new File("src/Day1/data.txt");

        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                res.add(Integer.parseInt(scanner.nextLine()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(res);
        System.out.println(res.size());

        int[] resArray = new int[res.size()];

        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }
}

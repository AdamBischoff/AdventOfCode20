package Day3;

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
        boolean[][] data = getData();
        int width = data[0].length;

        int noTrees = 0;
        int x = 0;
        int y = 0;

        while (y < data.length) {
            if (data[y][x]) {
                noTrees++;
            }
            y++;
            x = (x + 3) % width;

        }

        System.out.println(noTrees);
    }

    private static void second() {
        boolean[][] data = getData();

        int res = noTreesHit(data, 1, 1);
        res *= noTreesHit(data, 3, 1);
        res *= noTreesHit(data, 5, 1);
        res *= noTreesHit(data, 7, 1);
        res *= noTreesHit(data, 1, 2);

        System.out.println(res);



    }

    private static int noTreesHit(boolean[][] data, int noRight, int noDown) {
        int width = data[0].length;

        int noTrees = 0;
        int x = 0;
        int y = 0;

        while (y < data.length) {
            if (data[y][x]) {
                noTrees++;
            }
            y += noDown;
            x = (x + noRight) % width;

        }

        return noTrees;
    }




    private static boolean[][] getData() {
        File file = new File("src/Day3/data.txt");
        List<boolean[]> res = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                char[] line = scanner.nextLine().toCharArray();
                res.add(new boolean[line.length]);

                for (int i = 0; i < line.length; i++) {
                    if (line[i] == '#') {
                        res.get(res.size() - 1)[i] = true;
                    } else if (line[i] == '.') {
                        res.get(res.size() - 1)[i] = false;
                    } else {
                        System.err.println("UNEXPECTED INPUT " + line[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean[][] resArray = new boolean[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
//            System.out.println(Arrays.toString(res.get(i)));
        }

        return resArray;
    }
}

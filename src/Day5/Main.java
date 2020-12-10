package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        first();
        second();
    }

    private static void first() {
        String[] data = getData();
        List<Integer> ids = new ArrayList<>();

        for (String line : data) {
            int row = getRow(line, 7);
            int col = getCol(line, 7, 3);

            System.out.println(line + " has " + row + "," + col);
            ids.add(8*row + col);

        }

        int max = 0;
        for (int i : ids) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);
    }

    private static void second() {
        String[] data = getData();
        List<Integer> ids = new ArrayList<>();

        for (String line : data) {
            int row = getRow(line, 7);
            int col = getCol(line, 7, 3);
//            System.out.println(line + " has " + row + "," + col);
            ids.add(8*row + col);
        }

        List<Integer> possibleIds = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 8; j++) {
                possibleIds.add(8*i + j);
            }
        }

        possibleIds.removeAll(ids);

        int lastId = possibleIds.get(0);
        for (int i = 1; i < possibleIds.size(); i++) {
            if (lastId + 1 != possibleIds.get(i)) {
                lastId = possibleIds.get(i);
                break;
            }
            lastId = possibleIds.get(i);
        }

        System.out.println(lastId);

    }

    private static int getRow(String line, int lettersToRead) {
        int guess = 0;
        int range = (int) Math.pow(2, lettersToRead);
        char letter;

        for (int i = 0; i < lettersToRead; i++) {
            letter = line.charAt(i);
            range /= 2;

            if (letter == 'F') {
                //nothing
            } else if (letter == 'B') {
                guess += range;
            } else {
                System.err.println("Wrong input: " + letter);
            }
        }

        return guess;
    }

    private static int getCol(String line, int startPos, int lettersToRead) {
        int guess = 0;
        int range = (int) Math.pow(2, lettersToRead);
        char letter;

        for (int i = startPos; i < lettersToRead + startPos; i++) {
            letter = line.charAt(i);
            range /= 2;

            if (letter == 'L') {
                //nothing
            } else if (letter == 'R') {
                guess += range;
            } else {
                System.err.println("Wrong input: " + letter);
            }
        }

        return guess;
    }



    private static String[] getData() {
        File file = new File("src/Day5/data.txt");
        List<String> res = new ArrayList<>();

        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        while (scanner.hasNextLine()) {
            res.add(scanner.nextLine());
        }

        String[] resArray = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }
}

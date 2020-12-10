package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        first();
    }

    private static void first() {
        Passport[] data = getData();
        int noValidPassports = 0;

        for (Passport p : data) {
            if (p.isValid()) {
                noValidPassports++;
//                System.out.println("VALID");
//                System.out.println(p);
            } else {
//                System.out.println("INVALID");
//                System.out.println(p);
            }
        }

        System.out.println(data.length);
        System.out.println(noValidPassports);

    }










    private static Passport[] getData() {
        File file = new File("src/Day4/data.txt");
        List<Passport> res = new ArrayList<>();

        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Passport currentPassport = new Passport();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                res.add(currentPassport);
                currentPassport = new Passport();
                continue;
            }

            String[] pairs = line.split(" ");

            for (String p : pairs) {
                String[] pair = p.split(":");
                currentPassport.loadValue(pair[0], pair[1]);
            }
        }
        res.add(currentPassport);

        Passport[] resArray = new Passport[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }







    private static class Passport {
        String byr = null;
        String iyr = null;
        String eyr = null;
        String hgt = null;
        String hcl = null;
        String ecl = null;
        String pid = null;
        String cid = null; //Stil valid without

        public void loadValue(String key, String value) {
//            System.out.println(key + ":" + value);
            switch (key) {
                case "byr":
                    byr = value;
                    break;
                case "iyr":
                    iyr = value;
                    break;
                case "eyr":
                    eyr = value;
                    break;
                case "hgt":
                    hgt = value;
                    break;
                case "hcl":
                    hcl = value;
                    break;
                case "ecl":
                    ecl = value;
                    break;
                case "pid":
                    pid = value;
                    break;
                case "cid":
                    cid = value;
                    break;
                default:
                    System.err.println("NONE: " + key + ":" + value);
            }
        }

        public boolean isValid() {
            System.out.println(                    byr != null
                    && iyr != null
                    && eyr != null
                    && hgt != null
                    && hcl != null
                    && ecl != null
                    && pid != null
                    && furtherValidate());

            System.out.println(toString() + "\n");
            return
                    byr != null
                    && iyr != null
                    && eyr != null
                    && hgt != null
                    && hcl != null
                    && ecl != null
                    && pid != null
                    && furtherValidate();
        }

        private boolean furtherValidate() {
            int byrInt = Integer.parseInt(byr);
            if (byrInt < 1920 || byrInt > 2002) {
                return false;
            }
            System.out.println("a");

            int iyrInt = Integer.parseInt(iyr);
            if (iyrInt < 2010 || iyrInt > 2020) {
                return false;
            }
            System.out.println("b");

            int eyrInt = Integer.parseInt(eyr);
            if (eyrInt < 2020 || eyrInt > 2030) {
                return false;
            }
            System.out.println("c");

            String[] splitHgt = hgt.split("\\D");
            String hgtUnit = hgt.substring(splitHgt[0].length());
            int hgtValue = Integer.parseInt(splitHgt[0]);
            if (hgtUnit.equals("cm")) {
                if (hgtValue < 150 || hgtValue > 193) {
                    return false;
                }
            } else if (hgtUnit.equals("in")) {
                if (hgtValue < 59 || hgtValue > 76) {
                    return false;
                }
            } else if (hgtUnit.equals("")) {
                return false;
            } else {
                System.err.println("Unknown unit " + hgtUnit);
            }
            System.out.println("d");

            if (hcl.charAt(0) != '#' || hcl.length() != 7) {
                return false;
            }
            if (hcl.substring(1, hcl.length() - 1).matches("(\\d|[a-f]){6}")) {
                return false;
            }
            System.out.println("e");

            if (! (ecl.equals("amb") ||
                    ecl.equals("blu") ||
                    ecl.equals("brn") ||
                    ecl.equals("gry") ||
                    ecl.equals("grn") ||
                    ecl.equals("hzl") ||
                    ecl.equals("oth"))) {
                return false;
            }
            System.out.println("f");

            if (!pid.matches("\\d{9}")) {
                return false;
            }
            System.out.println("g");

            return true;


        }

        public String toString() {
            return  "byr:" + byr + "\n" +
                    "iyr:" + iyr + "\n" +
                    "eyr:" + eyr + "\n" +
                    "hgt:" + hgt + "\n" +
                    "hcl:" + hcl + "\n" +
                    "ecl:" + ecl + "\n" +
                    "pid:" + pid + "\n" +
                    "cid:" + cid + "\n";
        }
    }
}

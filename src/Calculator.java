import java.io.IOException;
import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input:");
        String expression = input.nextLine();
        String answer = Main.calc(expression);


        System.out.println("Output:\n" + answer);
    }
}

class Main {
    public static String calc(String input) throws IOException {
        boolean romOrAr = false;
        Main romExamination = new Main();
        Main arToRom = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3) {
            throw new IOException("throws Exception");
        }
        Integer numb1 = 0;
        Integer numb2 = 0;
        try {
            numb1 = Integer.valueOf(inputSplit[0]);
            numb2 = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                numb1 = romExamination.romToAr(inputSplit[0]);
                numb2 = romExamination.romToAr(inputSplit[2]);
                romOrAr = true;
            } catch (NumberFormatException ex) {
                throw new IOException("throws Exception");
            }
        }

        if ((numb1 < 1) || (numb1 > 10) || (numb2 < 1) || (numb2 > 10))
            throw new IOException("throws Exception");

        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = numb1 + numb2;
            case "-" -> result = numb1 - numb2;
            case "*" -> result = numb1 * numb2;
            case "/" -> result = numb1 / numb2;
            default -> {
                throw new IOException("throws Exception");
            }
        }

        String output;

        if (romOrAr) {
            if (result < 1) {
                throw new IOException("throws Exception");
            } else {
                output = arToRom.arToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }

        return output;
    }

    Integer romToAr(String romInput) {
        int result = 0;
        int[] ar = {10, 9, 5, 4, 1};
        String[] rom = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < ar.length; i++) {
            while (romInput.indexOf(rom[i]) == 0) {
                result += ar[i];
                romInput = romInput.substring(rom[i].length());
            }
        }

        return result;
    }

    String arToRome(int arInput) {
        String result = "";
        int value = 0;
        int[] ar = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rom = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < ar.length; i++) {
            value = arInput / ar[i];
            for (int j = 0; j < value; j++) {
                result = result.concat(rom[i]);
            }
            arInput = arInput % ar[i];
        }
        return result;
    }
}
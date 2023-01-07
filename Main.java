import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение, используя действия +, -, *, / и числа 1-10 или I-X");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String operator;
        String result;
        boolean isRoman;

        String[] operands = input.split("[^0-9IVX ]");

        operands[0] = operands[0].trim();
        operands[1] = operands[1].trim();

        if (operands.length != 2) throw new Exception("Должно быть два операнда");

        operator = detectOperator(input);

        if (operator == null) throw new Exception("Неподдерживаемая операция");

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            num1 = Roman.convertToArabic(operands[0]);
            num2 = Roman.convertToArabic(operands[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Числа должны быть в одном формате");
        }

        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = math(num1, num2, operator);

        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Римские числа не могут быть отрицательными");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperator(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int math(int a, int b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };

    }

}

class Roman {

    static String[] romanArray = new String[] {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X","XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX","XXI", "XXII", "XXIII", "XXIV","XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI","XXXVII",
            "XXXVIII", "XXXIX", "XL","XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII","XLIX", "L", "LI",
            "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX","LXI", "LXII","LXIII", "LXIV", "LXV",
            "LXVI", "LXVII", "LXVIII", "LXIX", "LXX","LXXI", "LXXII", "LXXIII", "LXXIV","LXXV", "LXXVI", "LXXVII",
            "LXXVIII", "LXXIX", "LXXX","LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV","LXXXVI", "LXXXVII", "LXXXVIII",
            "LXXXIX", "XC","XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII","XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (String s : romanArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabic(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}
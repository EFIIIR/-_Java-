package libraries.com;
import java.util.Scanner;
public class Main {
    public static int Arabic(String s) {   //Преобразование римских чисел в арабские
        int arab = 0;
        int pr = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = romanValue(s.charAt(i));
            if (curr < pr) {
                arab -= curr;
            } else {
                arab += curr;
            }
            pr = curr;
        }
        return arab;
    }
    public static String arToRoman(int arabic) { //Преобразования арабских в римские
        String roman = "";
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < values.length; i++) {
            while (arabic >= values[i]) {
                roman += symbols[i];
                arabic -= values[i];
            }
        }
        return roman;
    }
    public static int romanValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            default:
                return 0;
        }
    }
    public static boolean isRoman(String s) { //Проверка на то, является ли строка римской цифрой
        return s.matches("[IVX]+");
    }
    public static boolean isArabic(String s) { //Проверка на то, является ли строка арабской цифрой
        return s.matches("[1-9]|10");
    }
    public static int calculate(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                int result = a - b;
                if (result < 0) {
                    throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
                }
                return result;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }
    public static String calc(String input) throws IllegalArgumentException{
        String[] parts = input.split(" "); // Разбиение на 3 части 1)первое числ 2)операция 3)второе число
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверно"); // Исключение
        }
        String first = parts[0];
        char op = parts[1].charAt(0);
        String second = parts[2];
        boolean isArabic = isArabic(first) && isArabic(second); // Проверка
        boolean isRoman = isRoman(first) && isRoman(second); // Проверка
        if (!isArabic && !isRoman) {
            throw new IllegalArgumentException("Неверно");
        }
        int a = isArabic ? Integer.parseInt(first) : Arabic(first);  // Преобразуем в арабские, если числа римские
        int b = isArabic ? Integer.parseInt(second) : Arabic(second);
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа Error");
        }
        int result = calculate(a, b, op);
        String output = isArabic ? String.valueOf(result) : arToRoman(result); // Преобразование в римское , если число было арабским
        return output;
    }
    public static void main(String[] args) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = calc(input);
        System.out.println(output);
    }
}
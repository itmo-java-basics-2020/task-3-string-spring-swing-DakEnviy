package ru.itmo.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {
        if (inputArray == null) return new int[0];

        final int length = inputArray.length;
        final int[] outputArray = new int[length];

        if (length == 0) return outputArray;

        if (length > 1) System.arraycopy(inputArray, 0, outputArray, 1, length - 1);
        outputArray[0] = inputArray[length - 1];

        return outputArray;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     *
     * Если входной массив пуст или равен null - вернуть 0
     *
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {
        if (inputArray == null) return 0;
        if (inputArray.length == 1) return inputArray[0];

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (final int num : inputArray) {
            if (num >= max1) {
                max2 = max1;
                max1 = num;

            } else if (num > max2) {
                max2 = num;
            }
        }

        return max1 * max2;
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     *
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null || input.isEmpty()) return 0;

        int count = 0;
        for (final char c : input.toLowerCase().toCharArray()) {
            if (c == 'a' || c == 'b') {
                ++count;
            }
        }

        return (int) (count / (double) input.length() * 100);
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if (input == null) return false;
        if (input.isEmpty()) return true;

        final int length = input.length();
        final int halfLength = length / 2;

        for (int i = 0; i < halfLength; ++i) {
            if (input.charAt(i) != input.charAt(length - i - 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        if (input == null || input.isEmpty()) return "";

        final char[] charArray = input.toCharArray();
        final StringBuilder builder = new StringBuilder();

        char current = charArray[0];
        int count = 1;

        for (int i = 1; i < charArray.length; ++i) {
            final char c = charArray[i];

            if (c == current) {
                ++count;

            } else {
                builder.append(current).append(count);
                current = c;
                count = 1;
            }
        }

        builder.append(current).append(count);

        return builder.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {
        if (one == null || two == null || one.isEmpty() || two.isEmpty()) return false;

        final Map<Character, Integer> charMap = new HashMap<>();

        for (final char c : one.toCharArray()) {
            final Integer count = charMap.get(c);
            charMap.put(c, (count == null ? 0 : count) + 1);
        }

        for (final char c : two.toCharArray()) {
            final Integer count = charMap.get(c);
            if (count == null || count == 0) return false;

            charMap.put(c, count - 1);
        }

        return charMap.values().parallelStream()
            .allMatch(x -> x == 0);
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        if (s == null || s.isEmpty()) return false;

        final char[] charArray = s.toCharArray();
        Arrays.sort(charArray);

        for (int i = 0; i < charArray.length - 1; ++i) {
            if (charArray[i] == charArray[i + 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     *
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if (m == null) return new int[][]{{}, {}};

        final int size = m.length;
        final int[][] transposed = new int[size][size];

        try {
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    transposed[j][i] = m[i][j];
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return new int[][]{{}, {}};
        }

        return transposed;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     *
     * Запрещается пользоваться функций join
     *
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if (inputStrings == null) return "";

        final char sep = separator != null ? separator : ' ';
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < inputStrings.length; ++i) {
            builder.append(inputStrings[i]);

            if (i < inputStrings.length - 1) {
                builder.append(sep);
            }
        }

        return builder.toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if (inputStrings == null || prefix == null || prefix.isEmpty()) return 0;

        int count = 0;
        for (final String str : inputStrings) {
            if (str.startsWith(prefix)) {
                ++count;
            }
        }

        return count;
    }
}

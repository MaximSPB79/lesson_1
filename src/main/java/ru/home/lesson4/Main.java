package ru.home.lesson4;

public class Main {
    public static void main(String[] args) {


        String[][] array = new String[4][4]; // если изменить размер массива, выдаст исключение
        try {
            metodArray(array);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }

    /**
     * @param array
     * @throws MyArraySizeException
     * @throws MyArrayDataException
     */
    private static void metodArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4 на 4. Измените параметры");
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = String.valueOf(i + j);
                    // array[2][3] = "d"; // если раскомментировать строку, то выдаст исключение
                    System.out.print(array[i][j]);
                    try {
                        sum += Integer.parseInt(array[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException("Невозможно преоброзовать ячейку array[" + i + "][" + j + "]");
                    }
                }
                System.out.println();
            }
            System.out.println(sum);
        }
    }
}



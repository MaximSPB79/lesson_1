package ru.home.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {

    private static int winCount; // счетчик ячеек для победы
    private static int winSize; // размер победной серии
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '•';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SPACE_MAP = "|";
    private static final String SPACE_HEADER_MAP = "-";
    private static final String HEADER_FIRST_SYMBOL = "+";
    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля
    private static final Random RANDOM = new Random();
    private static int fieldSize; // Размерность игрового поля


    public static void main(String[] args) throws InterruptedException {

        playGame();

    }

    public static void playGame() throws InterruptedException {
        do {
            initialize();
            printField();
            winSize = getWinSize();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!"))
                    break;
                aiThinks();
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Компьютер победил!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
        } while (SCANNER.next().equalsIgnoreCase("Y"));
    }

    /**
     * Инициализация игрового поля
     */
    private static void initialize() {

        inputSize();

        field = new char[fieldSize][fieldSize];
        // Пройдем по всем элементам массива
        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * ввод размера игрового поля пользователем
     */
    private static void inputSize() {

        do {
            System.out.println("Введите размер поля от 1 до 9:");
            fieldSize = SCANNER.nextInt();
        } while (fieldSize < 0 || fieldSize > 9);
    }

    /**
     * Отрисовка игрового поля
     */
    private static void printField() {
        printHeaderField();
        printBodyField();
    }

    /**
     * Печать шапки игрового поля
     */
    private static void printHeaderField() {
        System.out.print(HEADER_FIRST_SYMBOL + SPACE_HEADER_MAP);
        for (int i = 0; i < fieldSize; i++) {
            printNumberMap(i, SPACE_HEADER_MAP);
        }
        System.out.println();
    }

    /**
     * печать номера на поле
     */
    private static void printNumberMap(int i, String s) {
        System.out.print(i + 1 + s);
    }

    /**
     * печать поля
     */
    private static void printBodyField() {
        for (int i = 0; i < fieldSize; i++) {
            printNumberMap(i, SPACE_MAP);
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(field[i][j] + SPACE_MAP);
            }
            System.out.println();
        }
    }


    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты хода X и Y (от 1 до " + fieldSize + ") через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[y][x] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellEmpty(int x, int y) {

        return field[y][x] == DOT_EMPTY;

    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива, игрового поля)
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        return x > -1 && x < fieldSize && y > -1 && y < fieldSize;
    }

    /**
     * Визуализация мыслительного процесса компьютера
     *
     * @throws InterruptedException
     */
    private static void aiThinks() throws InterruptedException {
        System.out.println("Подождите, компьютер думает");
        for (int i = 0; i < 15; i++) {
            System.out.print(">");
            Thread.sleep(120);
        }
        System.out.println();
    }

    /**
     * Ход компьютера, c возможностью блокировать победу человека
     */
    private static void aiTurn() {

        int x, y;

        for (y = 0; y < fieldSize; y++) {
            for (x = 0; x < fieldSize; x++) {
                if (isCellEmpty(x, y)) { // если ячейка пустая
                    field[y][x] = DOT_HUMAN; // записываем в нее символ хода человека
                    if (checkWin(DOT_HUMAN)) { // если находится победная серия
                        field[y][x] = DOT_AI; // мы перезаписываем в нее символ хода компьютера
                        return; // и останавливаем метод
                    }
                    field[y][x] = DOT_EMPTY; // если победной серии не нашли возвращаем пустую ячейку на место
                }
            }
        }
        do { // и выполняем рандомный ход
            x = RANDOM.nextInt(fieldSize);
            y = RANDOM.nextInt(fieldSize);
        } while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
    }

    /**
     * Проверка победы
     *
     * @param c
     * @return
     */
    private static boolean checkWin(char c) {

        return checkRowsAndColumns(c) || checkDiagonals(c);

    }

    /**
     * Проверка на победу столбцы и строки
     *
     * @param c
     * @return
     */

    private static boolean checkRowsAndColumns(char c) {
        return checkingRows(c) || checkingColumns(c);
    }

    /**
     * Проверка на победу диагонали
     *
     * @param c
     * @return
     */
    private static boolean checkDiagonals(char c) {
        return checkingDiagonalsCenter(c) || checkingDiagonalSide(c)
                || checkingDiagonalsCenterUpper(c) || checkingDiagonalsCenterBottom(c) ||
                checkDiagonalSideUpper(c) || checkDiagonalSideBottom(c);
    }

    /**
     * проверка на победу строки
     *
     * @param c
     * @return
     */
    private static boolean checkingRows(char c) {

        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                if (field[y][x] == c) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == winSize) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * проверка на победу столбцы
     *
     * @param c
     * @return
     */
    private static boolean checkingColumns(char c) {

        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                if (field[y][x] == c) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == winSize) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Определяем количество ячеек для победной серии
     *
     * @return
     */
    public static int getWinSize() {
        return fieldSize < 4 ? 3 : 4;
    }

    /**
     * проверка на победу центральная диагональ
     *
     * @param c
     * @return
     */
    private static boolean checkingDiagonalsCenter(char c) {

        for (int i = 0; i < fieldSize; i++) {
            if (field[i][i] == c) {
                winCount++;
            } else {
                winCount = 0;
            }
            if (winCount == winSize) {
                return true;
            }
        }
        return false;
    }

    /**
     * проверка на победу центральная диагональ
     *
     * @param c
     * @return
     */
    private static boolean checkingDiagonalSide(char c) {

        for (int i = 0; i < fieldSize; i++) {
            if (field[i][fieldSize - 1 - i] == c) {
                winCount++;
            } else {
                winCount = 0;
            }
            if (winCount == winSize) {
                return true;
            }
        }
        return false;
    }

    /**
     * проверка диагоналей, сверху от центральной
     *
     * @param c
     * @return
     */
    private static boolean checkingDiagonalsCenterUpper(char c) {

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize - i; j++) {
                if (field[j][j + i] == c) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == winSize) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * проверка диагоналей, снизу от центральной
     *
     * @param c
     * @return
     */
    private static boolean checkingDiagonalsCenterBottom(char c) {

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize - i; j++) {
                if (field[j + i][j] == c) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == winSize) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * проверка диагоналей сверху от побочной
     *
     * @param c
     * @return
     */
    private static boolean checkDiagonalSideUpper(char c) {

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize - i; j++) {
                if (field[j][fieldSize - 1 - j - i] == c) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == winSize) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * проверка диагоналей снизу от побочной
     *
     * @param c
     * @return
     */
    private static boolean checkDiagonalSideBottom(char c) {

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 1; j < fieldSize - i; j++) {
                if (field[j][fieldSize - j - i] == c) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == winSize) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверка на ничью
     *
     * @return
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++)
                if (isCellEmpty(x, y)) {
                    return false;
                }
        }
        return true;
    }

    /**
     * Метод проверки состояния игры
     *
     * @param c
     * @param str
     * @return
     */
    static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        } else if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        } else {
            return false; // Игра продолжается
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    public static void main(String[] args) throws IOException, InterruptedException {

        Player player1 = new Player();
        System.out.println("Игрок 1, введи, пожалуйста, своё имя:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        player1.setName(reader.readLine());

        Player player2 = new Player();
        System.out.println("Игрок 2, введи, пожалуйста, своё имя:");
        player2.setName(reader.readLine());

        player1.setOpponent(player2);
        player2.setOpponent(player1);

        PlayingBoard boardPlayer1 = new PlayingBoard();
        char[][] board1 = new char[11][11];
        boardPlayer1.setBoard(board1);
        player1.setOwnBoard(boardPlayer1);

        PlayingBoard boardPlayer2 = new PlayingBoard();
        char[][] board2 = new char[11][11];
        boardPlayer2.setBoard(board2);
        player2.setOwnBoard(boardPlayer2);

        PlayingBoard boardOfOpponentPlayer1 = new PlayingBoard();
        char[][] board3 = new char[11][11];
        boardOfOpponentPlayer1.setBoard(board3);
        player1.setOpponentBoard(boardOfOpponentPlayer1);

        PlayingBoard boardOfOpponentPlayer2 = new PlayingBoard();
        char[][] board4 = new char[11][11];
        boardOfOpponentPlayer2.setBoard(board4);
        player2.setOpponentBoard(boardOfOpponentPlayer2);

        System.out.println();
        System.out.println("Перед началом игры ознакомьтесь с правилами:");
        System.out.println();
        System.out.println("\u2488" + " Сейчас вам будет предложено разместить корабли на своих полях.");
        System.out.println("   Для этого вам нужно будет указать координаты клеток, в которых вы желаете разместить корабли. Учтите, что:");
        System.out.println("\u2705" + " Корабль - это одна или несколько последовательно идущих клеток (по вертикали или горизонтали).");
        System.out.println("\u2705" + " Корабль не должен касаться других кораблей сторонами и углами.");
        System.out.println();
        System.out.println("\u2489" + " После размещения всех кораблей начнется бой.");
        System.out.println("   Вам нужно будет вводить координаты клеток, в которых могут находиться корабли соперника.");
        System.out.println("   Обозначения на карте будут следующими:");
        System.out.println("\u274C" + ": \"Мимо!\" (право следующего хода передается сопернику);");
        System.out.println("\u23F9" + ": \"Ранил(а)!\" (право следующего хода остается у игрока, сделавшего данный ход);");
        System.out.println("\u2B50" + ": \"Убил(а)!\" (право следующего хода остается у игрока, сделавшего данный ход);");
        System.out.println();
        System.out.println("Удачи!");
        System.out.println();


        System.out.println(player1.getName() + ", начнем расставлять корабли на твоем поле " + '\u2B50' + " " + player2.getName() + ", отвернись!");
        boardPlayer1.printBoard();
        placeShips(player1);

        System.out.println(player2.getName() + ", начнем расставлять корабли на твоем поле " + '\u2B50' + " " + player1.getName() + ", отвернись!");
        boardPlayer2.printBoard();
        placeShips(player2);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Корабли расставлены. Начинаем бой!");
        Thread.sleep(2000);
        System.out.println("Сейчас случайным образом будет определен игрок, которому достанется право первого хода");
        Thread.sleep(2000);
        System.out.println("И это ...");
        Thread.sleep(2000);

        Random random = new Random();
        int r = random.nextInt(2);
        if (r == 0) {
            System.out.println(player1.getName() + "!");
            Thread.sleep(1000);
            player1.fight(player2);
        } else {
            System.out.println(player2.getName() + "!");
            Thread.sleep(1000);
            player2.fight(player1);
        }
    }

    public static void placeShipFour(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты четырехпалубного корабля (формат: x1,y1; x2,y2; x3,y3; x4,y4)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correct = true;
        boolean correctLength = true;

        if (array.length != 8) {
            correctLength = false;
        } else if ((Integer.parseInt(array[1]) == Integer.parseInt(array[3])) && ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) != 1) || (Integer.parseInt(array[4]) - (Integer.parseInt(array[2])) != 1 || Integer.parseInt(array[6]) - (Integer.parseInt(array[4])) != 1))) {
            correct = false;
        } else if (Integer.parseInt(array[0]) == Integer.parseInt(array[2]) && ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) != 1) || (Integer.parseInt(array[5]) - (Integer.parseInt(array[3])) != 1 || Integer.parseInt(array[7]) - (Integer.parseInt(array[5])) != 1))) {
            correct = false;
        } else if ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) == 1) && (Integer.parseInt(array[1]) != Integer.parseInt(array[3]) || Integer.parseInt(array[1]) != Integer.parseInt(array[5]) || Integer.parseInt(array[1]) != Integer.parseInt(array[7]))) {
            correct = false;
        } else if ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) == 1) && (Integer.parseInt(array[0]) != Integer.parseInt(array[2]) || Integer.parseInt(array[0]) != Integer.parseInt(array[4]) || Integer.parseInt(array[0]) != Integer.parseInt(array[6]))) {
            correct = false;
        }

        if (correct && correctLength) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[2]), Integer.parseInt(array[3]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[4]), Integer.parseInt(array[5]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[6]), Integer.parseInt(array[7]));
            player.getOwnBoard().printBoard();
            player.setShipFour1(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Integer.parseInt(array[7])});
        } else if (!correct) {
            System.out.println("Неверный формат ввода. Корабль - это одна или несколько последовательно идущих клеток (по вертикали или горизонтали).");
            placeShipFour(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты четырехпалубного корабля.");
            placeShipFour(player);
        }
    }

    public static void placeShipThree1(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты первого трехпалубного корабля (формат: x1,y1; x2,y2; x3,y3)");

        String s = scanner.nextLine();

        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correct = true;
        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 6) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50' || player.getOwnBoard().getBoard()[Integer.parseInt(array[3])][Integer.parseInt(array[2]) - 1] == '\u2B50' || player.getOwnBoard().getBoard()[Integer.parseInt(array[5])][Integer.parseInt(array[4]) - 1] == '\u2B50') {
            correctPlace = false;
        } else if (Integer.parseInt(array[1]) == Integer.parseInt(array[3]) && ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) != 1) || (Integer.parseInt(array[4]) - (Integer.parseInt(array[2])) != 1))) {
            correct = false;
        } else if (Integer.parseInt(array[0]) == Integer.parseInt(array[2]) && ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) != 1) || (Integer.parseInt(array[5]) - (Integer.parseInt(array[3])) != 1))) {
            correct = false;
        } else if ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) == 1) && (Integer.parseInt(array[1]) != Integer.parseInt(array[3]) || Integer.parseInt(array[1]) != Integer.parseInt(array[5]))) {
            correct = false;
        } else if ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) == 1) && (Integer.parseInt(array[0]) != Integer.parseInt(array[2]) || Integer.parseInt(array[0]) != Integer.parseInt(array[4]))) {
            correct = false;
        }

        if (correct && correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[2]), Integer.parseInt(array[3]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[4]), Integer.parseInt(array[5]));
            player.getOwnBoard().printBoard();
            player.setShipThree1(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipThree1(player);
        } else if (!correct) {
            System.out.println("Неверный формат ввода. Корабль - это одна или несколько последовательно идущих клеток (по вертикали или горизонтали).");
            placeShipThree1(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты трехпалубного корабля.");
            placeShipThree1(player);
        }
    }

    public static void placeShipThree2(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты второго трехпалубного корабля (формат: x1,y1; x2,y2; x3,y3)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correct = true;
        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 6) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50' || player.getOwnBoard().getBoard()[Integer.parseInt(array[3])][Integer.parseInt(array[2]) - 1] == '\u2B50' || player.getOwnBoard().getBoard()[Integer.parseInt(array[5])][Integer.parseInt(array[4]) - 1] == '\u2B50') {
            correctPlace = false;
        } else if (Integer.parseInt(array[1]) == Integer.parseInt(array[3]) && ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) != 1) || (Integer.parseInt(array[4]) - (Integer.parseInt(array[2])) != 1))) {
            correct = false;
        } else if (Integer.parseInt(array[0]) == Integer.parseInt(array[2]) && ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) != 1) || (Integer.parseInt(array[5]) - (Integer.parseInt(array[3])) != 1))) {
            correct = false;
        } else if ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) == 1) && (Integer.parseInt(array[1]) != Integer.parseInt(array[3]) || Integer.parseInt(array[1]) != Integer.parseInt(array[5]))) {
            correct = false;
        } else if ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) == 1) && (Integer.parseInt(array[0]) != Integer.parseInt(array[2]) || Integer.parseInt(array[0]) != Integer.parseInt(array[4]))) {
            correct = false;
        }

        if (correct && correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[2]), Integer.parseInt(array[3]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[4]), Integer.parseInt(array[5]));
            player.getOwnBoard().printBoard();
            player.setShipThree2(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipThree2(player);
        } else if (!correct) {
            System.out.println("Неверный формат ввода. Корабль - это одна или несколько последовательно идущих клеток (по вертикали или горизонтали).");
            placeShipThree2(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты трехпалубного корабля.");
            placeShipThree2(player);
        }
    }

    public static void placeShipTwo1(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты первого двухпалубного корабля (формат: x1,y1; x2,y2)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correct = true;
        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 4) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50' || player.getOwnBoard().getBoard()[Integer.parseInt(array[3])][Integer.parseInt(array[2]) - 1] == '\u2B50') {
            correctPlace = false;
        } else if (Integer.parseInt(array[1]) == Integer.parseInt(array[3]) && ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) != 1))) {
            correct = false;
        } else if (Integer.parseInt(array[0]) == Integer.parseInt(array[2]) && ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) != 1))) {
            correct = false;
        } else if ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) == 1) && (Integer.parseInt(array[1]) != Integer.parseInt(array[3]))) {
            correct = false;
        } else if ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) == 1) && (Integer.parseInt(array[0]) != Integer.parseInt(array[2]))) {
            correct = false;
        }

        if (correct && correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[2]), Integer.parseInt(array[3]));
            player.getOwnBoard().printBoard();
            player.setShipTwo1(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipTwo1(player);
        } else if (!correct) {
            System.out.println("Неверный формат ввода. Корабль - это одна или несколько последовательно идущих клеток (по вертикали или горизонтали).");
            placeShipTwo1(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты двухпалубного корабля.");
            placeShipTwo1(player);
        }
    }

    public static void placeShipTwo2(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты второго двухпалубного корабля (формат: x1,y1; x2,y2)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correct = true;
        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 4) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50' || player.getOwnBoard().getBoard()[Integer.parseInt(array[3])][Integer.parseInt(array[2]) - 1] == '\u2B50') {
            correctPlace = false;
        } else if (Integer.parseInt(array[1]) == Integer.parseInt(array[3]) && ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) != 1))) {
            correct = false;
        } else if (Integer.parseInt(array[0]) == Integer.parseInt(array[2]) && ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) != 1))) {
            correct = false;
        } else if ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) == 1) && (Integer.parseInt(array[1]) != Integer.parseInt(array[3]))) {
            correct = false;
        } else if ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) == 1) && (Integer.parseInt(array[0]) != Integer.parseInt(array[2]))) {
            correct = false;
        }

        if (correct && correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[2]), Integer.parseInt(array[3]));
            player.getOwnBoard().printBoard();
            player.setShipTwo2(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipTwo2(player);
        } else if (!correct) {
            System.out.println("Неверный формат ввода. Корабль - это одна или несколько последовательно идущих клеток (по вертикали или горизонтали).");
            placeShipTwo2(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты двухпалубного корабля.");
            placeShipTwo2(player);
        }
    }

    public static void placeShipTwo3(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты третьего двухпалубного корабля (формат: x1,y1; x2,y2)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correct = true;
        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 4) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50' || player.getOwnBoard().getBoard()[Integer.parseInt(array[3])][Integer.parseInt(array[2]) - 1] == '\u2B50') {
            correctPlace = false;
        } else if (Integer.parseInt(array[1]) == Integer.parseInt(array[3]) && ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) != 1))) {
            correct = false;
        } else if (Integer.parseInt(array[0]) == Integer.parseInt(array[2]) && ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) != 1))) {
            correct = false;
        } else if ((Integer.parseInt(array[2]) - (Integer.parseInt(array[0])) == 1) && (Integer.parseInt(array[1]) != Integer.parseInt(array[3]))) {
            correct = false;
        } else if ((Integer.parseInt(array[3]) - (Integer.parseInt(array[1])) == 1) && (Integer.parseInt(array[0]) != Integer.parseInt(array[2]))) {
            correct = false;
        }

        if (correct && correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[2]), Integer.parseInt(array[3]));
            player.getOwnBoard().printBoard();
            player.setShipTwo3(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipTwo3(player);
        } else if (!correct) {
            System.out.println("Неверный формат ввода. Корабль - это одна или несколько последовательно идущих клеток (по вертикали или горизонтали).");
            placeShipTwo3(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты двухпалубного корабля.");
            placeShipTwo3(player);
        }
    }

    public static void placeShipOne1(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты первого однопалубного корабля (формат: x1,y1)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 2) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50') {
            correctPlace = false;
        }

        if (correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().printBoard();
            player.setShipOne1(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipOne1(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты однопалубного корабля.");
            placeShipOne1(player);
        }
    }

    public static void placeShipOne2(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты второго однопалубного корабля (формат: x1,y1)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 2) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50') {
            correctPlace = false;
        }

        if (correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().printBoard();
            player.setShipOne2(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipOne2(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты однопалубного корабля.");
            placeShipOne2(player);
        }
    }

    public static void placeShipOne3(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты третьего однопалубного корабля (формат: x1,y1)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 2) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50') {
            correctPlace = false;
        }

        if (correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().printBoard();
            player.setShipOne3(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipOne3(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты однопалубного корабля.");
            placeShipOne3(player);
        }
    }

    public static void placeShipOne4(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты четвертого однопалубного корабля (формат: x1,y1)");

        String s = scanner.nextLine();
        s = s.replaceAll("[.,;/]", " ").replace("  ", " ");
        String[] array = s.split(" ");

        boolean correctLength = true;
        boolean correctPlace = true;

        if (array.length != 2) {
            correctLength = false;
        } else if (player.getOwnBoard().getBoard()[Integer.parseInt(array[1])][Integer.parseInt(array[0]) - 1] == '\u2B50') {
            correctPlace = false;
        }

        if (correctLength && correctPlace) {
            player.getOwnBoard().setFirstShips(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            player.getOwnBoard().printBoard();
            player.setShipOne1(new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1])});
        } else if (!correctPlace) {
            System.out.println("Неверный формат ввода. Данные координаты уже заняты другим кораблем");
            placeShipOne1(player);
        } else {
            System.out.println("Неверный формат ввода. Необходимо вести координаты однопалубного корабля.");
            placeShipOne1(player);
        }
    }

    public static void placeShips(Player player) {

        placeShipFour(player);
        placeShipThree1(player);
        placeShipThree2(player);
        placeShipTwo1(player);
        placeShipTwo2(player);
        placeShipTwo3(player);
        placeShipOne1(player);
        placeShipOne2(player);
        placeShipOne3(player);
        placeShipOne4(player);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}







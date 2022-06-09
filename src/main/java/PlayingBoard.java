import java.util.ArrayList;
import java.util.List;

public class PlayingBoard {

    private int counter = 1;
    private final List<Integer> temp = new ArrayList<>();
    private char[][] board;
    private int counterOfShipsKilled = 0;

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '\u2B1C';
            }
        }
    }

    public void printBoard() {
        System.out.println("  " + "\u2488" + " " + "\u2489" + " " + "\u248A" + " " + "\u248B" + " " + "\u248C" + " " + "\u248D" + " " + "\u248E" + " " + "\u248F" + " " + "\u2490" + " " + "\u2491");
        for (int i = 1; i <= 10; i++) {
            if (i == 1) System.out.print("\u2488" + " ");
            if (i == 2) System.out.print("\u2489" + " ");
            if (i == 3) System.out.print("\u248A" + " ");
            if (i == 4) System.out.print("\u248B" + " ");
            if (i == 5) System.out.print("\u248C" + " ");
            if (i == 6) System.out.print("\u248D" + " ");
            if (i == 7) System.out.print("\u248E" + " ");
            if (i == 8) System.out.print("\u248F" + " ");
            if (i == 9) System.out.print("\u2490" + " ");
            if (i == 10) System.out.print("\u2491" + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setFirstShips(int a, int b) {
        board[b][a - 1] = '\u2B50';
    }

    public void setOpponentShip(Player player, int a, int b) {

        // если попали в пустую клетку
        if ((player.getOwnBoard().getBoard()[b][a - 1]) == '\u2B1C') {
            System.out.println("Мимо!");
            board[b][a - 1] = '\u274C';
            printBoard();

            System.out.println();
            player.fight(player.getOpponent());

        // если попали в однопалубный корабль
        } else if (counter == 1 && (a == player.getShipOne4()[0] && b == player.getShipOne4()[1]) || (a == player.getShipOne3()[0] && b == player.getShipOne3()[1]) || (a == player.getShipOne2()[0] && b == player.getShipOne2()[1]) || (a == player.getShipOne1()[0] && b == player.getShipOne1()[1])) {

            System.out.println("Убил(а)!");
            counterOfShipsKilled++;
            board[b][a - 1] = '\u2B50';

            if (counterOfShipsKilled == 10) {
                printBoard();
                System.out.println();
                System.out.println("\u2B50" + "\u2B50" + "\u2B50" + "   " + player.getOpponent().getName() + ", ты - победитель!   " + "\u2B50" + "\u2B50" + "\u2B50");
            } else {
                System.out.println();
                player.getOpponent().fight(player);
            }
        }

        // если попали в клетку с не-однопалубным кораблем
        else if ((counter == 1 && player.getOwnBoard().getBoard()[b][a - 1] == '\u2B50')) {
            System.out.println("Ранил(а)!");
            temp.add(a);
            temp.add(b);
            counter++;
            board[b][a - 1] = '\u23F9';
            printBoard();
            System.out.println();
            player.getOpponent().fight(player);

        // если попали в двухпалубный корабль ShipTwo1
        } else if (counter == 2 && player.getOwnBoard().getBoard()[b][a - 1] == '\u2B50' && ((temp.get(0) == player.getShipTwo1()[0] && temp.get(1) == player.getShipTwo1()[1] && a == player.getShipTwo1()[2] && b == player.getShipTwo1()[3]) || ((temp.get(0) == player.getShipTwo1()[2] && temp.get(1) == player.getShipTwo1()[3] && a == player.getShipTwo1()[0] && b == player.getShipTwo1()[1])))) {

            System.out.println("Убил(а)!");
            counterOfShipsKilled++;

            board[player.getShipTwo1()[1]][player.getShipTwo1()[0] - 1] = '\u2B50';
            board[player.getShipTwo1()[3]][player.getShipTwo1()[2] - 1] = '\u2B50';

            temp.clear();
            counter = 1;

            if (counterOfShipsKilled == 10) {
                printBoard();
                System.out.println();
                System.out.println("\u2B50" + "\u2B50" + "\u2B50" + "   " + player.getOpponent().getName() + ", ты - победитель!   " + "\u2B50" + "\u2B50" + "\u2B50");
            } else {
                System.out.println();
                player.getOpponent().fight(player);
            }

        // если попали в двухпалубный корабль ShipTwo2
        } else if (counter == 2 && player.getOwnBoard().getBoard()[b][a - 1] == '\u2B50' && ((temp.get(0) == player.getShipTwo2()[0] && temp.get(1) == player.getShipTwo2()[1] && a == player.getShipTwo2()[2] && b == player.getShipTwo2()[3]) || ((temp.get(0) == player.getShipTwo2()[2] && temp.get(1) == player.getShipTwo2()[3] && a == player.getShipTwo2()[0] && b == player.getShipTwo2()[1])))) {

            System.out.println("Убил(а)!");
            counterOfShipsKilled++;

            board[player.getShipTwo2()[1]][player.getShipTwo2()[0] - 1] = '\u2B50';
            board[player.getShipTwo2()[3]][player.getShipTwo2()[2] - 1] = '\u2B50';

            temp.clear();
            counter = 1;

            if (counterOfShipsKilled == 10) {
                printBoard();
                System.out.println();
                System.out.println("\u2B50" + "\u2B50" + "\u2B50" + "   " + player.getOpponent().getName() + ", ты - победитель!   " + "\u2B50" + "\u2B50" + "\u2B50");
            } else {
                System.out.println();
                player.getOpponent().fight(player);
            }

        // если попали в двухпалубный корабль ShipTwo3
        } else if (counter == 2 && player.getOwnBoard().getBoard()[b][a - 1] == '\u2B50' && ((temp.get(0) == player.getShipTwo3()[0] && temp.get(1) == player.getShipTwo3()[1] && a == player.getShipTwo3()[2] && b == player.getShipTwo3()[3]) || ((temp.get(0) == player.getShipTwo3()[2] && temp.get(1) == player.getShipTwo3()[3] && a == player.getShipTwo3()[0] && b == player.getShipTwo3()[1])))) {

            System.out.println("Убил(а)!");
            counterOfShipsKilled++;

            board[player.getShipTwo3()[1]][player.getShipTwo3()[0] - 1] = '\u2B50';
            board[player.getShipTwo3()[3]][player.getShipTwo3()[2] - 1] = '\u2B50';

            temp.clear();
            counter = 1;

            if (counterOfShipsKilled == 10) {
                printBoard();
                System.out.println();
                System.out.println("\u2B50" + "\u2B50" + "\u2B50" + "   " + player.getOpponent().getName() + ", ты - победитель!   " + "\u2B50" + "\u2B50" + "\u2B50");
            } else {
                System.out.println();
                player.getOpponent().fight(player);
            }

        } else if (counter == 2) {
            System.out.println("Ранил(а)!");
            temp.add(a);
            temp.add(b);
            counter++;
            board[b][a - 1] = '\u23F9';
            printBoard();
            player.getOpponent().fight(player);

        // если попали в трехпалубный корабль ShipThree1
        } else if (counter == 3 && player.getOwnBoard().getBoard()[b][a - 1] == '\u2B50' && (a == player.getShipThree1()[0] && b == player.getShipThree1()[1]) || (a == player.getShipThree1()[2] && b == player.getShipThree1()[3]) || (a == player.getShipThree1()[4] && b == player.getShipThree1()[5])) {

            System.out.println("Убил(а)!");
            counterOfShipsKilled++;

            board[player.getShipThree1()[1]][player.getShipThree1()[0] - 1] = '\u2B50';
            board[player.getShipThree1()[3]][player.getShipThree1()[2] - 1] = '\u2B50';
            board[player.getShipThree1()[5]][player.getShipThree1()[4] - 1] = '\u2B50';

            temp.clear();
            counter = 1;

            if (counterOfShipsKilled == 10) {
                printBoard();
                System.out.println();
                System.out.println("\u2B50" + "\u2B50" + "\u2B50" + "   " + player.getOpponent().getName() + ", ты - победитель!   " + "\u2B50" + "\u2B50" + "\u2B50");
            } else {
                System.out.println();
                player.getOpponent().fight(player);
            }

        // если попали в трехпалубный корабль ShipThree2
        } else if (counter == 3 && player.getOwnBoard().getBoard()[b][a - 1] == '\u2B50' && (a == player.getShipThree2()[0] && b == player.getShipThree2()[1]) || (a == player.getShipThree2()[2] && b == player.getShipThree2()[3]) || (a == player.getShipThree2()[4] && b == player.getShipThree2()[5])) {

            System.out.println("Убил(а)!");
            counterOfShipsKilled++;

            board[player.getShipThree2()[1]][player.getShipThree2()[0] - 1] = '\u2B50';
            board[player.getShipThree2()[3]][player.getShipThree2()[2] - 1] = '\u2B50';
            board[player.getShipThree2()[5]][player.getShipThree2()[4] - 1] = '\u2B50';

            temp.clear();
            counter = 1;

            if (counterOfShipsKilled == 10) {
                printBoard();
                System.out.println();
                System.out.println("\u2B50" + "\u2B50" + "\u2B50" + "   " + player.getOpponent().getName() + ", ты - победитель!   " + "\u2B50" + "\u2B50" + "\u2B50");
            } else {
                System.out.println();
                player.getOpponent().fight(player);
            }

        } else if (counter == 3) {
            System.out.println("Ранил(а)!");
            temp.add(a);
            temp.add(b);
            counter++;
            board[b][a - 1] = '\u23F9';
            printBoard();
            player.getOpponent().fight(player);

        // если попал в четырехпалубный корабль
        } else if ((counter == 4 && player.getOwnBoard().getBoard()[b][a - 1] == '\u2B50')) {
            System.out.println("Убил(а)!");
            counterOfShipsKilled++;

            board[player.getShipFour1()[1]][player.getShipFour1()[0] - 1] = '\u2B50';
            board[player.getShipFour1()[3]][player.getShipFour1()[2] - 1] = '\u2B50';
            board[player.getShipFour1()[5]][player.getShipFour1()[4] - 1] = '\u2B50';
            board[player.getShipFour1()[7]][player.getShipFour1()[6] - 1] = '\u2B50';

            temp.clear();
            counter = 1;

            if (counterOfShipsKilled == 10) {
                printBoard();
                System.out.println();
                System.out.println("\u2B50" + "\u2B50" + "\u2B50" + "   " + player.getOpponent().getName() + ", ты - победитель!   " + "\u2B50" + "\u2B50" + "\u2B50");
            } else {
                System.out.println();
                player.getOpponent().fight(player);
            }
        }
    }
}

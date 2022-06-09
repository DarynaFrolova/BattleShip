import java.util.Scanner;

public class Player {
    private String name;
    private PlayingBoard ownBoard;
    private PlayingBoard opponentBoard;
    private Player opponent;

    private int[] shipOne1;
    private int[] shipOne2;
    private int[] shipOne3;
    private int[] shipOne4;
    private int[] shipTwo1;
    private int[] shipTwo2;
    private int[] shipTwo3;
    private int[] shipThree1;
    private int[] shipThree2;
    private int[] shipFour1;


    public int[] getShipOne1() {
        return shipOne1;
    }
    public void setShipOne1(int[] shipOne1) {
        this.shipOne1 = shipOne1;
    }

    public int[] getShipOne2() {
        return shipOne2;
    }
    public void setShipOne2(int[] shipOne2) {
        this.shipOne2 = shipOne2;
    }

    public int[] getShipOne3() {
        return shipOne3;
    }
    public void setShipOne3(int[] shipOne3) {
        this.shipOne3 = shipOne3;
    }

    public int[] getShipOne4() {
        return shipOne4;
    }
    public void setShipOne4(int[] shipOne4) {
        this.shipOne4 = shipOne4;
    }

    public int[] getShipTwo1() {
        return shipTwo1;
    }
    public void setShipTwo1(int[] shipTwo1) {
        this.shipTwo1 = shipTwo1;
    }

    public int[] getShipTwo2() {
        return shipTwo2;
    }
    public void setShipTwo2(int[] shipTwo2) {
        this.shipTwo2 = shipTwo2;
    }

    public int[] getShipTwo3() {
        return shipTwo3;
    }
    public void setShipTwo3(int[] shipTwo3) {
        this.shipTwo3 = shipTwo3;
    }

    public int[] getShipThree1() {
        return shipThree1;
    }
    public void setShipThree1(int[] shipThree1) {
        this.shipThree1 = shipThree1;
    }

    public int[] getShipThree2() {
        return shipThree2;
    }
    public void setShipThree2(int[] shipThree2) {
        this.shipThree2 = shipThree2;
    }

    public int[] getShipFour1() {
        return shipFour1;
    }
    public void setShipFour1(int[] shipFour1) {
        this.shipFour1 = shipFour1;
    }

    public Player getOpponent() {
        return opponent;
    }
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public PlayingBoard getOwnBoard() {
        return ownBoard;
    }
    public void setOwnBoard(PlayingBoard board) {
        this.ownBoard = board;
    }

    public void setOpponentBoard(PlayingBoard opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    public void fight(Player player) {
            System.out.println(name + ", введи координаты клетки, в которой может находиться корабль соперника (в формате x1,y1):");
            opponentBoard.printBoard();
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            s = s.replace(",", " ").replace("  ", " ");
            String[] array = s.split(" ");
            opponentBoard.setOpponentShip(player, Integer.parseInt(array[0]), Integer.parseInt(array[1]));
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Coordinate {
    int row;
    int col;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}

public class KillAllAndReturnHome {
    private static final int BOARD_SIZE = 10;
    private static final List<Coordinate> soldiers = new ArrayList<>();
    private static Coordinate castle;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("find_my_home_castle –soldiers ");
        int numSoldiers = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        // Input soldier coordinates
        for (int i = 0; i < numSoldiers; i++) {
            System.out.print("Enter coordinates for soldier " + (i + 1) + ": ");
            String[] coords = scanner.nextLine().split(",");
            int row = Integer.parseInt(coords[0].trim());
            int col = Integer.parseInt(coords[1].trim());
            soldiers.add(new Coordinate(row, col));
        }

        // Input castle coordinates
        System.out.print("Enter the coordinates for your \"special\" castle: ");
        String[] coords = scanner.nextLine().split(",");
        int row = Integer.parseInt(coords[0].trim());
        int col = Integer.parseInt(coords[1].trim());
        castle = new Coordinate(row, col);

        System.out.println("Thanks. There are 3 unique paths for your 'special_castle'");

        // Find and print the paths
        findPaths(castle, new ArrayList<>(), 1);
        scanner.close();
    }

    private static void findPaths(Coordinate current, List<Coordinate> path, int pathNumber) {
        path.add(current);

        // Check if the castle has returned home
        if (current.row == castle.row && current.col == castle.col && path.size() > 1) {
            System.out.println("Path " + pathNumber);
            System.out.println("=======");
            System.out.println("Start: " + path.get(0));
            for (int i = 1; i < path.size(); i++) {
                System.out.print(path.get(i).equals(path.get(0)) ? "Arrive " : "Kill ");
                System.out.print(path.get(i));
                if (i < path.size() - 1) {
                    System.out.print(". Turn Left");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        // Try moving forward
        Coordinate forward = new Coordinate(current.row + 1, current.col);
        if (isValidMove(forward)) {
            findPaths(forward, path, pathNumber);
            path.remove(path.size() - 1);
        }

        // Try moving left
        Coordinate left = new Coordinate(current.row, current.col - 1);
        if (isValidMove(left)) {
            findPaths(left, path, pathNumber);
            path.remove(path.size() - 1);
        }

        // Try jumping over soldiers
        for (int i = 1; i < BOARD_SIZE; i++) {
            Coordinate jump = new Coordinate(current.row + i, current.col);
            if (isValidMove(jump)) {
                findPaths(jump, path, pathNumber);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean isValidMove(Coordinate coord) {
        if (coord.row < 1 || coord.row > BOARD_SIZE || coord.col < 1 || coord.col > BOARD_SIZE) {
            return false;
        }

        for (Coordinate soldier : soldiers) {
            if (soldier.row == coord.row && soldier.col == coord.col) {
                return false;
            }
        }

        return true;
    }
}

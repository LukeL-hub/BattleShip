import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShipPlacer {
    private Random random = new Random();

    private final int[] shipSizes = {5, 4, 3, 3, 2};

    public List<Ship> placeShipRandomly(GameBoard board) {
        List<Ship> ships = new ArrayList<>();
        for (int size : shipSizes) {
            Ship ship = new Ship(size);
            boolean placed = false;
            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                if (horizontal) {
                    if (col + size > 10) continue;
                    boolean valid = true;
                    for (int i = col; i < col + size; i++) {
                        if (board.getCell(row, i).hasShip()) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) continue;
                    for (int i = col; i < col + size; i++) {
                        board.getCell(row, i).setShip(ship);
                    }
                    placed = true;
                } else {
                    if (row + size > 10) continue;
                    boolean valid = true;
                    for (int i = row; i < row + size; i++) {
                        if (board.getCell(i, col).hasShip()) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) continue;
                    for (int i = row; i < row + size; i++) {
                        board.getCell(i, col).setShip(ship);
                    }
                    placed = true;
                }
            }
            ships.add(ship);
        }
        return ships;
    }
}

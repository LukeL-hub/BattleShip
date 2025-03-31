import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private Cell[][] cells = new Cell[10][10];
    private List<Ship> ships = new ArrayList<>();
    private ShipPlacer shipPlacer = new ShipPlacer();

    public GameBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void placeShips() {
        ships = shipPlacer.placeShipRandomly(this);
    }


    public boolean fireAtCell(int x, int y) {
        return cells[x][y].fireUpon();
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public List<Ship> getShips() {
        return ships;
    }
}

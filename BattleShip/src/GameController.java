public class GameController {
    private GameBoard gameBoard;
    private Player player;
    private Scoreboard scoreboard;

    public GameController(GameBoard gameBoard, Player player, Scoreboard scoreboard) {
        this.gameBoard = gameBoard;
        this.player = player;
        this.scoreboard = scoreboard;
    }

    public String processMove(int x, int y) {
        Cell cell = gameBoard.getCell(x, y);
        if (cell.isFiredUpon()) {
            return "Already fired";
        }
        boolean hit = gameBoard.fireAtCell(x, y);
        if (hit) {
            player.recordHit();
            Ship ship = cell.getShip();
            if (ship != null && ship.isSunk()) {
                if (allShipsSunk()) {
                    return "Win";
                }
                return "Sunk";
            }
            return "Hit";
        } else {
            player.recordMiss();
            if (player.getStrikeCount() >= 3) {
                return "Loss";
            }
            return "Miss";
        }
    }

    private boolean allShipsSunk() {
        for (Ship ship : gameBoard.getShips()) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Player getPlayer() {
        return player;
    }
}

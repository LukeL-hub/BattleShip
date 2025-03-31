public class Cell {
    private boolean isOccupied = false;
    private boolean isFiredUpon = false;
    private Ship ship;


    public boolean fireUpon() {
        if (isFiredUpon) {
            return false; // Already shot
        }
        isFiredUpon = true;
        if (isOccupied) {
            ship.takeHit();
            return true; // Hit
        }
        return false; // Miss
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        this.isOccupied = true;
    }

    public boolean isFiredUpon() {
        return isFiredUpon;
    }

    public boolean hasShip() {
        return isOccupied;
    }

    public Ship getShip() {
        return ship;
    }
}

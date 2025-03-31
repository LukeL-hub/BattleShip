public class Player {
    private int totalHits = 0;
    private int totalMisses = 0;
    private int consecutiveMisses = 0;
    private int strikeCount = 0;

    public void recordHit() {
        totalHits++;
        consecutiveMisses = 0; // reset on a hit
    }

    public void recordMiss() {
        totalMisses++;
        consecutiveMisses++;
        if (consecutiveMisses >= 5) {
            strikeCount++;
            consecutiveMisses = 0;
        }
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getTotalMisses() {
        return totalMisses;
    }

    public int getConsecutiveMisses() {
        return consecutiveMisses;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public void reset() {
        totalHits = 0;
        totalMisses = 0;
        consecutiveMisses = 0;
        strikeCount = 0;
    }
}

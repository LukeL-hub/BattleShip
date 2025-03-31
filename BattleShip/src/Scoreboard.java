public class Scoreboard {
    public String getScoreText(Player player) {
        return "Hits: " + player.getTotalHits() +
                " | Misses: " + player.getTotalMisses() +
                " | Consecutive Misses: " + player.getConsecutiveMisses() +
                " | Strike Count: " + player.getStrikeCount();
    }
}

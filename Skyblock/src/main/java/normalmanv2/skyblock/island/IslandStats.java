package normalmanv2.skyblock.island;

import normalmanv2.api.utils.stats.Stats;

public class IslandStats implements Stats {

    private int level;
    private double score;

    public IslandStats(){
        this.level = 1;
        this.score = 0;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void addLevel(int level) {
        this.level += level;
    }

    @Override
    public void removeLevel(int level) {
        this.level -= level;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public void addScore(double score) {
        this.score += score;
    }

    @Override
    public void removeScore(double score) {
        this.score -= score;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public double getScore() {
        return score;
    }
}

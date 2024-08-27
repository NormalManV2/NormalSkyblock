package normalmanv2.api.utils.stats;

public interface Stats {

    void setLevel(int level);
    void addLevel(int level);
    void removeLevel(int level);

    void setScore(double score);
    void addScore(double score);
    void removeScore(double score);

    int getLevel();
    double getScore();

}

package fight;

public class Archmage extends Player {

    private static final double FIGHT = 1.5;
    public Archmage(int rank) {
        super(rank);
        this.mFighting = FIGHT*rank;
    }

    public Archmage(int rank, Skill skill) {
        super(rank, skill);
    }
}

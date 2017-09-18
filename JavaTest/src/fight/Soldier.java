package fight;

public class Soldier extends Player {
    public static final double FIGHT = 1;
    public Soldier(int rank){
        super(rank);
        this.mFighting = FIGHT*rank;
    }
    
    public Soldier(int rank, Skill skill) {
        super(rank, skill);
    }

}

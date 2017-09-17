package fight;

public class Soldier extends Player {
    public static final double FIGHT = 1;
    public static final double STORM = 1.5;
    public static final double BEHEAD = 1.8;
    public static final int DEFAULT = 1;
    public Soldier(int rank){
        super(rank);
    }
    
    public boolean killByStorm(Monster monster){
        this.mFighting *= STORM;
        monster.mFighting *= DEFAULT;
        return super.kill(monster);
    }
    
    public boolean killByBehead(Monster monster){
        this.mFighting *= BEHEAD;
        monster.mFighting *= DEFAULT;
        return super.kill(monster);
    }

}

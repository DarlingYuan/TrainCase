package fight;

public class Archmage extends Player {

    private static final double FIGHT = 1.5;
    public static final double FIRE = 1.2;
    public static final double FREEZE = 1.3;
    public static final double FIRE_MONSTER = 0.9;
    public static final double FREEZE_MONSTER = 0.8;

    public Archmage(int rank) {
        super(rank);
        this.mFighting = FIGHT*rank;
    }

    public boolean killByFire(Monster monster) {
        this.mFighting *= FIRE;
        monster.mFighting *= FIRE_MONSTER;
        return super.kill(monster);
    }

    public boolean killByFrezze(Monster monster) {
        this.mFighting *= FREEZE;
        monster.mFighting *= FREEZE_MONSTER;
        return super.kill(monster);
    }

}

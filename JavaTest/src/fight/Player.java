package fight;

public class Player extends Role {

    public Player(int rank) {
        super(rank);
    }

    public boolean kill(Monster monster) {
        return monster.acceptFight(this);
    }

}
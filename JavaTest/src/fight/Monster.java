package fight;

import java.math.BigDecimal;

public class Monster extends Role {
    public Monster(int rank) {
        super(rank);
    }

    public boolean acceptFight(Player player) {
        return compareFight(this.mFighting, player.mFighting);
    }

    private boolean compareFight(double arg1,double arg2){
        int result = new BigDecimal(arg1).compareTo(new BigDecimal(arg2));
        return result<=0 ? true:false;
    }

}

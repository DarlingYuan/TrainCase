package fight;

public class Player extends Role {
    public Skill mSkill = Skill.DEFAULT;
    
    public Player(int rank) {
        super(rank);
    }
    
    public Player(int rank,Skill skill) {
        this(rank);
        this.mSkill = skill;
    }

    public boolean kill(Monster monster) {
        mSkill.killBySkill(mSkill.getmSkillType(), this, monster);
        return monster.acceptFight(this);
    }
    
}
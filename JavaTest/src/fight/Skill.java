package fight;

public enum Skill {
    DEFAULT("DEFAULT",1,1),
    STORM("STORM",1.5,1),
    BEHEAD("BEHEAD",1.8,1),
    FIRE("FIRE",1.2,0.9),
    FREEZE("FREEZE",1.3,0.8);
    public static final String STORM_TYPE = "STORM";
    public static final String BEHEAD_TYPE = "BEHEAD";
    private String mSkillType;
    private double mPlayerIncrease;
    public String getmSkillType() {
        return mSkillType;
    }
    public double getmPlayerIncrease() {
        return mPlayerIncrease;
    }
    public double getmMonsterReduce() {
        return mMonsterReduce;
    }
    private double mMonsterReduce;
    private Skill(String skillType, double playerIncrease,double monsterReduce){
        this.mSkillType = skillType;
        this.mPlayerIncrease = playerIncrease;
        this.mMonsterReduce = monsterReduce;
    }
    public void killBySkill(String skillType, Player player, Monster monster){
        Skill skill = DEFAULT;
        if(skillType.equals(STORM.getmSkillType())){
            skill = STORM;
        }else if(skillType.equals(BEHEAD.getmSkillType())){
            skill = BEHEAD;
        }else if(skillType.equals(FIRE.getmSkillType())){
            skill = FIRE;
        }else if(skillType.equals(FREEZE.getmSkillType())){
            skill = FREEZE;
        }
        player.mFighting *= skill.getmPlayerIncrease();
        monster.mFighting *= skill.getmMonsterReduce();
    }
}

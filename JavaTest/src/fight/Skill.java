package fight;

public enum Skill {
    STORM("STORM",1.5,1),
    BEHEAD("BEHEAD",1.8,1),
    FIRE("FIRE",1.2,0.9),
    FREEZE("FREEZE",1.3,0.8);
    public static final String STORM_TYPE = "STORM";
    public static final String BEHEAD_TYPE = "BEHEAD";
    private String mSkillType;
    private double mPlayerIncrease;
    private double mMonsterReduce;
    private Skill(String skillType, double playerIncrease,double monsterReduce){
        this.mSkillType = skillType;
        this.mPlayerIncrease = playerIncrease;
        this.mMonsterReduce = monsterReduce;
    }
    public boolean killBySkill(String skillType){
        switch (skillType) {
        case STORM_TYPE:
            
            break;

        default:
            break;
        }
        return true;
    }
}

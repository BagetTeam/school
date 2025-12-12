package assignment1;

public class Lanceforged extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE = 3;
    public static int BASE_ATTACK_DAMAGE;

    private int piercingPower;
    private int actionRange;

    public Lanceforged(Tile position, int piercingPower, int actionRange) {
        super(position, BASE_HEALTH, WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
        this.piercingPower = piercingPower;
        this.actionRange = actionRange;
    }

    @Override
    public int takeAction() {
        Tile currentAttackRange = this.getPosition();
        int numberOfAttacks = 0;
        double totalSkillPoints = 0;

        for (int i = 0; i <= actionRange; i++) {
            if (currentAttackRange == null || currentAttackRange.isCamp()) {
                break;
            }
            if (!currentAttackRange.isCamp() && currentAttackRange.isOnThePath()
                    && currentAttackRange.getMonster() != null) {
                Monster[] currentTroop = currentAttackRange.getMonsters();
                for (int j = 0; j < piercingPower; j++) {
                    if (j < currentAttackRange.getNumOfMonsters()) {
                        double damageDealt = currentTroop[j].takeDamage(BASE_ATTACK_DAMAGE, WEAPON_TYPE);
                        totalSkillPoints += (BASE_ATTACK_DAMAGE / damageDealt) + 1;
                        numberOfAttacks++;
                    } else {
                        break;
                    }
                }
                break;
            }
            currentAttackRange = currentAttackRange.towardTheCamp();
        }
        if (numberOfAttacks > 0) {
            return (int) Math.floor(totalSkillPoints / numberOfAttacks);
        }
        return 0;
    }

}

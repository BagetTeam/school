package assignment1;

public abstract class Warrior extends Fighter {
    private int requiredSkillPoints;

    public static double CASTLE_DMG_REDUCTION;

    public Warrior(Tile position, double health, int weaponType, int attackDamage, int requiredSkillPoints) {
        super(position, health, weaponType, attackDamage);
        this.requiredSkillPoints = requiredSkillPoints;
    }

    public int getTrainingCost() {
        return requiredSkillPoints;
    }

    @Override
    public double takeDamage(double damage, int weaponType) {
        if (this.getPosition() != null && this.getPosition().isCastle()) {
            damage = damage * (1 - CASTLE_DMG_REDUCTION);
        }
        return super.takeDamage(damage, weaponType);
    }

    @Override
    public int takeAction() {
        return 0;
    }
}

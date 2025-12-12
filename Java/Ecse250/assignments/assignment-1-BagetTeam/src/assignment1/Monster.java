package assignment1;

public class Monster extends Fighter {

    private int rageLevel = 0;

    public static int BERSERK_THRESHOLD;

    public Monster(Tile position, double health, int weaponType, int attackDamage) {
        super(position, health, weaponType, attackDamage);
    }

    @Override
    public int takeAction() {
        int repeat = 1;

        if (rageLevel >= BERSERK_THRESHOLD) {
            this.rageLevel = 0;
            repeat = 2;
        }
        for (int i = 0; i < repeat; i++) {
            Tile currentPosition = this.getPosition();
            if (currentPosition == null) { // Ensure currentPosition is valid
                System.out.println("Monster has no position! Skipping action.");
                return 0;
            }
            Warrior currentWarrior = currentPosition.getWarrior();
            if (currentWarrior != null) {
                currentWarrior.takeDamage(getAttackDamage(), getWeaponType());

                currentPosition.removeFighter(this);
                currentPosition.addFighter(this);
            } else {
                System.out.println("Toward the castle");
                if (currentPosition.isCastle()) {
                    System.out.println("REACHED THE CASTLE");
                    return 0;
                }
                currentPosition.removeFighter(this);
                currentPosition = currentPosition.towardTheCastle();
                // if (currentPosition == null) {
                // System.out.println("REACHED THE CASTLE");
                // return 0;
                // }
                currentPosition.addFighter(this);
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        }

        Monster otherMonster = (Monster) object;
        return otherMonster.getAttackDamage() == this.getAttackDamage();
        // if (object.getClass() == Monster.class) {
        // double diff = ((Monster) object).getHealth() - this.getHealth();
        // if (diff < 0) {
        // diff *= -1;
        // }
        // if ((diff <= 0.001) && (((Fighter) object).getPosition() ==
        // this.getPosition())
        // && (((Fighter) object).getAttackDamage() == this.getAttackDamage())) {
        // return true;
        // }
        // }
        // return false;
    }

    @Override
    public double takeDamage(double damage, int weaponType) {
        double difference = 0;
        if (this.getWeaponType() < weaponType) {
            difference = weaponType - this.getWeaponType();
        }
        double damageDealt = super.takeDamage(damage, weaponType);
        if (this.getHealth() > 0) {
            this.rageLevel += difference;
        }
        return damageDealt;
    }

    @Override
    public String toString() {
        if (this.getPosition() == null) {
            return "Monster(" + "position=null" + ",health=" + this.getHealth() + ",weaponType=" + this.getWeaponType()
                    + ",attackDamage=" + this.getAttackDamage() + ")";
        }
        return "Monster("
                + "position=" + "TILE" + ",health=" + this.getHealth() +
                ",weaponType=" + this.getWeaponType() +
                ",attackDamage=" + this.getAttackDamage() + ")";
    }
}

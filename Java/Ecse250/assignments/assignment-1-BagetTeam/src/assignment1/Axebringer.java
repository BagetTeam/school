package assignment1;

public class Axebringer extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE = 2;
    public static int BASE_ATTACK_DAMAGE;

    private boolean hasAxe;

    public Axebringer(Tile position) {
        super(position, BASE_HEALTH, WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
        hasAxe = true;
    }

    @Override
    public int takeAction() {
        Tile currentPosition = this.getPosition();
        double damageDealt = 0;

        if (!hasAxe) {
            hasAxe = true;
            return 0;
        }

        if (currentPosition.getMonster() != null) {
            damageDealt = currentPosition.getMonster().takeDamage(BASE_ATTACK_DAMAGE, WEAPON_TYPE);
        } else {
            currentPosition = currentPosition.towardTheCamp();
            if (currentPosition.getMonster() != null && !(currentPosition.isCamp())) {
                damageDealt = currentPosition.getMonster().takeDamage(BASE_ATTACK_DAMAGE, WEAPON_TYPE);
                hasAxe = false;
            }
        }

        if (damageDealt > 0) {
            return (int) Math.floor(BASE_ATTACK_DAMAGE / damageDealt + 1);
        }
        return 0;

    }
}

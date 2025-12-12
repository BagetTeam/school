package assignment1;

public class Bladesworn extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE = 3;
    public static int BASE_ATTACK_DAMAGE;

    public Bladesworn(Tile position) {
        super(position, BASE_HEALTH, WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
    }

    @Override
    public int takeAction() {
        Tile currentPosition = this.getPosition();

        if (currentPosition.getMonster() != null) {
            double damageDealt = currentPosition.getMonster().takeDamage(BASE_ATTACK_DAMAGE, WEAPON_TYPE);
            return (int) Math.floor(BASE_ATTACK_DAMAGE / damageDealt + 1);
        } else {
            Tile nextPosition = currentPosition.towardTheCamp();
            if (nextPosition != null && !nextPosition.isCamp() && nextPosition.getWarrior() == null) {
                currentPosition.removeFighter(this);
                this.setPosition(nextPosition);
                nextPosition.addFighter(this);
            }
            return 0;
        }
    }
}

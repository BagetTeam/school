package assignment1;

public abstract class Fighter {
    private Tile position;
    private double health;
    private int weaponType;
    private int attackDamage;

    public Fighter(Tile position, double health, int weaponType, int attackDamage) {
        this.health = health;
        this.weaponType = weaponType;
        this.attackDamage = attackDamage;

        if (!position.addFighter(this)) {
            System.out.println("NOT ADDED");
            this.position = null;
            throw new IllegalArgumentException("Cannot add warrior to this position");
        }
        this.position = position;
        System.out.println("ADDED");

    }

    public final Tile getPosition() {
        return position;
    }

    public final double getHealth() {
        return health;
    }

    public final int getWeaponType() {
        return weaponType;

    }

    public final int getAttackDamage() {
        return attackDamage;
    }

    public final void setPosition(Tile tile) {
        position = tile;
    }

    public double takeDamage(double damage, int weaponType) {
        if (this.weaponType > weaponType) {
            damage *= 0.5;
        } else if (this.weaponType < weaponType) {
            damage *= 1.5;
        }
        health -= damage;
        if (health <= 0 && this.getPosition() != null) {
            this.getPosition().removeFighter(this);
        }
        return damage;
    }

    public abstract int takeAction();

    @Override
    public boolean equals(Object object) {
        if (object.getClass() == this.getClass()) {
            double diff = ((Fighter) object).health - this.health;
            if (diff < 0) {
                diff *= -1;
            }
            if ((diff <= 0.001) && (((Fighter) object).position == this.position)) {
                return true;
            }
        }
        return false;
    }
}

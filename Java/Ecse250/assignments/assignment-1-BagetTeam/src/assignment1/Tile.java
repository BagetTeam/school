package assignment1;

public class Tile {
    private boolean isCastle;
    private boolean isCamp;
    private boolean onThePath;
    private Tile towardTheCastle;
    private Tile towardTheCamp;
    private Warrior warrior;
    private MonsterTroop troop;

    public Tile() {
        isCastle = false;
        isCamp = false;
        onThePath = false;
        towardTheCastle = null;
        towardTheCamp = null;
        warrior = null;
        troop = new MonsterTroop();
    }

    public Tile(boolean isCastle, boolean isCamp, boolean onThePath, Tile towardTheCastle, Tile towardTheCamp,
            Warrior warrior, MonsterTroop troop) {
        this.isCastle = isCastle;
        this.isCamp = isCamp;
        this.onThePath = onThePath;
        this.towardTheCastle = towardTheCastle;
        this.towardTheCamp = towardTheCamp;
        this.warrior = warrior;
        if (troop == null) {
            this.troop = new MonsterTroop();
        } else {
            this.troop = troop;
        }
    }

    public boolean isCastle() {
        return isCastle;
    }

    public boolean isCamp() {
        return isCamp;
    }

    public void buildCastle() {
        isCastle = true;
        // onThePath = true;
    }

    public void buildCamp() {
        isCamp = true;
        // onThePath = true;
    }

    public boolean isOnThePath() {
        return onThePath;
    }

    public Tile towardTheCastle() {
        if (!onThePath || isCastle) {
            return null;
        }
        return towardTheCastle;
    }

    public Tile towardTheCamp() {
        if (!onThePath || isCamp) {
            return null;
        }
        return towardTheCamp;
    }

    public void createPath(Tile tileTowardCastle, Tile tileTowardCamp) {
        if ((isCastle && tileTowardCastle != null) || (!isCastle && tileTowardCastle == null) ||
                (isCamp && tileTowardCamp != null) || (!isCamp && tileTowardCamp == null)) {
            throw new IllegalArgumentException("Invalid path configuration");
        }
        onThePath = true;
        this.towardTheCastle = tileTowardCastle;
        this.towardTheCamp = tileTowardCamp;
        /*
         * if (tileTowardCastle != null) {
         * this.towardTheCastle.onThePath = true;
         * }
         * if (tileTowardCamp != null) {
         * this.towardTheCamp.onThePath = true;
         * }
         * if (tileTowardCastle.isOnThePath() || tileTowardCamp.isOnThePath()) {
         * 
         * }
         */
    }

    public int getNumOfMonsters() {
        return troop.sizeOfTroop();
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public Monster getMonster() {
        return troop.getFirstMonster();
    }

    public Monster[] getMonsters() {
        // if (troop == null) {
        // return new Monster[0];
        // }
        return troop.getMonsters();
    }

    public boolean addFighter(Fighter fighter) {
        if (fighter == null) {
            System.out.println("1");
            return false;
        }

        if (fighter instanceof Warrior) {
            if (!isCamp && warrior == null) {
                fighter.setPosition(this);
                warrior = (Warrior) fighter;

                return true;
            }
        }

        else if (fighter instanceof Monster) {
            if (!onThePath) {
                System.out.println("2");
                return false;
            }

            fighter.setPosition(this);
            troop.addMonster((Monster) fighter);

            return true;
        }
        return false;
    }

    public boolean removeFighter(Fighter fighter) {
        if (fighter instanceof Warrior) {
            System.out.println("WARRIOR");
        } else if (fighter instanceof Monster) {
            System.out.println("MONSTER" + ((Monster) fighter).toString());
        }
        if (fighter instanceof Warrior && warrior.equals(fighter)) {
            System.out.println("REMOVING WARRIOR");
            warrior = null;
            fighter.setPosition(null);
            return true;

        } else if (fighter instanceof Monster && troop.removeMonster((Monster) fighter)) {
            fighter.setPosition(null);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tile(" + troop.toString() + ")";
    }
}

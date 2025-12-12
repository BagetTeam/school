package assignment1;

public class MonsterTroop {
    private Monster[] monsters;
    private int numOfMonsters;
    private static final int INITIAL_CAPACITY = 10;

    public MonsterTroop() {
        this.monsters = new Monster[INITIAL_CAPACITY];
        this.numOfMonsters = 0;
    }

    public int sizeOfTroop() {
        return numOfMonsters;
    }

    public Monster[] getMonsters() {
        Monster[] monsters = new Monster[numOfMonsters];
        for (int i = 0; i < numOfMonsters; i++) {
            monsters[i] = this.monsters[i];
        }
        return monsters;
    }

    public Monster getFirstMonster() {
        if (numOfMonsters == 0) {
            return null;
        }
        return monsters[0];
    }

    public void addMonster(Monster monster) {
        if (monster == null) {
            return;
        }

        if (numOfMonsters == monsters.length) {
            Monster[] newMonsters = new Monster[monsters.length + INITIAL_CAPACITY];
            for (int i = 0; i < numOfMonsters; i++) {
                newMonsters[i] = monsters[i];
            }
            monsters = newMonsters;
        }

        monsters[numOfMonsters] = monster;
        numOfMonsters++;
    }

    public boolean removeMonster(Monster monster) {
        if (numOfMonsters == 0 || monster == null) {
            return false;
        }

        for (int i = 0; i < numOfMonsters; i++) {
            if (monsters[i] == monster) {
                for (int j = i; j < numOfMonsters - 1; j++) {
                    monsters[j] = monsters[j + 1];
                }
                numOfMonsters--;
                monsters[numOfMonsters] = null;
                return true;
            }
        }
        return false;
        // resize the array if the number of monsters is less than 1/4 of the array size
        // (for better memory)
        // if (numOfMonsters > 0 && numOfMonsters < monsters.length / 4) {
        // Monster[] newMonsters = new Monster[monsters.length / 2];
        // for (int i = 0; i < numOfMonsters; i++) {
        // newMonsters[i] = monsters[i];
        // }
        // monsters = newMonsters;
        // }

        // return true;
    }

    @Override
    public String toString() {
        if (numOfMonsters == 0) {
            return "MonsterTroop()";
        }

        StringBuilder msg = new StringBuilder("MonsterTroop(");
        for (int i = 0; i < numOfMonsters; i++) {
            msg.append("monster=").append(monsters[i].toString());
            if (i < numOfMonsters - 1) {
                msg.append(", \n");
            }
        }
        msg.append(")");
        return msg.toString();
    }
}
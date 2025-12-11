import java.util.Arrays;

public class OpenAddressing extends SimpleHashSet {
    public int[] Table;
    private int size;

    protected OpenAddressing(int w, int seed, int A) {
        super(w, seed, A);
        this.Table = new int[m];
        for (int i =0; i<m; i++) {
            Table[i] = -1;
        }
    }

    /**Implements the probe function p(k, i)*/
    public int probe(int key, int i) {
        long prod = ((long) A * key) & ((1L << w) - 1); // A*k mod 2^w
        int h = (int) (prod >>> (w - r));                // upper r bits
        return (h + i) & (m - 1);                        // linear probing
    }

    /** Inserts key k into hash set. Returns the number of collisions encountered */
    @Override
    public int insert(int key) {
        if (contains(key)) {
            int c = 0;
            for (int i = 0; ; i++) {   
                int s = probe(key, i);
                c++;
                if (Table[s] == key) return c;
            }
        }

        int collisions = 0;
        for(int i = 0; i < this.m; i++, collisions++) {
            int slot = probe(key, i);
            if (Table[slot] == -1 || Table[slot] == -2) {               
                Table[slot] = key;
                size++;
                return collisions;
            }
        }
        return collisions;
    }

    /** Removes key k from hash table. Returns the number of collisions encountered */
    @Override
    public int remove(int key){   
        int collisions = 0;
        for(int i = 0; i < this.m; i++) {
            int slot = probe(key, i);
            int val = Table[slot];
            if (val == key) {
                Table[slot] = -2;   // mark as deleted
                size--;
                return collisions;
            }
            collisions++;
            if (val == -1) break;   // not found
        }
        return collisions;
    }


    /** Returns whether the key is in the set */
    @Override
    public boolean contains(int key) {
        for(int i = 0; i < this.m; i++) {
            int slot = probe(key, i);
            if (Table[slot] == key) {
                return true;
            }
            if (Table[slot] == -1) return false;
        }
        return false;
    }

    /** Return how many keys are currently in the set */
    @Override
    long size() {
        // TODO
        return this.size;
    }
}
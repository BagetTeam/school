import java.util.*;

public class Chaining extends SimpleHashSet {
    public ArrayList<ArrayList<Integer>>  Table;
    private long size = 0;

    protected Chaining(int w, int seed, int A){
        super(w, seed, A);
        this.Table = new ArrayList<>(m);
        for (int i=0; i<m; i++) {
            Table.add(new ArrayList<>());
        }
    }

    /**Implements the hash function h(k)*/
    public int hash(int key) {
        long Akey = this.A * key % SimpleHashSet.power2(this.w);
        return (int) (Akey >> (this.w-this.r));
    }


    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    @Override
    public int insert(int key){
        // TODO
        int index = this.hash(key);
        ArrayList<Integer> chain = Table.get(index);

        int i = chain.indexOf(key);
        if (i != -1) {
            return i+1;
        } 
        int collisions = chain.size();
        chain.add(key);
        size++;
        
        return collisions;
    }

    /** Removes key k from hash table. Returns the number of collisions encountered */
    @Override
    int remove(int key) {
        int index = this.hash(key);
        ArrayList<Integer> chain = Table.get(index);
        int collisions = 0;
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i) == key) {
                chain.remove(i);
                size--;
                return collisions;
            }
            collisions++;
        }
        return collisions;
    }

    /** Returns whether the key is in the set */
    @Override
    boolean contains(int key) {
        int index = this.hash(key);
        ArrayList<Integer> chain = Table.get(index);
        return chain.contains(key);
    }

    /** Return how many keys are currently in the set */
    @Override
    long size() {
        return this.size;
    }
}
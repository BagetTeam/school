import java.util.*;

public class NaiveDisjointSet<T> {
    HashMap<T, T> parentMap = new HashMap<>();
    private HashMap<T, Integer> rankMap = new HashMap<>();

    void add(T element) {
        if (!parentMap.containsKey(element)) {
            parentMap.put(element, element);
            rankMap.put(element, 0);
        }
    }

    // TODO: Implement path compression
    T find(T a) {
        T node = parentMap.get(a);
        if (node.equals(a)) {
            return node;
        } else {
            T parent = find(node);
            parentMap.put(node, parent);
            return parent;
        }
    }

    // TODO: Implement union by size or union by rank
    void union(T a, T b) {
        T parentA = find(a);
        T parentB = find(b);
        if (parentA.equals(parentB)) {
            return;
        }

        int rankA = rankMap.get(parentA);
        int rankB = rankMap.get(parentB);

        if (rankA > rankB) {
            parentMap.put(parentB, parentA);
        } else if (rankA < rankB) {
            parentMap.put(parentA, parentB);
        } else {
            parentMap.put(parentA, parentB);
            rankMap.put(parentB, rankB + 1);
        } 
    }
}

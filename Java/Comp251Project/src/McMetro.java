import java.util.*;
import java.lang.Math.*;
import java.sql.Connection;

public class McMetro {
    protected Track[] tracks;
    protected HashMap<BuildingID, Building> buildingTable = new HashMap<>();

    private List<String> passengers = new ArrayList<>();
    
    private List<Edge> edges = new ArrayList<>();
    private Trie trie = new Trie();

    private static class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        String word;

        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void add(String word) {
            TrieNode current = root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                    current.children[index].word = word.substring(0, i + 1);
                }
                current = current.children[index];
            }
            current.isEnd = true;
        }

        TrieNode search(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (current.children[index] == null) {
                    return null;
                }
                current = current.children[index];
            }
            return current;
        }

        ArrayList<String> getWords(TrieNode node) {
            ArrayList<String> words = new ArrayList<>();
            if (node.isEnd) {
                words.add(node.word);
            }
            for (TrieNode child : node.children) {
                words.addAll(getWords(child));
            }
            return words;
        }
    }

    private static class Edge implements Comparable<Edge> {
        BuildingID start;
        BuildingID end;
        int capacity;
        int cost;
        TrackID id;
        int goodness;

        Edge(BuildingID start, BuildingID end, int capacity, int cost, TrackID id) {
            this.start = start;
            this.end = end;
            this.capacity = capacity;
            this.cost = cost;
            this.id = id;
            this.goodness = Math.floorDiv(capacity, cost);
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(other.goodness, this.goodness);
            // int capacityCompare = Integer.compare(other.capacity, this.capacity);
            // if (capacityCompare != 0) {
            //     return capacityCompare;
            // }
            // return Integer.compare(this.cost, other.cost);
        }
    }

    // You may initialize anything you need in the constructor
    McMetro(Track[] tracks, Building[] buildings) {
       this.tracks = tracks;

       // Populate buildings table
       for (Building building : buildings) {
           buildingTable.putIfAbsent(building.id(), building);
       }

        for (Track track : tracks) {
            BuildingID s = track.startBuildingId();
            BuildingID e = track.endBuildingId();
            
            Building sBuilding = buildingTable.get(s);
            Building eBuilding = buildingTable.get(e);

            int maxCapacity = Math.min(track.capacity(), Math.min(sBuilding.occupants(), eBuilding.occupants()));
            edges.add(new Edge(s, e, maxCapacity, track.cost(), track.id()));

        }

        Collections.sort(edges);
    }

    // Maximum number of passengers that can be transported from start to end
    int maxPassengers(BuildingID start, BuildingID end) {
        // TODO: your implementation here
        if (!buildingTable.containsKey(start) || !buildingTable.containsKey(end)) {
            return 0;
        }
        
        if (start.equals(end)) {
            return 0;
        }

        


        int max = 0;
        
        return 0;
    }

    // Returns a list of trackIDs that connect to every building maximizing total network capacity taking cost into account
    TrackID[] bestMetroSystem() {
        List<TrackID> trackIDs = new ArrayList<>();

        NaiveDisjointSet<BuildingID> ds = new NaiveDisjointSet<>();
        
        for (BuildingID buildingID : buildingTable.keySet()) {
            ds.add(buildingID);
        }
        
        for (Edge edge : edges) {
            BuildingID start = edge.start;
            BuildingID end = edge.end;

            if (ds.find(start).equals(ds.find(end))) {
                continue;
            }

            trackIDs.add(edge.id);
            ds.union(start, end);
        }

        return trackIDs.toArray(new TrackID[0]);
    }

    // Adds a passenger to the system
    void addPassenger(String name) {
        // TODO: your implementation here
        trie.add(name.toLowerCase());
    }

    // Do not change this
    void addPassengers(String[] names) {
        for (String s : names) {
            addPassenger(s);
        }
    }

    // Returns all passengers in the system whose names start with firstLetters
    ArrayList<String> searchForPassengers(String firstLetters) {
        // TODO: your implementation here
        firstLetters = firstLetters.toLowerCase();

        ArrayList<String> passengers = new ArrayList<>();
        TrieNode prefix = trie.search(firstLetters);
        if (prefix == null) {
            return passengers;
        }

        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(prefix);
        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            if (node.isEnd) {
                passengers.add(capitalize(node.word));
            } else {
                for (TrieNode child : node.children) {
                    if (child != null) {
                        queue.add(child);
                    }
                }
            }
        }
        return passengers;
    }

    // Return how many ticket checkers will be hired
    static int hireTicketCheckers(int[][] schedule) {
        // TODO: your implementation here
        Arrays.sort(schedule, (a, b) -> Integer.compare(a[1], b[1]));
        
        return 0;
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
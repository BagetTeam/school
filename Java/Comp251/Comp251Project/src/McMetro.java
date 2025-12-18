import java.util.*;

public class McMetro {
    protected Track[] tracks;
    protected HashMap<BuildingID, Building> buildingTable = new HashMap<>();
    
    private ArrayList<Edge> edges = new ArrayList<>();
    private Trie trie = new Trie();
    private HashMap<BuildingID, ArrayList<Edge>> graph = new HashMap<>();

    private class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        char letter;

        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }

        TrieNode(char letter) {
            children = new TrieNode[26];
            isEnd = false;
            this.letter = letter;
        }
    }

    private class Trie {
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
                    current.children[index] = new TrieNode(c);
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
    }

    private class Edge implements Comparable<Edge> {
        BuildingID start;
        BuildingID end;
        int capacity;
        int flow;
        TrackID id;
        int goodness;

        Edge(BuildingID start, BuildingID end, int capacity, int cost, TrackID id) {
            this.start = start;
            this.end = end;
            this.capacity = capacity;
            this.flow = 0;
            this.id = id;
            this.goodness = Math.floorDiv(capacity, cost);
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(other.goodness, this.goodness);
        }
    }

    
    McMetro(Track[] tracks, Building[] buildings) {
       this.tracks = tracks != null ? tracks : new Track[0];

        if (buildings != null) {
            for (Building building : buildings) {
                if (building != null) { 
                    buildingTable.putIfAbsent(building.id(), building);
                    graph.putIfAbsent(building.id(), new ArrayList<>());
                }
            }
        }

        if (tracks != null) {
            for (Track track : tracks) {
                if (track == null) continue;

                BuildingID s = track.startBuildingId();
                BuildingID e = track.endBuildingId();

                Building sBuilding = buildingTable.get(s);
                Building eBuilding = buildingTable.get(e);
                
                if (sBuilding == null || eBuilding == null) {
                    continue; 
                }

                int maxCapacity = Math.min(track.capacity(), Math.min(sBuilding.occupants(), eBuilding.occupants()));
                Edge edge = new Edge(s, e, maxCapacity, track.cost(), track.id());
                edges.add(edge);

                graph.get(s).add(edge);
                graph.get(e).add(edge);
            }
        }

        Collections.sort(edges);
    }

    
    int maxPassengers(BuildingID start, BuildingID end) {
        if (!buildingTable.containsKey(start) || !buildingTable.containsKey(end)) {
            return 0;
        }
        
        if (start.equals(end)) {
            return 0;
        }

        int totalFlow = 0;

        HashMap<BuildingID, BuildingID> parentNodes = new HashMap<>();
        HashMap<BuildingID, Edge> parentEdges = new HashMap<>();

        while (passengerBfs(start, end, parentNodes, parentEdges)) {    
            int pathFlow = Integer.MAX_VALUE;
            BuildingID cur = end;
            while (!cur.equals(start)) {
                BuildingID parentNode = parentNodes.get(cur);
                Edge edge = parentEdges.get(cur);
                pathFlow = Math.min(pathFlow, edge.capacity - edge.flow);
                cur = parentNode;
            }

            cur = end;
            while (!cur.equals(start)) {
                Edge edge = parentEdges.get(cur);
                edge.flow += pathFlow;

                cur = parentNodes.get(cur);
            }

            totalFlow += pathFlow;

        }
                
        return totalFlow;
    }

    private boolean passengerBfs(BuildingID start, BuildingID target, HashMap<BuildingID, BuildingID> parentNode, HashMap<BuildingID, Edge> parentEdge) {
        parentNode.clear();
        parentEdge.clear();
        HashSet<BuildingID> visited = new HashSet<>();
        Queue<BuildingID> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            BuildingID current = queue.poll();
            if (current.equals(target)) {
                return true;
            }
            for (Edge edge : graph.get(current)) {
                BuildingID next = edge.end;
                if (!visited.contains(next) && edge.capacity - edge.flow > 0) {
                    visited.add(next);
                    queue.add(next);
                    parentNode.put(next, current);
                    parentEdge.put(next, edge);
                }
            }
        }
        return false;
    }

    
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

    
    void addPassenger(String name) {
        if (name == null || name.isEmpty()) {
            return;
        }
        trie.add(name.toLowerCase());
    }

    
    void addPassengers(String[] names) {
        for (String s : names) {
            addPassenger(s);
        }
    }

    ArrayList<String> searchForPassengers(String firstLetters) {
        firstLetters = firstLetters.toLowerCase();

        ArrayList<String> passengers = new ArrayList<>();
        TrieNode prefix = trie.search(firstLetters);
        if (prefix == null) {
            return passengers;
        }

        searchDfs(prefix, firstLetters, passengers);
        return passengers;
    }

    private void searchDfs(TrieNode node, String word, List<String> passengers) {
        if (node.isEnd) {
            passengers.add(capitalize(word));
        }
        for (TrieNode child : node.children) {
            if (child != null) {
                searchDfs(child, word + child.letter, passengers);
            }
        }
    }


    static int hireTicketCheckers(int[][] schedule) {
        if (schedule == null || schedule.length == 0) return 0;

        Arrays.sort(schedule, (a, b) -> Integer.compare(a[1], b[1]));
        int lastTime = Integer.MIN_VALUE;
        int hired = 0;
        for (int[] time : schedule) {
            if (time[0] >= lastTime) {
                hired++;
                lastTime = time[1];
            }
        }
        return hired;
        
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
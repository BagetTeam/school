import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class IslandMap {
    // A list of all the bridges between islands
    ArrayList<Bridge> bridges;

    IslandMap() {
        this.bridges = new ArrayList<>();
    }

    // Return the highest probability of surviving when choosing a path from startIsland to endIsland
    public double bestChanceOfSurvival(int startIsland, int endIsland) {
        // Build adjacency list with transformed costs
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (Bridge bridge : bridges) {
            double cost = -Math.log(bridge.p);
            addEdge(graph, bridge.a, bridge.b, cost);
            addEdge(graph, bridge.b, bridge.a, cost); // Bidirectional
        }
        
        // Dijkstra's algorithm for shortest path (minimum total cost)
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.cost));
        Map<Integer, Double> minCost = new HashMap<>();
        
        pq.offer(new Node(startIsland, 0.0));
        minCost.put(startIsland, 0.0);
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            // Found destination
            if (current.island == endIsland) {
                return Math.exp(-current.cost);
            }
            
            // Skip outdated entries
            if (current.cost > minCost.get(current.island)) {
                continue;
            }
            
            // Explore neighbors
            for (Edge edge : graph.getOrDefault(current.island, Collections.emptyList())) {
                double newCost = current.cost + edge.cost;
                if (newCost < minCost.getOrDefault(edge.to, Double.POSITIVE_INFINITY)) {
                    minCost.put(edge.to, newCost);
                    pq.offer(new Node(edge.to, newCost));
                }
            }
        }
        
        return 0.0; // No path exists
    }
    
    private void addEdge(Map<Integer, List<Edge>> graph, int from, int to, double cost) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, cost));
    }
    
    private static class Edge {
        int to;
        double cost;
        Edge(int to, double cost) { this.to = to; this.cost = cost; }
    }
    
    private static class Node {
        int island;
        double cost;
        Node(int island, double cost) { this.island = island; this.cost = cost; }
    }
}
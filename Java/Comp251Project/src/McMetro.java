import java.util.*;
import java.lang.Math.*;
import java.sql.Connection;

public class McMetro {
    protected Track[] tracks;
    protected HashMap<BuildingID, Building> buildingTable = new HashMap<>();

    private static class Edge implements Comparable<Edge> {
        BuildingID start;
        BuildingID end;
        int weight;

        Edge(BuildingID start, BuildingID end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(other.weight, this.weight);
        }
    }

    // You may initialize anything you need in the constructor
    McMetro(Track[] tracks, Building[] buildings) {
       this.tracks = tracks;

       // Populate buildings table
       for (Building building : buildings) {
           buildingTable.putIfAbsent(building.id(), building);
           graph.put(building.id(), new ArrayList<>());
       }

       for (Track track : tracks) {
           BuildingID start = track.startBuildingId();
           BuildingID end = track.endBuildingId();

           Building startBuilding = buildingTable.get(start);
           Building endBuilding = buildingTable.get(end);

           int capacityToEnd = Math.min(endBuilding.occupants(), track.capacity());
           int capacityToStart = Math.min(startBuilding.occupants(), track.capacity());

           graph.get(start).add(new Edge(end, capacityToEnd, track.cost()));
           graph.get(end).add(new Edge(end, capacityToStart, track.cost()));
       }
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
        // TODO: your implementation here
        return new TrackID[0];
    }

    // Adds a passenger to the system
    void addPassenger(String name) {
        // TODO: your implementation here
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
        return new ArrayList<>();
    }

    // Return how many ticket checkers will be hired
    static int hireTicketCheckers(int[][] schedule) {
        // TODO: your implementation here
        return 0;
    }
}
package algorithms;

import java.util.*;

/**
 * Dijkstra's algorithm to find shortest learning paths to skill mastery.
 * Computes shortest path from a source course to all other courses.
 *
 * Time Complexity: O((V + E) log V) using PriorityQueue
 * Space Complexity: O(V)
 */
public class Dijkstra {

    private Graph graph;

    /** Result containing distances and paths. */
    public static class Result {
        public Map<String, Integer> distances;
        public Map<String, String> predecessors; // For path reconstruction

        Result(Map<String, Integer> distances, Map<String, String> predecessors) {
            this.distances = distances;
            this.predecessors = predecessors;
        }
    }

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    /**
     * Computes shortest paths from source to all reachable vertices.
     */
    public Result findShortestPaths(String source) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> pred = new HashMap<>();
        Set<String> settled = new HashSet<>();

        // Initialize distances
        for (String vertex : graph.getVertices()) {
            dist.put(vertex, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        // Priority queue ordered by distance
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.distance));
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            String u = pq.poll().vertex;
            if (settled.contains(u)) continue;
            settled.add(u);

            for (Graph.Edge edge : graph.getEdges(u)) {
                String v = edge.dest;
                int weight = edge.weight;
                if (!settled.contains(v) && dist.get(u) != Integer.MAX_VALUE
                        && dist.get(u) + weight < dist.get(v)) {
                    dist.put(v, dist.get(u) + weight);
                    pred.put(v, u);
                    pq.add(new Node(v, dist.get(v)));
                }
            }
        }

        return new Result(dist, pred);
    }

    /** Reconstructs path from source to target using predecessors. */
    public List<String> getPath(Result result, String target) {
        List<String> path = new ArrayList<>();
        if (!result.predecessors.containsKey(target) && !target.equals(
                result.distances.entrySet().stream()
                        .filter(e -> e.getValue() == 0)
                        .findFirst().get().getKey())) {
            return path; // No path
        }

        String current = target;
        while (current != null) {
            path.add(0, current);
            current = result.predecessors.get(current);
        }
        return path;
    }

    /** Internal node for priority queue. */
    private static class Node {
        String vertex;
        int distance;

        Node(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}

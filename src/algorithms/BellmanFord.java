package algorithms;

import java.util.*;

/**
 * Bellman-Ford algorithm for evaluating weighted course dependencies.
 * Handles negative edge weights and detects negative cycles.
 *
 * Time Complexity: O(V * E)
 * Space Complexity: O(V)
 */
public class BellmanFord {

    private Graph graph;

    /** Result containing distances and predecessor info. */
    public static class Result {
        public Map<String, Integer> distances;
        public Map<String, String> predecessors;
        public boolean hasNegativeCycle;

        Result(Map<String, Integer> distances, Map<String, String> predecessors,
               boolean hasNegativeCycle) {
            this.distances = distances;
            this.predecessors = predecessors;
            this.hasNegativeCycle = hasNegativeCycle;
        }
    }

    public BellmanFord(Graph graph) {
        this.graph = graph;
    }

    /**
     * Runs Bellman-Ford algorithm from the given source.
     * Returns shortest distances and detects negative cycles.
     */
    public Result findShortestPaths(String source) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> pred = new HashMap<>();

        // Step 1: Initialize distances
        for (String vertex : graph.getVertices()) {
            dist.put(vertex, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        // Collect all edges
        List<EdgeInfo> edges = new ArrayList<>();
        for (String u : graph.getVertices()) {
            for (Graph.Edge edge : graph.getEdges(u)) {
                edges.add(new EdgeInfo(u, edge.dest, edge.weight));
            }
        }

        int V = graph.getVertexCount();

        // Step 2: Relax all edges V - 1 times
        for (int i = 1; i < V; i++) {
            for (EdgeInfo edge : edges) {
                if (dist.get(edge.src) != Integer.MAX_VALUE &&
                        dist.get(edge.src) + edge.weight < dist.get(edge.dest)) {
                    dist.put(edge.dest, dist.get(edge.src) + edge.weight);
                    pred.put(edge.dest, edge.src);
                }
            }
        }

        // Step 3: Check for negative cycles
        boolean hasNegativeCycle = false;
        for (EdgeInfo edge : edges) {
            if (dist.get(edge.src) != Integer.MAX_VALUE &&
                    dist.get(edge.src) + edge.weight < dist.get(edge.dest)) {
                hasNegativeCycle = true;
                break;
            }
        }

        return new Result(dist, pred, hasNegativeCycle);
    }

    /** Reconstructs path to target using predecessors. */
    public List<String> getPath(Result result, String target) {
        List<String> path = new ArrayList<>();
        String current = target;
        if (result.distances.getOrDefault(target, Integer.MAX_VALUE) == Integer.MAX_VALUE) {
            return path; // No path
        }
        while (current != null) {
            path.add(0, current);
            current = result.predecessors.get(current);
        }
        return path;
    }

    /** Internal helper for edge storage. */
    private static class EdgeInfo {
        String src, dest;
        int weight;
        EdgeInfo(String src, String dest, int weight) {
            this.src = src; this.dest = dest; this.weight = weight;
        }
    }
}

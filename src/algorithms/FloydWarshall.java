package algorithms;

import java.util.*;

/**
 * Floyd-Warshall algorithm for analyzing all learning path combinations.
 * Computes shortest paths between ALL pairs of vertices.
 *
 * Time Complexity: O(V^3)
 * Space Complexity: O(V^2)
 */
public class FloydWarshall {

    private Graph graph;
    private List<String> vertices;

    /** Result containing the distance matrix and next pointers. */
    public static class Result {
        public Map<String, Map<String, Integer>> distances;
        public Map<String, Map<String, String>> next; // For path reconstruction

        Result(Map<String, Map<String, Integer>> distances,
               Map<String, Map<String, String>> next) {
            this.distances = distances;
            this.next = next;
        }
    }

    public FloydWarshall(Graph graph) {
        this.graph = graph;
        this.vertices = new ArrayList<>(graph.getVertices());
    }

    /**
     * Computes all-pairs shortest paths.
     */
    public Result findAllPairsShortestPaths() {
        int n = vertices.size();
        Map<String, Map<String, Integer>> dist = new HashMap<>();
        Map<String, Map<String, String>> next = new HashMap<>();

        // Initialize distance matrix
        for (int i = 0; i < n; i++) {
            dist.put(vertices.get(i), new HashMap<>());
            next.put(vertices.get(i), new HashMap<>());
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist.get(vertices.get(i)).put(vertices.get(j), 0);
                } else {
                    dist.get(vertices.get(i)).put(vertices.get(j), Integer.MAX_VALUE / 2);
                }
                next.get(vertices.get(i)).put(vertices.get(j), null);
            }
        }

        // Fill initial edges
        for (String u : graph.getVertices()) {
            for (Graph.Edge edge : graph.getEdges(u)) {
                dist.get(u).put(edge.dest, edge.weight);
                next.get(u).put(edge.dest, edge.dest);
            }
        }

        // Floyd-Warshall core algorithm
        for (int k = 0; k < n; k++) {
            String kVertex = vertices.get(k);
            for (int i = 0; i < n; i++) {
                String iVertex = vertices.get(i);
                for (int j = 0; j < n; j++) {
                    String jVertex = vertices.get(j);
                    int newDist = dist.get(iVertex).get(kVertex)
                            + dist.get(kVertex).get(jVertex);
                    if (newDist < dist.get(iVertex).get(jVertex)) {
                        dist.get(iVertex).put(jVertex, newDist);
                        next.get(iVertex).put(jVertex,
                                next.get(iVertex).get(kVertex));
                    }
                }
            }
        }

        return new Result(dist, next);
    }

    /** Reconstructs path from source to target. */
    public List<String> getPath(Result result, String source, String target) {
        List<String> path = new ArrayList<>();
        if (!result.next.containsKey(source)
                || result.next.get(source).get(target) == null) {
            if (source.equals(target)) {
                path.add(source);
                return path;
            }
            return path; // No path
        }

        path.add(source);
        String current = source;
        while (!current.equals(target)) {
            current = result.next.get(current).get(target);
            if (current == null) {
                path.clear();
                return path;
            }
            path.add(current);
        }
        return path;
    }

    /** Returns the list of vertex names. */
    public List<String> getVertices() { return vertices; }
}

package algorithms;

import java.util.*;

/**
 * Graph representation of course prerequisites and learning paths.
 * Implements BFS, DFS, Cycle Detection, and Prim's MST algorithm.
 *
 * Time Complexities:
 * - BFS: O(V + E)
 * - DFS: O(V + E)
 * - Cycle Detection: O(V + E)
 * - Prim's MST: O(E log V)
 */
public class Graph {

    private Map<String, List<Edge>> adjList;
    private int vertexCount;

    /** Edge with destination and weight. */
    public static class Edge {
        public String dest;
        public int weight;

        public Edge(String dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public Graph() {
        adjList = new HashMap<>();
        vertexCount = 0;
    }

    /** Adds a vertex (course node) to the graph. */
    public void addVertex(String courseId) {
        if (!adjList.containsKey(courseId)) {
            adjList.put(courseId, new ArrayList<>());
            vertexCount++;
        }
    }

    /** Adds a directed edge from source to destination with given weight. */
    public void addEdge(String src, String dest, int weight) {
        addVertex(src);
        addVertex(dest);
        adjList.get(src).add(new Edge(dest, weight));
    }

    /** Returns adjacency list for a vertex. */
    public List<Edge> getEdges(String vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    /** Returns all vertices. */
    public Set<String> getVertices() {
        return adjList.keySet();
    }

    // ---------- BFS ----------

    /**
     * Breadth-First Search to explore available course paths.
     * Returns the traversal order starting from the given course.
     */
    public List<String> bfs(String start) {
        List<String> result = new ArrayList<>();
        if (!adjList.containsKey(start)) return result;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.dest)) {
                    visited.add(edge.dest);
                    queue.add(edge.dest);
                }
            }
        }
        return result;
    }

    // ---------- DFS ----------

    /**
     * Depth-First Search for traversing prerequisite structures.
     * Returns the traversal order starting from the given course.
     */
    public List<String> dfs(String start) {
        List<String> result = new ArrayList<>();
        if (!adjList.containsKey(start)) return result;

        Set<String> visited = new HashSet<>();
        dfsRec(start, visited, result);
        return result;
    }

    private void dfsRec(String vertex, Set<String> visited, List<String> result) {
        visited.add(vertex);
        result.add(vertex);

        for (Edge edge : adjList.get(vertex)) {
            if (!visited.contains(edge.dest)) {
                dfsRec(edge.dest, visited, result);
            }
        }
    }

    // ---------- Cycle Detection ----------

    /** Detects if there is a cycle in the prerequisite graph. */
    public boolean hasCycle() {
        Set<String> white = new HashSet<>(adjList.keySet()); // Unvisited
        Set<String> gray = new HashSet<>(); // In progress
        Set<String> black = new HashSet<>(); // Done

        for (String vertex : adjList.keySet()) {
            if (white.contains(vertex)) {
                if (hasCycleUtil(vertex, white, gray, black))
                    return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(String vertex, Set<String> white,
                                  Set<String> gray, Set<String> black) {
        white.remove(vertex);
        gray.add(vertex);

        for (Edge edge : adjList.get(vertex)) {
            if (black.contains(edge.dest)) continue;
            if (gray.contains(edge.dest)) return true; // Back edge = cycle
            if (hasCycleUtil(edge.dest, white, gray, black))
                return true;
        }

        gray.remove(vertex);
        black.add(vertex);
        return false;
    }

    // ---------- Prim's MST ----------

    /**
     * Prim's Minimum Spanning Tree algorithm.
     * Returns the edges (as strings) that form the MST.
     * Time complexity: O(E log V) using PriorityQueue.
     */
    public List<String> primMST() {
        List<String> mstEdges = new ArrayList<>();
        if (adjList.isEmpty()) return mstEdges;

        Set<String> visited = new HashSet<>();
        PriorityQueue<MSTEdge> pq = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.weight));

        // Start from first vertex
        String start = adjList.keySet().iterator().next();
        visited.add(start);

        // Add all edges from start
        for (Edge edge : adjList.get(start)) {
            pq.add(new MSTEdge(start, edge.dest, edge.weight));
        }

        while (!pq.isEmpty() && visited.size() < vertexCount) {
            MSTEdge minEdge = pq.poll();
            if (visited.contains(minEdge.dest)) continue;

            visited.add(minEdge.dest);
            mstEdges.add(minEdge.src + " --(" + minEdge.weight + ")--> " + minEdge.dest);

            for (Edge edge : adjList.get(minEdge.dest)) {
                if (!visited.contains(edge.dest)) {
                    pq.add(new MSTEdge(minEdge.dest, edge.dest, edge.weight));
                }
            }
        }

        return mstEdges;
    }

    /** Internal helper for Prim's MST priority queue. */
    private static class MSTEdge {
        String src, dest;
        int weight;
        MSTEdge(String src, String dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // ---------- Topological Sort ----------

    /** Returns topological ordering of courses. Throws if cycle detected. */
    public List<String> topologicalSort() {
        if (hasCycle()) {
            throw new IllegalStateException("Graph has a cycle! Topological sort not possible.");
        }

        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String vertex : adjList.keySet()) {
            if (!visited.contains(vertex)) {
                topoSortUtil(vertex, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void topoSortUtil(String vertex, Set<String> visited, Stack<String> stack) {
        visited.add(vertex);
        for (Edge edge : adjList.get(vertex)) {
            if (!visited.contains(edge.dest)) {
                topoSortUtil(edge.dest, visited, stack);
            }
        }
        stack.push(vertex);
    }

    /** Returns number of vertices. */
    public int getVertexCount() { return vertexCount; }
}

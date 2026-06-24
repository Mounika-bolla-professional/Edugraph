import model.Student;
import model.Course;
import service.*;
import util.SampleData;

import algorithms.*;

import java.util.*;

/**
 * EduGraph – Adaptive Learning & Course Recommendation Platform
 * Main entry point for the console application.
 *
 * This application demonstrates:
 *   - Trees & Balanced Search Structures (BST, AVL)
 *   - Multiway Trees & Range Query Structures (B-Tree)
 *   - Graph Algorithms (BFS, DFS, Cycle Detection, Prim's MST)
 *   - Shortest Path Optimization (Dijkstra, Bellman-Ford, Floyd-Warshall)
 *   - Advanced Sorting & Data Ranking (Merge, Quick, Heap, Counting, Radix Sort)
 *   - Greedy Algorithms & Dynamic Programming (Activity Selection,
 *     Fractional/0/1 Knapsack, LIS)
 */
public class Main {

    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static AssessmentService assessmentService =
            new AssessmentService(studentService, courseService);
    private static RecommendationService recommendationService =
            new RecommendationService(studentService, courseService, assessmentService);

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Preload sample data
        System.out.println("Loading EduGraph Platform...");
        SampleData.preload(studentService, courseService, assessmentService);

        while (true) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> studentManagementMenu();
                case 2 -> courseManagementMenu();
                case 3 -> assessmentManagementMenu();
                case 4 -> treeAlgorithmsMenu();
                case 5 -> graphAlgorithmsMenu();
                case 6 -> shortestPathMenu();
                case 7 -> sortingAlgorithmsMenu();
                case 8 -> dynamicProgrammingMenu();
                case 9 -> recommendationsMenu();
                case 10 -> analyticsDashboard();
                case 11 -> {
                    System.out.println("\nThank you for using EduGraph. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // =====================================================================
    // MAIN MENU
    // =====================================================================

    private static void printMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          EDUGRAPH PLATFORM");
        System.out.println("  Adaptive Learning & Course Recommendation");
        System.out.println("=".repeat(50));
        System.out.println("  1.  Student Management");
        System.out.println("  2.  Course Management");
        System.out.println("  3.  Assessment Management");
        System.out.println("  4.  Tree Algorithms (CO1 - BST, AVL)");
        System.out.println("  5.  Graph Algorithms (CO3 - BFS, DFS, MST)");
        System.out.println("  6.  Shortest Path Algorithms (CO4)");
        System.out.println("  7.  Sorting Algorithms (CO5)");
        System.out.println("  8.  Dynamic Programming & Greedy (CO6)");
        System.out.println("  9.  Recommendations");
        System.out.println("  10. Analytics Dashboard");
        System.out.println("  11. Exit");
        System.out.println("-".repeat(50));
    }

    // =====================================================================
    // 1. STUDENT MANAGEMENT
    // =====================================================================

    private static void studentManagementMenu() {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("  1. Add Student");
            System.out.println("  2. Search Student");
            System.out.println("  3. Update Student");
            System.out.println("  4. Delete Student");
            System.out.println("  5. Display All Students");
            System.out.println("  6. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> studentService.addStudent(scanner);
                case 2 -> studentService.searchStudent(scanner);
                case 3 -> studentService.updateStudent(scanner);
                case 4 -> studentService.deleteStudent(scanner);
                case 5 -> studentService.displayAllStudents();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // =====================================================================
    // 2. COURSE MANAGEMENT
    // =====================================================================

    private static void courseManagementMenu() {
        while (true) {
            System.out.println("\n--- Course Management ---");
            System.out.println("  1. Add Course");
            System.out.println("  2. Search Course");
            System.out.println("  3. Update Course");
            System.out.println("  4. Delete Course");
            System.out.println("  5. Display All Courses");
            System.out.println("  6. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> courseService.addCourse(scanner);
                case 2 -> courseService.searchCourse(scanner);
                case 3 -> courseService.updateCourse(scanner);
                case 4 -> courseService.deleteCourse(scanner);
                case 5 -> courseService.displayAllCourses();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // =====================================================================
    // 3. ASSESSMENT MANAGEMENT
    // =====================================================================

    private static void assessmentManagementMenu() {
        while (true) {
            System.out.println("\n--- Assessment Management ---");
            System.out.println("  1. Add Marks");
            System.out.println("  2. Generate Grade");
            System.out.println("  3. GPA Calculation");
            System.out.println("  4. Student Result Report");
            System.out.println("  5. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> assessmentService.addMarks(scanner);
                case 2 -> assessmentService.generateGrade(scanner);
                case 3 -> assessmentService.calculateGPA(scanner);
                case 4 -> assessmentService.studentResultReport(scanner);
                case 5 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // =====================================================================
    // 4. TREE ALGORITHMS (CO1 - BST & AVL)
    // =====================================================================

    private static void treeAlgorithmsMenu() {
        while (true) {
            System.out.println("\n--- Tree Algorithms (CO1) ---");
            System.out.println("  1. BST - Display Courses (Inorder Traversal)");
            System.out.println("  2. BST - Search Course by ID");
            System.out.println("  3. BST - Delete Course by ID");
            System.out.println("  4. AVL Tree - Display Students (Inorder)");
            System.out.println("  5. AVL Tree - Search Student by ID");
            System.out.println("  6. AVL Tree - Delete Student by ID");
            System.out.println("  7. B-Tree - Student Indexing (Display)");
            System.out.println("  8. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- BST: Courses Sorted by ID ---");
                    System.out.println("Time Complexity: O(n) inorder traversal");
                    courseService.displayAllCourses();
                }
                case 2 -> {
                    System.out.print("Enter Course ID: ");
                    String id = scanner.nextLine().trim();
                    Course c = courseService.getCourseById(id);
                    if (c == null) System.out.println("Course not found!");
                    else {
                        System.out.println(Course.getSeparator());
                        System.out.println(Course.getHeader());
                        System.out.println(Course.getSeparator());
                        System.out.println(c);
                        System.out.println(Course.getSeparator());
                    }
                }
                case 3 -> {
                    System.out.print("Enter Course ID to delete: ");
                    String id = scanner.nextLine().trim();
                    courseService.getCourseBST().delete(id);
                    System.out.println("Course deleted (if existed).");
                }
                case 4 -> {
                    System.out.println("\n--- AVL Tree: Students Sorted by ID ---");
                    System.out.println("Time Complexity: O(n) inorder traversal");
                    studentService.displayAllStudents();
                }
                case 5 -> {
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine().trim();
                    Student s = studentService.getStudentById(id);
                    if (s == null) System.out.println("Student not found!");
                    else {
                        System.out.println(Student.getSeparator());
                        System.out.println(Student.getHeader());
                        System.out.println(Student.getSeparator());
                        System.out.println(s);
                        System.out.println(Student.getSeparator());
                    }
                }
                case 6 -> {
                    System.out.print("Enter Student ID to delete: ");
                    String id = scanner.nextLine().trim();
                    studentService.getStudentTree().delete(id);
                    System.out.println("Student deleted (if existed).");
                }
                case 7 -> {
                    System.out.println("\n--- B-Tree: Student Indexing ---");
                    System.out.println("Time Complexity: O(t * log_t(n)) for operations");
                    System.out.println("B-Tree with degree t=4 for student indexing.");
                    BTree bTree = new BTree(4);
                    for (Student s : studentService.getAllStudents()) {
                        bTree.insert(s);
                    }
                    List<Student> indexed = bTree.getAllStudents();
                    System.out.println("Students retrieved via B-Tree traversal:");
                    System.out.println(Student.getSeparator());
                    System.out.println(Student.getHeader());
                    System.out.println(Student.getSeparator());
                    for (Student s : indexed) {
                        System.out.println(s);
                    }
                    System.out.println(Student.getSeparator());
                    System.out.println("Total indexed: " + indexed.size());
                }
                case 8 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // =====================================================================
    // 5. GRAPH ALGORITHMS (CO3)
    // =====================================================================

    private static void graphAlgorithmsMenu() {
        while (true) {
            System.out.println("\n--- Graph Algorithms (CO3) ---");
            System.out.println("  1. Build Prerequisite Graph");
            System.out.println("  2. BFS - Explore Course Paths");
            System.out.println("  3. DFS - Traverse Prerequisites");
            System.out.println("  4. Cycle Detection in Prerequisites");
            System.out.println("  5. Prim's MST - Optimal Curriculum");
            System.out.println("  6. Topological Sort - Course Order");
            System.out.println("  7. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> buildAndDisplayGraph();
                case 2 -> bfsDemo();
                case 3 -> dfsDemo();
                case 4 -> cycleDetectionDemo();
                case 5 -> primMstDemo();
                case 6 -> topologicalSortDemo();
                case 7 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    /** Builds a graph from course prerequisites. */
    private static Graph buildPrerequisiteGraph() {
        Graph graph = new Graph();
        for (Course c : courseService.getAllCourses()) {
            graph.addVertex(c.getCourseId());
            for (String prereq : c.getPrerequisites()) {
                // Edge from prerequisite to course (need prereq first)
                graph.addEdge(prereq, c.getCourseId(), c.getDifficulty());
            }
        }
        return graph;
    }

    private static void buildAndDisplayGraph() {
        System.out.println("\n--- Course Prerequisite Graph ---");
        Graph graph = buildPrerequisiteGraph();

        System.out.println("Vertices (Courses): " + graph.getVertexCount());
        System.out.println("\nEdges (Prerequisite -> Course [difficulty weight]):");
        for (String v : graph.getVertices()) {
            for (Graph.Edge e : graph.getEdges(v)) {
                System.out.printf("  %s -> %s  (weight: %d)%n", v, e.dest, e.weight);
            }
        }
    }

    private static void bfsDemo() {
        System.out.println("\n--- BFS: Explore Available Course Paths ---");
        System.out.println("Time Complexity: O(V + E)");
        System.out.print("Enter starting course ID (e.g., C001): ");
        String start = scanner.nextLine().trim();

        Graph graph = buildPrerequisiteGraph();
        List<String> bfsOrder = graph.bfs(start);

        if (bfsOrder.isEmpty()) {
            System.out.println("Course not found in graph!");
            return;
        }

        System.out.println("BFS Traversal from " + start + ":");
        for (int i = 0; i < bfsOrder.size(); i++) {
            String cid = bfsOrder.get(i);
            Course c = courseService.getCourseById(cid);
            String name = (c != null) ? c.getCourseName() : cid;
            System.out.printf("  %2d. %-12s (%s)%n", i + 1, cid, name);
        }
    }

    private static void dfsDemo() {
        System.out.println("\n--- DFS: Traverse Prerequisite Structures ---");
        System.out.println("Time Complexity: O(V + E)");
        System.out.print("Enter starting course ID (e.g., C001): ");
        String start = scanner.nextLine().trim();

        Graph graph = buildPrerequisiteGraph();
        List<String> dfsOrder = graph.dfs(start);

        if (dfsOrder.isEmpty()) {
            System.out.println("Course not found in graph!");
            return;
        }

        System.out.println("DFS Traversal from " + start + ":");
        for (int i = 0; i < dfsOrder.size(); i++) {
            String cid = dfsOrder.get(i);
            Course c = courseService.getCourseById(cid);
            String name = (c != null) ? c.getCourseName() : cid;
            System.out.printf("  %2d. %-12s (%s)%n", i + 1, cid, name);
        }
    }

    private static void cycleDetectionDemo() {
        System.out.println("\n--- Cycle Detection in Prerequisite Graph ---");
        System.out.println("Time Complexity: O(V + E)");

        Graph graph = buildPrerequisiteGraph();
        boolean hasCycle = graph.hasCycle();

        System.out.println("Prerequisite graph has cycle: " + hasCycle);
        if (hasCycle) {
            System.out.println("WARNING: Circular prerequisites detected! Invalid course structure.");
        } else {
            System.out.println("No cycles found. Prerequisite structure is valid.");
        }
    }

    private static void primMstDemo() {
        System.out.println("\n--- Prim's MST: Optimal Curriculum Structure ---");
        System.out.println("Time Complexity: O(E log V)");

        Graph graph = buildPrerequisiteGraph();
        List<String> mstEdges = graph.primMST();

        System.out.println("Minimum Spanning Tree edges:");
        if (mstEdges.isEmpty()) {
            System.out.println("  (Graph too small or empty)");
        } else {
            for (String edge : mstEdges) {
                System.out.println("  " + edge);
            }
        }
    }

    private static void topologicalSortDemo() {
        System.out.println("\n--- Topological Sort: Correct Course Order ---");
        System.out.println("Time Complexity: O(V + E)");

        Graph graph = buildPrerequisiteGraph();
        try {
            List<String> topoOrder = graph.topologicalSort();
            System.out.println("Recommended course completion order:");
            for (int i = 0; i < topoOrder.size(); i++) {
                String cid = topoOrder.get(i);
                Course c = courseService.getCourseById(cid);
                String name = (c != null) ? c.getCourseName() : cid;
                System.out.printf("  %2d. %-12s (%s)%n", i + 1, cid, name);
            }
        } catch (IllegalStateException e) {
            System.out.println("Cannot sort: " + e.getMessage());
        }
    }

    // =====================================================================
    // 6. SHORTEST PATH ALGORITHMS (CO4)
    // =====================================================================

    private static void shortestPathMenu() {
        while (true) {
            System.out.println("\n--- Shortest Path Algorithms (CO4) ---");
            System.out.println("  1. Dijkstra - Shortest Learning Path");
            System.out.println("  2. Bellman-Ford - Weighted Dependency Analysis");
            System.out.println("  3. Floyd-Warshall - All-Pairs Shortest Path");
            System.out.println("  4. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> dijkstraDemo();
                case 2 -> bellmanFordDemo();
                case 3 -> floydWarshallDemo();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void dijkstraDemo() {
        System.out.println("\n--- Dijkstra: Shortest Learning Path ---");
        System.out.println("Time Complexity: O((V+E) log V)");
        System.out.print("Enter source course ID: ");
        String src = scanner.nextLine().trim();
        System.out.print("Enter destination course ID: ");
        String dest = scanner.nextLine().trim();

        Graph graph = buildPrerequisiteGraph();
        Dijkstra dijkstra = new Dijkstra(graph);
        Dijkstra.Result result = dijkstra.findShortestPaths(src);

        Integer dist = result.distances.get(dest);
        if (dist == null || dist == Integer.MAX_VALUE) {
            System.out.println("No path from " + src + " to " + dest);
        } else {
            List<String> path = dijkstra.getPath(result, dest);
            System.out.println("Shortest path distance: " + dist);
            System.out.print("Path: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) System.out.print(" -> ");
            }
            System.out.println();
        }
    }

    private static void bellmanFordDemo() {
        System.out.println("\n--- Bellman-Ford: Weighted Dependency Analysis ---");
        System.out.println("Time Complexity: O(V * E)");
        System.out.print("Enter source course ID: ");
        String src = scanner.nextLine().trim();
        System.out.print("Enter destination course ID: ");
        String dest = scanner.nextLine().trim();

        Graph graph = buildPrerequisiteGraph();
        BellmanFord bf = new BellmanFord(graph);
        BellmanFord.Result result = bf.findShortestPaths(src);

        if (result.hasNegativeCycle) {
            System.out.println("WARNING: Negative cycle detected in the graph!");
        }

        Integer dist = result.distances.get(dest);
        if (dist == null || dist == Integer.MAX_VALUE) {
            System.out.println("No path from " + src + " to " + dest);
        } else {
            List<String> path = bf.getPath(result, dest);
            System.out.println("Shortest path distance: " + dist);
            System.out.print("Path: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) System.out.print(" -> ");
            }
            System.out.println();
        }
    }

    private static void floydWarshallDemo() {
        System.out.println("\n--- Floyd-Warshall: All-Pairs Shortest Path ---");
        System.out.println("Time Complexity: O(V^3)");

        Graph graph = buildPrerequisiteGraph();
        FloydWarshall fw = new FloydWarshall(graph);
        FloydWarshall.Result result = fw.findAllPairsShortestPaths();

        System.out.println("\nDistance Matrix (showing all pairs):");
        List<String> verts = fw.getVertices();
        System.out.print("        ");
        for (String v : verts) System.out.printf("%-8s", v);
        System.out.println();

        for (String u : verts) {
            System.out.printf("%-8s", u);
            for (String v : verts) {
                int d = result.distances.get(u).get(v);
                if (d >= Integer.MAX_VALUE / 2)
                    System.out.printf("%-8s", "INF");
                else
                    System.out.printf("%-8d", d);
            }
            System.out.println();
        }

        System.out.println("\nSample paths:");
        if (verts.size() >= 2) {
            List<String> path = fw.getPath(result, verts.get(0), verts.get(verts.size() - 1));
            if (!path.isEmpty()) {
                System.out.print("Path from " + verts.get(0) + " to "
                        + verts.get(verts.size() - 1) + ": ");
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if (i < path.size() - 1) System.out.print(" -> ");
                }
                System.out.println();
            }
        }
    }

    // =====================================================================
    // 7. SORTING ALGORITHMS (CO5)
    // =====================================================================

    private static void sortingAlgorithmsMenu() {
        while (true) {
            System.out.println("\n--- Sorting Algorithms (CO5) ---");
            System.out.println("  1. Merge Sort - Students by GPA");
            System.out.println("  2. Quick Sort - Courses by Popularity");
            System.out.println("  3. Heap Sort - Top Performers");
            System.out.println("  4. Counting Sort - Exam IDs");
            System.out.println("  5. Radix Sort - Roll Numbers");
            System.out.println("  6. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> {
                    MergeSort mergeSort = new MergeSort();
                    mergeSort.demonstrateSort(studentService.getAllStudents());
                }
                case 2 -> {
                    QuickSort quickSort = new QuickSort();
                    quickSort.demonstrateSort(courseService.getAllCourses());
                }
                case 3 -> {
                    HeapSort heapSort = new HeapSort();
                    heapSort.demonstrateSort(studentService.getAllStudents(), 5);
                }
                case 4 -> {
                    CountingSort countingSort = new CountingSort();
                    countingSort.demonstrateSort(assessmentService.getAllAssessments());
                }
                case 5 -> {
                    RadixSort radixSort = new RadixSort();
                    radixSort.demonstrateSort(studentService.getAllStudents());
                }
                case 6 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // =====================================================================
    // 8. DYNAMIC PROGRAMMING & GREEDY (CO6)
    // =====================================================================

    private static void dynamicProgrammingMenu() {
        while (true) {
            System.out.println("\n--- Dynamic Programming & Greedy (CO6) ---");
            System.out.println("  1. Activity Selection - Study Schedule");
            System.out.println("  2. Fractional Knapsack - Time Allocation");
            System.out.println("  3. 0/1 Knapsack - Course Selection (Credit Limit)");
            System.out.println("  4. LIS - Performance Trend Analysis");
            System.out.println("  5. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = getIntInput("");
            switch (choice) {
                case 1 -> new ActivitySelection().demonstrate();
                case 2 -> new FractionalKnapsack().demonstrate();
                case 3 -> new Knapsack01().demonstrate();
                case 4 -> new LIS().demonstrate();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // =====================================================================
    // 9. RECOMMENDATIONS
    // =====================================================================

    private static void recommendationsMenu() {
        recommendationService.displayRecommendations(scanner);
    }

    // =====================================================================
    // 10. ANALYTICS DASHBOARD
    // =====================================================================

    private static void analyticsDashboard() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          ANALYTICS DASHBOARD");
        System.out.println("=".repeat(60));

        // Top 5 Students by GPA
        System.out.println("\n--- Top 5 Students (Highest GPA) ---");
        List<Student> allStudents = studentService.getAllStudents();
        allStudents.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        System.out.printf("%-3s %-12s %-20s %-6s%n", "#", "ID", "Name", "GPA");
        System.out.println("-".repeat(45));
        for (int i = 0; i < Math.min(5, allStudents.size()); i++) {
            Student s = allStudents.get(i);
            System.out.printf("%-3d %-12s %-20s %-6.2f%n",
                    i + 1, s.getStudentId(), s.getName(), s.getGpa());
        }

        // Lowest Performers
        System.out.println("\n--- Lowest 3 Performers (Lowest GPA) ---");
        System.out.printf("%-3s %-12s %-20s %-6s%n", "#", "ID", "Name", "GPA");
        System.out.println("-".repeat(45));
        int n = allStudents.size();
        for (int i = n - 1; i >= Math.max(0, n - 3); i--) {
            Student s = allStudents.get(i);
            System.out.printf("%-3d %-12s %-20s %-6.2f%n",
                    n - i, s.getStudentId(), s.getName(), s.getGpa());
        }

        // Course Popularity Ranking
        System.out.println("\n--- Course Popularity Ranking ---");
        List<Course> allCourses = courseService.getAllCourses();
        allCourses.sort((a, b) -> Integer.compare(b.getPopularity(), a.getPopularity()));
        System.out.printf("%-3s %-10s %-25s %-10s%n", "#", "ID", "Course", "Popularity");
        System.out.println("-".repeat(50));
        for (int i = 0; i < allCourses.size(); i++) {
            Course c = allCourses.get(i);
            System.out.printf("%-3d %-10s %-25s %-10d%n",
                    i + 1, c.getCourseId(),
                    c.getCourseName().length() > 24
                            ? c.getCourseName().substring(0, 24)
                            : c.getCourseName(),
                    c.getPopularity());
        }

        // Average GPA
        double avgGpa = allStudents.stream()
                .mapToDouble(Student::getGpa).average().orElse(0);
        System.out.printf("\n--- Average GPA: %.2f / 4.00 ---%n", avgGpa);

        // Assessment Statistics
        List<model.Assessment> allAssessments = assessmentService.getAllAssessments();
        System.out.println("\n--- Assessment Statistics ---");
        if (!allAssessments.isEmpty()) {
            double avgMarks = allAssessments.stream()
                    .mapToDouble(model.Assessment::getMarks).average().orElse(0);
            double maxMarks = allAssessments.stream()
                    .mapToDouble(model.Assessment::getMarks).max().orElse(0);
            double minMarks = allAssessments.stream()
                    .mapToDouble(model.Assessment::getMarks).min().orElse(0);
            long passed = allAssessments.stream()
                    .filter(a -> a.getMarks() >= 40).count();

            System.out.printf("  Total Assessments:  %d%n", allAssessments.size());
            System.out.printf("  Average Marks:      %.2f%n", avgMarks);
            System.out.printf("  Highest Marks:      %.2f%n", maxMarks);
            System.out.printf("  Lowest Marks:       %.2f%n", minMarks);
            System.out.printf("  Passed (>=40):      %d/%d (%.1f%%)%n",
                    passed, allAssessments.size(),
                    (double) passed / allAssessments.size() * 100);
        }

        System.out.println("=".repeat(60));
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    // =====================================================================
    // UTILITY
    // =====================================================================

    private static int getIntInput(String prompt) {
        if (!prompt.isEmpty()) System.out.print(prompt);
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

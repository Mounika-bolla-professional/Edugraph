package algorithms;

import java.util.*;

/**
 * 0/1 Knapsack for selecting courses within credit limits.
 * Dynamic programming solution - each course can be taken at most once.
 *
 * Time Complexity: O(n * W) where n = number of items, W = capacity
 * Space Complexity: O(n * W) for DP table
 */
public class Knapsack01 {

    /** Represents a course that can be selected. */
    public static class CourseOption {
        String courseName;
        int credit;  // Weight/credit hours
        int value;   // Learning value / benefit

        public CourseOption(String courseName, int credit, int value) {
            this.courseName = courseName;
            this.credit = credit;
            this.value = value;
        }
    }

    /** Result of 0/1 knapsack selection. */
    public static class SelectionResult {
        public int totalValue;
        public int totalCredits;
        public List<CourseOption> selectedCourses;

        SelectionResult(int totalValue, int totalCredits,
                        List<CourseOption> selectedCourses) {
            this.totalValue = totalValue;
            this.totalCredits = totalCredits;
            this.selectedCourses = selectedCourses;
        }
    }

    /**
     * Selects courses to maximize learning value within credit limit.
     */
    public SelectionResult selectCourses(List<CourseOption> courses, int maxCredits) {
        int n = courses.size();
        int[][] dp = new int[n + 1][maxCredits + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {
            CourseOption course = courses.get(i - 1);
            for (int w = 1; w <= maxCredits; w++) {
                if (course.credit <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w],
                            dp[i - 1][w - course.credit] + course.value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected courses
        List<CourseOption> selected = new ArrayList<>();
        int w = maxCredits;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                CourseOption course = courses.get(i - 1);
                selected.add(course);
                w -= course.credit;
            }
        }

        int totalCredits = selected.stream().mapToInt(c -> c.credit).sum();
        return new SelectionResult(dp[n][maxCredits], totalCredits, selected);
    }

    /**
     * Demonstrates the 0/1 knapsack algorithm.
     */
    public void demonstrate() {
        System.out.println("\n=== 0/1 KNAPSACK: Course Selection Under Credit Limits ===");
        System.out.println("Time Complexity: O(n * W)");
        System.out.println("Problem: Select courses within 15 credits to maximize learning value\n");

        List<CourseOption> courses = new ArrayList<>();
        courses.add(new CourseOption("Data Structures", 4, 90));
        courses.add(new CourseOption("Algorithms", 4, 85));
        courses.add(new CourseOption("Machine Learning", 3, 95));
        courses.add(new CourseOption("Web Development", 3, 60));
        courses.add(new CourseOption("Database Systems", 3, 70));
        courses.add(new CourseOption("Computer Networks", 3, 50));
        courses.add(new CourseOption("Software Engineering", 3, 65));
        courses.add(new CourseOption("Operating Systems", 4, 75));

        int maxCredits = 15;

        System.out.println("Available Courses:");
        System.out.println("  " + "-".repeat(55));
        System.out.printf("  %-25s %-8s %-8s%n", "Course", "Credits", "Value");
        System.out.println("  " + "-".repeat(55));
        for (CourseOption c : courses) {
            System.out.printf("  %-25s %-8d %-8d%n",
                    c.courseName, c.credit, c.value);
        }

        System.out.printf("\nMaximum Credits Allowed: %d\n", maxCredits);
        SelectionResult result = selectCourses(courses, maxCredits);

        System.out.println("\nSelected Courses (Optimal Set):");
        System.out.println("  " + "-".repeat(45));
        System.out.printf("  %-25s %-8s %-8s%n", "Course", "Credits", "Value");
        System.out.println("  " + "-".repeat(45));
        for (CourseOption c : result.selectedCourses) {
            System.out.printf("  %-25s %-8d %-8d%n",
                    c.courseName, c.credit, c.value);
        }
        System.out.println("  " + "-".repeat(45));
        System.out.printf("  Total: %28s %-8d %-8d%n",
                "", result.totalCredits, result.totalValue);
    }
}

package algorithms;

import java.util.*;

/**
 * Fractional Knapsack for allocating limited learning time across courses.
 * Greedy algorithm that allows taking fractional amounts of items.
 *
 * Time Complexity: O(n log n) for sorting
 * Space Complexity: O(n)
 */
public class FractionalKnapsack {

    /** Represents a learning resource with value and time cost. */
    public static class LearningResource {
        String courseName;
        double value;     // Learning value/benefit
        double timeCost;  // Time required in hours
        double ratio;     // Value per unit time

        public LearningResource(String courseName, double value, double timeCost) {
            this.courseName = courseName;
            this.value = value;
            this.timeCost = timeCost;
            this.ratio = value / timeCost;
        }
    }

    /** Result of fractional knapsack allocation. */
    public static class AllocationResult {
        public double totalValue;
        public List<Allocation> allocations;

        AllocationResult(double totalValue, List<Allocation> allocations) {
            this.totalValue = totalValue;
            this.allocations = allocations;
        }
    }

    public static class Allocation {
        String courseName;
        double timeAllocated;
        double valueGained;
        double fraction;

        Allocation(String courseName, double timeAllocated,
                   double valueGained, double fraction) {
            this.courseName = courseName;
            this.timeAllocated = timeAllocated;
            this.valueGained = valueGained;
            this.fraction = fraction;
        }
    }

    /**
     * Allocates learning time across courses to maximize total learning value.
     *
     * @param resources List of learning resources
     * @param totalTime Total available study time in hours
     * @return Allocation result with details
     */
    public AllocationResult allocateTime(List<LearningResource> resources, double totalTime) {
        // Sort by value/time ratio descending
        List<LearningResource> sorted = new ArrayList<>(resources);
        sorted.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        List<Allocation> allocations = new ArrayList<>();
        double remainingTime = totalTime;
        double totalValue = 0;

        for (LearningResource r : sorted) {
            if (remainingTime <= 0) break;

            if (r.timeCost <= remainingTime) {
                // Take whole item
                allocations.add(new Allocation(r.courseName, r.timeCost, r.value, 1.0));
                totalValue += r.value;
                remainingTime -= r.timeCost;
            } else {
                // Take fraction
                double fraction = remainingTime / r.timeCost;
                double valueGained = r.value * fraction;
                allocations.add(new Allocation(r.courseName, remainingTime, valueGained, fraction));
                totalValue += valueGained;
                remainingTime = 0;
            }
        }

        return new AllocationResult(totalValue, allocations);
    }

    /**
     * Demonstrates the fractional knapsack algorithm.
     */
    public void demonstrate() {
        System.out.println("\n=== FRACTIONAL KNAPSACK: Learning Time Allocation ===");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("Problem: Allocate 20 hours across courses to maximize learning value\n");

        List<LearningResource> resources = new ArrayList<>();
        resources.add(new LearningResource("Data Structures", 90, 6));
        resources.add(new LearningResource("Algorithms", 85, 5));
        resources.add(new LearningResource("Machine Learning", 95, 8));
        resources.add(new LearningResource("Web Dev", 60, 4));
        resources.add(new LearningResource("Database", 70, 5));
        resources.add(new LearningResource("Networking", 50, 3));

        double totalTime = 20;

        System.out.println("Available Learning Resources:");
        System.out.println("  " + "-".repeat(70));
        System.out.printf("  %-25s %-8s %-8s %-10s%n", "Course", "Value", "Hours", "Value/Hr");
        System.out.println("  " + "-".repeat(70));
        for (LearningResource r : resources) {
            System.out.printf("  %-25s %-8.0f %-8.1f %-10.2f%n",
                    r.courseName, r.value, r.timeCost, r.ratio);
        }

        System.out.printf("\nTotal Available Time: %.0f hours\n", totalTime);
        AllocationResult result = allocateTime(resources, totalTime);

        System.out.println("\nOptimal Allocation:");
        System.out.println("  " + "-".repeat(65));
        System.out.printf("  %-25s %-12s %-12s %-10s%n",
                "Course", "Time Used", "Value Gain", "Fraction");
        System.out.println("  " + "-".repeat(65));
        for (Allocation a : result.allocations) {
            System.out.printf("  %-25s %-8.1f hrs  %-10.2f  %-6.2f%%%n",
                    a.courseName, a.timeAllocated, a.valueGained, a.fraction * 100);
        }
        System.out.println("  " + "-".repeat(65));
        System.out.printf("  Total Learning Value Achieved: %.2f%n", result.totalValue);
    }
}

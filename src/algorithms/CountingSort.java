package algorithms;

import model.Assessment;
import java.util.List;

/**
 * Counting Sort for sorting exam/assessment IDs.
 * Non-comparison based sort that works well for integer keys in a known range.
 *
 * Time Complexity: O(n + k) where k is the range
 * Space Complexity: O(k)
 */
public class CountingSort {

    /**
     * Sorts assessment IDs (extracted integer portion) in ascending order.
     * Returns a new sorted list without modifying the original.
     */
    public List<Assessment> sortByExamId(List<Assessment> assessments) {
        if (assessments == null || assessments.size() <= 1)
            return assessments;

        // Find the maximum numeric ID value
        int max = 0;
        for (Assessment a : assessments) {
            int id = extractNumericId(a.getAssessmentId());
            if (id > max) max = id;
        }

        // Counting sort
        int[] count = new int[max + 1];

        for (Assessment a : assessments) {
            count[extractNumericId(a.getAssessmentId())]++;
        }

        // Cumulative count for stable sort
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        @SuppressWarnings("unchecked")
        Assessment[] output = new Assessment[assessments.size()];
        for (int i = assessments.size() - 1; i >= 0; i--) {
            int id = extractNumericId(assessments.get(i).getAssessmentId());
            output[count[id] - 1] = assessments.get(i);
            count[id]--;
        }

        return java.util.Arrays.asList(output);
    }

    /** Extracts numeric part from assessment ID like "A001" -> 1. */
    private int extractNumericId(String assessmentId) {
        try {
            return Integer.parseInt(assessmentId.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Demonstrates sorting with before/after display.
     */
    public void demonstrateSort(List<Assessment> assessments) {
        System.out.println("\n=== COUNTING SORT: Assessments by Exam ID ===");
        System.out.println("Time Complexity: O(n + k)");
        System.out.println("\n--- Before Sorting ---");
        for (Assessment a : assessments) {
            System.out.printf("  %-14s %-12s %-10s Marks: %.2f%n",
                    a.getAssessmentId(), a.getStudentId(),
                    a.getCourseId(), a.getMarks());
        }

        List<Assessment> sorted = sortByExamId(assessments);

        System.out.println("\n--- After Sorting (by Assessment ID) ---");
        for (Assessment a : sorted) {
            System.out.printf("  %-14s %-12s %-10s Marks: %.2f%n",
                    a.getAssessmentId(), a.getStudentId(),
                    a.getCourseId(), a.getMarks());
        }
    }
}

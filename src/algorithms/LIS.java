package algorithms;

import java.util.*;

/**
 * Longest Increasing Subsequence (LIS) for analyzing student progress trends.
 * Uses dynamic programming with O(n log n) optimization via binary search.
 *
 * Time Complexity: O(n log n) with binary search optimization
 * Space Complexity: O(n)
 */
public class LIS {

    /**
     * Finds the length of the Longest Increasing Subsequence in the given array.
     */
    public int lengthOfLIS(double[] arr) {
        if (arr == null || arr.length == 0) return 0;

        // tails[i] = smallest possible tail value for increasing subsequence of length i+1
        double[] tails = new double[arr.length];
        int len = 1;
        tails[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < tails[0]) {
                tails[0] = arr[i];
            } else if (arr[i] >= tails[len - 1]) {
                tails[len++] = arr[i];
            } else {
                int pos = binarySearch(tails, 0, len - 1, arr[i]);
                tails[pos] = arr[i];
            }
        }
        return len;
    }

    private int binarySearch(double[] arr, int left, int right, double key) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * Finds the actual Longest Increasing Subsequence.
     */
    public List<Double> findLIS(double[] arr) {
        int n = arr.length;
        if (n == 0) return new ArrayList<>();

        int[] lis = new int[n];
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        Arrays.fill(lis, 1);

        int maxLen = 1, maxIdx = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] >= arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    prev[i] = j;
                }
            }
            if (lis[i] > maxLen) {
                maxLen = lis[i];
                maxIdx = i;
            }
        }

        // Reconstruct sequence
        List<Double> result = new ArrayList<>();
        int idx = maxIdx;
        while (idx >= 0) {
            result.add(arr[idx]);
            idx = prev[idx];
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Demonstrates LIS with progress trend analysis.
     */
    public void demonstrate() {
        System.out.println("\n=== LONGEST INCREASING SUBSEQUENCE: Performance Trend Analysis ===");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("Problem: Analyze student's progress trend over semesters\n");

        // Simulate a student's progress (scores) over 10 assessments
        double[] scores = {45, 55, 50, 60, 65, 58, 70, 75, 72, 80};

        System.out.println("Student's Assessment Scores Over Time:");
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("  Assessment %2d: %.0f%n", i + 1, scores[i]);
        }

        int lisLength = lengthOfLIS(scores);
        List<Double> lisSequence = findLIS(scores);

        System.out.println("\nLongest Increasing Subsequence Length: " + lisLength);
        System.out.println("Improvement Trend (Score Progression):");
        for (int i = 0; i < lisSequence.size(); i++) {
            System.out.printf("  %s", (i == 0 ? "" : "-> "));
            System.out.printf("%.0f ", lisSequence.get(i));
        }
        System.out.println();

        // Student 2 - declining performance
        double[] scores2 = {85, 78, 82, 70, 68, 72, 65, 60};
        System.out.println("\n\nSecond Student's Assessment Scores:");
        for (int i = 0; i < scores2.length; i++) {
            System.out.printf("  Assessment %2d: %.0f%n", i + 1, scores2[i]);
        }
        System.out.println("LIS Length: " + lengthOfLIS(scores2));
        System.out.println("\nAnalysis: First student shows consistent improvement (LIS=" + lisLength
                + "), indicating positive learning trend.");
    }
}

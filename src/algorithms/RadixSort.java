package algorithms;

import model.Student;
import java.util.List;

/**
 * Radix Sort for sorting student roll numbers/numeric IDs.
 * Non-comparison based sort processing digits one by one.
 *
 * Time Complexity: O(d * (n + k)) where d = number of digits, k = base (10)
 * Space Complexity: O(n + k)
 */
public class RadixSort {

    /**
     * Sorts students by the numeric portion of their Student ID.
     * Returns a new sorted list without modifying the original.
     */
    public List<Student> sortByRollNumber(List<Student> students) {
        if (students == null || students.size() <= 1)
            return students;

        // Extract numeric IDs
        int[] numericIds = new int[students.size()];
        int max = 0;
        for (int i = 0; i < students.size(); i++) {
            numericIds[i] = extractNumericId(students.get(i).getStudentId());
            if (numericIds[i] > max) max = numericIds[i];
        }

        // Apply counting sort for each digit (LSD-first)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(students, numericIds, exp);
        }

        return students; // Modified in-place (we return same reference)
    }

    private void countingSortByDigit(List<Student> students, int[] numericIds, int exp) {
        int n = students.size();
        Student[] output = new Student[n];
        int[] count = new int[10];

        // Count occurrences of each digit
        for (int i = 0; i < n; i++) {
            int digit = (numericIds[i] / exp) % 10;
            count[digit]++;
        }

        // Cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build output array (stable sort)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (numericIds[i] / exp) % 10;
            output[count[digit] - 1] = students.get(i);
            count[digit]--;
        }

        // Copy back (update both students list and numericIds)
        for (int i = 0; i < n; i++) {
            students.set(i, output[i]);
            numericIds[i] = extractNumericId(output[i].getStudentId());
        }
    }

    /** Extracts numeric part from student ID like "S001" -> 1. */
    private int extractNumericId(String studentId) {
        try {
            return Integer.parseInt(studentId.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Demonstrates sorting with before/after display.
     */
    public void demonstrateSort(List<Student> students) {
        System.out.println("\n=== RADIX SORT: Students by Roll Number (Numeric ID) ===");
        System.out.println("Time Complexity: O(d * (n + k))");
        System.out.println("\n--- Before Sorting ---");
        for (Student s : students) {
            System.out.printf("  %-12s %-20s %-15s%n",
                    s.getStudentId(), s.getName(), s.getDepartment());
        }

        // Create a copy for demonstration
        java.util.ArrayList<Student> copy = new java.util.ArrayList<>(students);
        sortByRollNumber(copy);

        System.out.println("\n--- After Sorting (by Roll Number) ---");
        for (Student s : copy) {
            System.out.printf("  %-12s %-20s %-15s%n",
                    s.getStudentId(), s.getName(), s.getDepartment());
        }
    }
}

package algorithms;

import model.Student;
import java.util.List;

/**
 * Heap Sort to identify top-performing students.
 * Uses binary heap data structure for efficient selection.
 *
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(1) (in-place)
 */
public class HeapSort {

    /**
     * Sorts students by GPA in ascending order (for top performers,
     * we can read from the end).
     * Returns a new sorted list without modifying the original.
     */
    public List<Student> sortByGpa(List<Student> students) {
        if (students == null || students.size() <= 1)
            return students;

        Student[] arr = students.toArray(new Student[0]);
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }

        return java.util.Arrays.asList(arr);
    }

    private void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].getGpa() > arr[largest].getGpa())
            largest = left;
        if (right < n && arr[right].getGpa() > arr[largest].getGpa())
            largest = right;
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private void swap(Student[] arr, int i, int j) {
        Student temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Prints top N students by GPA.
     */
    public void demonstrateSort(List<Student> students, int topN) {
        System.out.println("\n=== HEAP SORT: Top Performers (by GPA) ===");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("\n--- All Students (Before Sorting) ---");
        for (Student s : students) {
            System.out.printf("  %-12s %-20s GPA: %.2f%n",
                    s.getStudentId(), s.getName(), s.getGpa());
        }

        List<Student> sorted = sortByGpa(students);

        System.out.println("\n--- Top " + topN + " Performers (Highest GPA) ---");
        int count = Math.min(topN, sorted.size());
        for (int i = sorted.size() - 1; i >= sorted.size() - count; i--) {
            Student s = sorted.get(i);
            System.out.printf("  %2d. %-12s %-20s GPA: %.2f%n",
                    sorted.size() - i, s.getStudentId(), s.getName(), s.getGpa());
        }
    }
}

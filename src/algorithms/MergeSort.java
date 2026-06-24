package algorithms;

import model.Student;
import java.util.List;

/**
 * Merge Sort for sorting students by marks or academic performance (GPA).
 * Divide-and-conquer algorithm with stable sorting.
 *
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(n)
 */
public class MergeSort {

    /**
     * Sorts a list of students by GPA in descending order.
     * Returns a new sorted list without modifying the original.
     */
    public List<Student> sortByGpa(List<Student> students) {
        if (students == null || students.size() <= 1)
            return students;

        Student[] arr = students.toArray(new Student[0]);
        mergeSort(arr, 0, arr.length - 1);
        return java.util.Arrays.asList(arr);
    }

    private void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private void merge(Student[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] leftArr = new Student[n1];
        Student[] rightArr = new Student[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            // Sort by GPA descending (higher GPA first)
            if (leftArr[i].getGpa() >= rightArr[j].getGpa()) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    /**
     * Prints the students before and after sorting.
     */
    public void demonstrateSort(List<Student> students) {
        System.out.println("\n=== MERGE SORT: Students by GPA (Descending) ===");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("\n--- Before Sorting ---");
        for (Student s : students) {
            System.out.printf("  %-12s %-20s GPA: %.2f%n",
                    s.getStudentId(), s.getName(), s.getGpa());
        }

        List<Student> sorted = sortByGpa(students);

        System.out.println("\n--- After Sorting (by GPA descending) ---");
        for (int i = 0; i < sorted.size(); i++) {
            Student s = sorted.get(i);
            System.out.printf("  %2d. %-12s %-20s GPA: %.2f%n",
                    i + 1, s.getStudentId(), s.getName(), s.getGpa());
        }
    }
}

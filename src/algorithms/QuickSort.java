package algorithms;

import model.Course;
import java.util.List;

/**
 * Quick Sort to rank courses based on popularity or difficulty.
 * In-place sorting with efficient average-case performance.
 *
 * Time Complexity: O(n log n) average, O(n^2) worst
 * Space Complexity: O(log n) for recursion stack
 */
public class QuickSort {

    /**
     * Sorts courses by popularity in descending order.
     * Returns a new sorted list without modifying the original.
     */
    public List<Course> sortByPopularity(List<Course> courses) {
        if (courses == null || courses.size() <= 1)
            return courses;

        Course[] arr = courses.toArray(new Course[0]);
        quickSort(arr, 0, arr.length - 1);
        return java.util.Arrays.asList(arr);
    }

    private void quickSort(Course[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Course[] arr, int low, int high) {
        // Use median-of-three for better pivot selection
        int mid = low + (high - low) / 2;
        if (arr[high].getPopularity() > arr[low].getPopularity())
            swap(arr, low, high);
        if (arr[mid].getPopularity() > arr[high].getPopularity())
            swap(arr, mid, high);
        if (arr[mid].getPopularity() > arr[low].getPopularity())
            swap(arr, low, mid);

        Course pivot = arr[low]; // Pivot is now at arr[low]
        int i = low + 1;
        int j = high;

        while (i <= j) {
            while (i <= j && arr[i].getPopularity() >= pivot.getPopularity()) i++;
            while (i <= j && arr[j].getPopularity() < pivot.getPopularity()) j--;
            if (i < j) swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    private void swap(Course[] arr, int i, int j) {
        Course temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Prints courses before and after sorting by popularity.
     */
    public void demonstrateSort(List<Course> courses) {
        System.out.println("\n=== QUICK SORT: Courses by Popularity (Descending) ===");
        System.out.println("Time Complexity: O(n log n) average, O(n^2) worst");
        System.out.println("\n--- Before Sorting ---");
        for (Course c : courses) {
            System.out.printf("  %-10s %-25s Popularity: %d%n",
                    c.getCourseId(), c.getCourseName(), c.getPopularity());
        }

        List<Course> sorted = sortByPopularity(courses);

        System.out.println("\n--- After Sorting (by Popularity descending) ---");
        for (int i = 0; i < sorted.size(); i++) {
            Course c = sorted.get(i);
            System.out.printf("  %2d. %-10s %-25s Popularity: %d%n",
                    i + 1, c.getCourseId(), c.getCourseName(), c.getPopularity());
        }
    }
}

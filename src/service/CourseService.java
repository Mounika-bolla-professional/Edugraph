package service;

import algorithms.BST;
import model.Course;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for Course management operations.
 * Uses BST for efficient storage and retrieval by Course ID.
 */
public class CourseService {

    private BST courseBST;

    public CourseService() {
        this.courseBST = new BST();
    }

    /** Adds a course to the BST (also used for preloading). */
    public void addCourse(Course course) {
        courseBST.insert(course);
    }

    /** Adds a new course via user input. */
    public void addCourse(Scanner scanner) {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine().trim();

        if (courseBST.search(id) != null) {
            System.out.println("Course with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Credits: ");
        int credits = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Difficulty (1-5): ");
        int difficulty = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Popularity (1-100): ");
        int popularity = Integer.parseInt(scanner.nextLine().trim());

        Course course = new Course(id, name, credits, difficulty, popularity);
        courseBST.insert(course);
        System.out.println("Course added successfully!");
    }

    /** Searches and displays a course by ID. */
    public void searchCourse(Scanner scanner) {
        System.out.println("\n--- Search Course ---");
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine().trim();

        Course course = courseBST.search(id);
        if (course == null) {
            System.out.println("Course not found!");
        } else {
            System.out.println(Course.getSeparator());
            System.out.println(Course.getHeader());
            System.out.println(Course.getSeparator());
            System.out.println(course);
            System.out.println(Course.getSeparator());
        }
    }

    /** Updates an existing course. */
    public void updateCourse(Scanner scanner) {
        System.out.println("\n--- Update Course ---");
        System.out.print("Enter Course ID to update: ");
        String id = scanner.nextLine().trim();

        Course course = courseBST.search(id);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        System.out.println("Current details: " + course);
        System.out.println("Enter new values (press Enter to keep current):");

        System.out.print("Course Name [" + course.getCourseName() + "]: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) course.setCourseName(name);

        System.out.print("Credits [" + course.getCredits() + "]: ");
        String credStr = scanner.nextLine().trim();
        if (!credStr.isEmpty()) course.setCredits(Integer.parseInt(credStr));

        System.out.print("Difficulty [" + course.getDifficulty() + "]: ");
        String diffStr = scanner.nextLine().trim();
        if (!diffStr.isEmpty()) course.setDifficulty(Integer.parseInt(diffStr));

        System.out.print("Popularity [" + course.getPopularity() + "]: ");
        String popStr = scanner.nextLine().trim();
        if (!popStr.isEmpty()) course.setPopularity(Integer.parseInt(popStr));

        courseBST.insert(course);
        System.out.println("Course updated successfully!");
    }

    /** Deletes a course by ID. */
    public void deleteCourse(Scanner scanner) {
        System.out.println("\n--- Delete Course ---");
        System.out.print("Enter Course ID to delete: ");
        String id = scanner.nextLine().trim();

        Course course = courseBST.search(id);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        courseBST.delete(id);
        System.out.println("Course deleted successfully!");
    }

    /** Displays all courses sorted by ID. */
    public void displayAllCourses() {
        System.out.println("\n--- All Courses (Sorted by ID) ---");
        List<Course> courses = courseBST.inorder();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println(Course.getSeparator());
        System.out.println(Course.getHeader());
        System.out.println(Course.getSeparator());
        for (Course c : courses) {
            System.out.println(c);
        }
        System.out.println(Course.getSeparator());
        System.out.println("Total Courses: " + courses.size());
    }

    /** Returns the BST for external use. */
    public BST getCourseBST() { return courseBST; }

    /** Returns all courses as a list. */
    public List<Course> getAllCourses() { return courseBST.inorder(); }

    /** Returns a course by ID. */
    public Course getCourseById(String id) { return courseBST.search(id); }

    /** Returns count of courses. */
    public int getCourseCount() { return courseBST.inorder().size(); }
}

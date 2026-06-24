package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in the EduGraph system.
 * Includes details like credits, difficulty, popularity, and prerequisites.
 */
public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private int difficulty;    // 1 (easiest) to 5 (hardest)
    private int popularity;    // 1 (least) to 100 (most popular)
    private List<String> prerequisites; // List of prerequisite Course IDs

    public Course(String courseId, String courseName, int credits, int difficulty, int popularity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.difficulty = difficulty;
        this.popularity = popularity;
        this.prerequisites = new ArrayList<>();
    }

    public Course(String courseId, String courseName, int credits, int difficulty,
                  int popularity, List<String> prerequisites) {
        this(courseId, courseName, credits, difficulty, popularity);
        this.prerequisites = new ArrayList<>(prerequisites);
    }

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public int getPopularity() { return popularity; }
    public void setPopularity(int popularity) { this.popularity = popularity; }

    public List<String> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<String> prerequisites) { this.prerequisites = prerequisites; }
    public void addPrerequisite(String courseId) { this.prerequisites.add(courseId); }

    @Override
    public String toString() {
        return String.format("| %-10s | %-25s | %-4d | %-4d | %-4d | %-20s |",
                courseId, courseName, credits, difficulty, popularity,
                prerequisites.isEmpty() ? "None" : String.join(", ", prerequisites));
    }

    /** Returns a formatted header for course table display. */
    public static String getHeader() {
        return String.format("| %-10s | %-25s | %-4s | %-4s | %-4s | %-20s |",
                "Course ID", "Course Name", "Cred", "Diff", "Pop", "Prerequisites");
    }

    /** Returns a separator line for the course table. */
    public static String getSeparator() {
        return "+" + "-".repeat(12) + "+" + "-".repeat(27) + "+" + "-".repeat(6) + "+"
                + "-".repeat(6) + "+" + "-".repeat(6) + "+" + "-".repeat(22) + "+";
    }
}

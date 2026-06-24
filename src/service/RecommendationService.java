package service;

import model.Course;
import model.Student;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service that generates adaptive course recommendations for students.
 * Uses completed courses, GPA, learning progress, and prerequisite satisfaction.
 */
public class RecommendationService {

    private StudentService studentService;
    private CourseService courseService;
    private AssessmentService assessmentService;

    public RecommendationService(StudentService studentService,
                                  CourseService courseService,
                                  AssessmentService assessmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.assessmentService = assessmentService;
    }

    /**
     * Generates course recommendations for a specific student.
     *
     * Scoring factors:
     *   - Prerequisite satisfaction (required to be eligible)
     *   - GPA match (higher GPA students get harder courses)
     *   - Progress alignment
     *   - Difficulty preference based on past performance
     */
    public List<ScoredCourse> getRecommendations(String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) return new ArrayList<>();

        // Get courses the student has already taken (have assessments)
        Set<String> completedCourses = assessmentService.getAssessmentsByStudent(studentId)
                .stream().map(a -> a.getCourseId()).collect(Collectors.toSet());

        List<Course> allCourses = courseService.getAllCourses();
        List<ScoredCourse> scored = new ArrayList<>();

        for (Course course : allCourses) {
            // Skip courses already completed
            if (completedCourses.contains(course.getCourseId())) continue;

            // Check prerequisites
            boolean prereqSatisfied = true;
            for (String prereqId : course.getPrerequisites()) {
                if (!completedCourses.contains(prereqId)) {
                    prereqSatisfied = false;
                    break;
                }
            }
            if (!prereqSatisfied) continue;

            // Calculate recommendation score (0-100)
            double score = calculateScore(student, course);
            scored.add(new ScoredCourse(course, score));
        }

        // Sort by score descending
        scored.sort((a, b) -> Double.compare(b.score, a.score));
        return scored;
    }

    /** Calculates a recommendation score for a course given a student profile. */
    private double calculateScore(Student student, Course course) {
        double score = 50; // Base score

        // Factor 1: GPA alignment (higher GPA → harder courses)
        double gpaFactor = student.getGpa() / 4.0; // 0.0 to 1.0
        score += (gpaFactor * 20) * (course.getDifficulty() / 5.0);

        // Factor 2: Progress alignment
        double progressFactor = student.getProgress() / 100.0; // 0.0 to 1.0
        score += progressFactor * 15;

        // Factor 3: Popularity bonus
        score += (course.getPopularity() / 100.0) * 10;

        // Factor 4: Semester-based readiness
        int semesterFactor = Math.min(student.getSemester(), 8) / 8;
        score += semesterFactor * 5;

        return Math.min(100, Math.max(0, score));
    }

    /** Displays recommendations for a student. */
    public void displayRecommendations(Scanner scanner) {
        System.out.println("\n=== ADAPTIVE COURSE RECOMMENDATIONS ===");
        System.out.println("Algorithm: Multi-factor scoring with prerequisite satisfaction");
        System.out.println("Factors: GPA Alignment, Progress, Popularity, Semester Readiness\n");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Student: " + student.getName() + " (" + student.getStudentId() + ")");
        System.out.println("GPA: " + String.format("%.2f", student.getGpa()));
        System.out.println("Progress: " + String.format("%.1f%%", student.getProgress()));
        System.out.println();

        List<ScoredCourse> recommendations = getRecommendations(studentId);

        if (recommendations.isEmpty()) {
            System.out.println("No recommendations available. Complete more prerequisites first.");
            return;
        }

        System.out.println("Recommended Courses:");
        System.out.println("  " + "-".repeat(60));
        System.out.printf("  %-3s %-25s %-12s %-8s%n", "#", "Course", "Difficulty", "Score");
        System.out.println("  " + "-".repeat(60));

        int rank = 1;
        for (ScoredCourse sc : recommendations) {
            String diffStars = "★".repeat(sc.course.getDifficulty())
                    + "☆".repeat(5 - sc.course.getDifficulty());
            System.out.printf("  %-3d %-25s %-12s %-8.1f%n",
                    rank++,
                    sc.course.getCourseName().length() > 24
                            ? sc.course.getCourseName().substring(0, 24)
                            : sc.course.getCourseName(),
                    diffStars,
                    sc.score);
        }
        System.out.println("  " + "-".repeat(60));
    }

    /** Wrapper for a course with its recommendation score. */
    public static class ScoredCourse {
        public Course course;
        public double score;

        public ScoredCourse(Course course, double score) {
            this.course = course;
            this.score = score;
        }
    }
}

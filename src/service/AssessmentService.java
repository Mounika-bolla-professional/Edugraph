package service;

import model.Assessment;
import model.Student;
import model.Course;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for Assessment management operations.
 * Handles marks, grades, GPA calculation, and result reports.
 */
public class AssessmentService {

    private List<Assessment> assessments;
    private StudentService studentService;
    private CourseService courseService;

    public AssessmentService(StudentService studentService, CourseService courseService) {
        this.assessments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /** Adds an assessment record. */
    public void addAssessment(Assessment a) { assessments.add(a); }

    /** Adds marks for a student via user input. */
    public void addMarks(Scanner scanner) {
        System.out.println("\n--- Add Marks ---");
        System.out.print("Enter Assessment ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        if (studentService.getStudentById(studentId) == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("Course not found!");
            return;
        }

        System.out.print("Enter Marks (0-100): ");
        double marks = Double.parseDouble(scanner.nextLine().trim());

        Assessment assessment = new Assessment(id, studentId, courseId, marks);
        assessments.add(assessment);
        System.out.println("Marks added successfully! Grade: " + assessment.getGrade());
    }

    /** Generates grade for a specific assessment. */
    public void generateGrade(Scanner scanner) {
        System.out.println("\n--- Generate Grade ---");
        System.out.print("Enter Assessment ID: ");
        String id = scanner.nextLine().trim();

        Assessment assessment = findAssessment(id);
        if (assessment == null) {
            System.out.println("Assessment not found!");
            return;
        }

        System.out.println("Assessment: " + assessment.getAssessmentId());
        System.out.println("Student: " + assessment.getStudentId());
        System.out.println("Course: " + assessment.getCourseId());
        System.out.println("Marks: " + assessment.getMarks());
        System.out.println("Grade: " + assessment.getGrade());
    }

    /** Calculates GPA for a specific student. */
    public void calculateGPA(Scanner scanner) {
        System.out.println("\n--- GPA Calculation ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        // Find all assessments for this student
        List<Assessment> studentAssessments = assessments.stream()
                .filter(a -> a.getStudentId().equals(studentId))
                .collect(Collectors.toList());

        if (studentAssessments.isEmpty()) {
            System.out.println("No assessments found for this student.");
            return;
        }

        double totalGradePoints = 0;
        int totalCourses = 0;
        Map<String, List<Assessment>> byCourse = studentAssessments.stream()
                .collect(Collectors.groupingBy(Assessment::getCourseId));

        System.out.println("\nCourse-wise Performance:");
        System.out.println("  " + "-".repeat(50));
        System.out.printf("  %-15s %-8s %-8s %-8s%n", "Course", "Marks", "Grade", "Grade Pt");
        System.out.println("  " + "-".repeat(50));

        for (Map.Entry<String, List<Assessment>> entry : byCourse.entrySet()) {
            String courseId = entry.getKey();
            Course course = courseService.getCourseById(courseId);
            String courseName = (course != null) ? course.getCourseName() : courseId;

            // Average marks for the course
            double avgMarks = entry.getValue().stream()
                    .mapToDouble(Assessment::getMarks).average().orElse(0);
            String grade = Assessment.computeGrade(avgMarks);
            double gradePoint = Assessment.getGradePoints(grade);

            System.out.printf("  %-15s %-8.2f %-8s %-8.2f%n",
                    courseName.length() > 15 ? courseName.substring(0, 15) : courseName,
                    avgMarks, grade, gradePoint);

            totalGradePoints += gradePoint;
            totalCourses++;
        }

        double gpa = totalCourses > 0 ? totalGradePoints / totalCourses : 0;
        System.out.println("  " + "-".repeat(50));
        System.out.printf("  Calculated GPA: %.2f (from %d course(s))%n", gpa, totalCourses);
        student.setGpa(gpa);
    }

    /** Generates a full result report for a student. */
    public void studentResultReport(Scanner scanner) {
        System.out.println("\n--- Student Result Report ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("  STUDENT RESULT REPORT");
        System.out.println("=".repeat(70));
        System.out.printf("  Student: %s (%s)%n", student.getName(), student.getStudentId());
        System.out.printf("  Department: %s | Semester: %d | GPA: %.2f%n",
                student.getDepartment(), student.getSemester(), student.getGpa());
        System.out.println("-".repeat(70));

        List<Assessment> studentAssessments = assessments.stream()
                .filter(a -> a.getStudentId().equals(studentId))
                .sorted(Comparator.comparing(Assessment::getAssessmentId))
                .collect(Collectors.toList());

        if (studentAssessments.isEmpty()) {
            System.out.println("  No assessment records found.");
        } else {
            System.out.println(Assessment.getSeparator());
            System.out.println(Assessment.getHeader());
            System.out.println(Assessment.getSeparator());
            for (Assessment a : studentAssessments) {
                System.out.println(a);
            }
            System.out.println(Assessment.getSeparator());
        }

        System.out.println("=".repeat(70));
    }

    /** Returns all assessments. */
    public List<Assessment> getAllAssessments() { return assessments; }

    /** Returns assessments for a specific student. */
    public List<Assessment> getAssessmentsByStudent(String studentId) {
        return assessments.stream()
                .filter(a -> a.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    /** Returns assessments for a specific course. */
    public List<Assessment> getAssessmentsByCourse(String courseId) {
        return assessments.stream()
                .filter(a -> a.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    /** Finds an assessment by ID. */
    public Assessment findAssessment(String id) {
        return assessments.stream()
                .filter(a -> a.getAssessmentId().equals(id))
                .findFirst().orElse(null);
    }

    /** Returns count of assessments. */
    public int getAssessmentCount() { return assessments.size(); }
}

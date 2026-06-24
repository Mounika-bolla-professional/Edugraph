package model;

/**
 * Represents an assessment/score record for a student in a course.
 * Stores marks and computed grade.
 */
public class Assessment {
    private String assessmentId;
    private String studentId;
    private String courseId;
    private double marks;      // 0 to 100
    private String grade;      // A+, A, B+, B, C+, C, D, F

    public Assessment(String assessmentId, String studentId, String courseId, double marks) {
        this.assessmentId = assessmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
        this.grade = computeGrade(marks);
    }

    /** Computes letter grade from numerical marks. */
    public static String computeGrade(double marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B+";
        if (marks >= 60) return "B";
        if (marks >= 55) return "C+";
        if (marks >= 50) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    /** Returns grade points corresponding to letter grade. */
    public static double getGradePoints(String grade) {
        switch (grade) {
            case "A+": return 4.0;
            case "A":  return 4.0;
            case "B+": return 3.5;
            case "B":  return 3.0;
            case "C+": return 2.5;
            case "C":  return 2.0;
            case "D":  return 1.0;
            case "F":  return 0.0;
            default:   return 0.0;
        }
    }

    // Getters and Setters
    public String getAssessmentId() { return assessmentId; }
    public void setAssessmentId(String assessmentId) { this.assessmentId = assessmentId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) {
        this.marks = marks;
        this.grade = computeGrade(marks);
    }

    public String getGrade() { return grade; }

    @Override
    public String toString() {
        return String.format("| %-14s | %-12s | %-10s | %-6.2f | %-4s |",
                assessmentId, studentId, courseId, marks, grade);
    }

    /** Returns a formatted header for assessment table display. */
    public static String getHeader() {
        return String.format("| %-14s | %-12s | %-10s | %-6s | %-4s |",
                "Assessment ID", "Student ID", "Course ID", "Marks", "Grade");
    }

    /** Returns a separator line for the assessment table. */
    public static String getSeparator() {
        return "+" + "-".repeat(16) + "+" + "-".repeat(14) + "+" + "-".repeat(12) + "+"
                + "-".repeat(8) + "+" + "-".repeat(6) + "+";
    }
}

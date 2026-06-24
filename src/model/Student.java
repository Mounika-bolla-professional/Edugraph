package model;

/**
 * Represents a student in the EduGraph system.
 * Stores personal and academic information.
 */
public class Student {
    private String studentId;
    private String name;
    private String department;
    private int semester;
    private double gpa;
    private double progress; // 0.0 to 100.0

    public Student(String studentId, String name, String department, int semester, double gpa, double progress) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.semester = semester;
        this.gpa = gpa;
        this.progress = progress;
    }

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public double getProgress() { return progress; }
    public void setProgress(double progress) { this.progress = progress; }

    @Override
    public String toString() {
        return String.format("| %-12s | %-20s | %-15s | %-4d | %-5.2f | %-6.2f%% |",
                studentId, name, department, semester, gpa, progress);
    }

    /** Returns a formatted header for student table display. */
    public static String getHeader() {
        return String.format("| %-12s | %-20s | %-15s | %-4s | %-5s | %-8s |",
                "Student ID", "Name", "Department", "Sem", "GPA", "Progress");
    }

    /** Returns a separator line for the student table. */
    public static String getSeparator() {
        return "+" + "-".repeat(14) + "+" + "-".repeat(22) + "+" + "-".repeat(17) + "+"
                + "-".repeat(6) + "+" + "-".repeat(7) + "+" + "-".repeat(10) + "+";
    }
}

package util;

import model.Student;
import model.Course;
import model.Assessment;
import service.StudentService;
import service.CourseService;
import service.AssessmentService;

import java.util.Arrays;
import java.util.List;

/**
 * Generates and preloads sample data for the EduGraph system.
 * Creates 20 students, 15 courses, and 50 assessments.
 */
public class SampleData {

    /**
     * Preloads all sample data into the respective services.
     */
    public static void preload(StudentService studentService,
                                CourseService courseService,
                                AssessmentService assessmentService) {
        preloadStudents(studentService);
        preloadCourses(courseService);
        preloadAssessments(assessmentService);
        System.out.println("Sample data loaded: 20 students, 15 courses, 50 assessments.");
    }

    // ========== 20 Students ==========
    private static void preloadStudents(StudentService service) {
        List<Student> students = Arrays.asList(
            new Student("S001", "Aarav Sharma",    "Computer Science",    4, 3.75, 85.0),
            new Student("S002", "Priya Patel",     "Computer Science",    4, 3.90, 92.0),
            new Student("S003", "Rahul Verma",     "Information Technology", 3, 3.20, 60.0),
            new Student("S004", "Sneha Reddy",     "Computer Science",    2, 3.50, 70.0),
            new Student("S005", "Vikram Singh",    "Electrical Engg",     4, 2.80, 55.0),
            new Student("S006", "Ananya Gupta",    "Information Technology", 3, 3.60, 78.0),
            new Student("S007", "Arjun Nair",      "Computer Science",    2, 3.95, 95.0),
            new Student("S008", "Divya Kulkarni",  "Mechanical Engg",     4, 3.10, 65.0),
            new Student("S009", "Rohit Joshi",     "Computer Science",    3, 2.50, 45.0),
            new Student("S010", "Kavya Iyer",      "Information Technology", 1, 3.80, 88.0),
            new Student("S011", "Manish Kumar",    "Electrical Engg",     3, 3.30, 72.0),
            new Student("S012", "Pooja Deshmukh",  "Computer Science",    4, 4.00, 98.0),
            new Student("S013", "Karan Mehta",     "Mechanical Engg",     2, 2.90, 50.0),
            new Student("S014", "Isha Agarwal",    "Information Technology", 1, 3.45, 75.0),
            new Student("S015", "Siddharth Rao",   "Computer Science",    3, 3.70, 82.0),
            new Student("S016", "Neha Saxena",     "Electrical Engg",     2, 3.25, 68.0),
            new Student("S017", "Aditya Pandey",   "Computer Science",    1, 3.55, 76.0),
            new Student("S018", "Riya Kapoor",     "Information Technology", 4, 3.85, 90.0),
            new Student("S019", "Harsh Tiwari",    "Mechanical Engg",     3, 2.70, 48.0),
            new Student("S020", "Tanvi Bhatt",     "Computer Science",    2, 3.65, 80.0)
        );

        for (Student s : students) {
            service.addStudent(s);
        }
    }

    // ========== 15 Courses ==========
    private static void preloadCourses(CourseService service) {
        List<Course> courses = Arrays.asList(
            new Course("C001", "Programming Fundamentals", 4, 2, 95,
                       Arrays.asList()),
            new Course("C002", "Data Structures",          4, 3, 90,
                       Arrays.asList("C001")),
            new Course("C003", "Algorithms",               4, 4, 85,
                       Arrays.asList("C001", "C002")),
            new Course("C004", "Database Systems",         3, 3, 80,
                       Arrays.asList("C001")),
            new Course("C005", "Operating Systems",        3, 4, 70,
                       Arrays.asList("C001", "C002")),
            new Course("C006", "Computer Networks",        3, 3, 65,
                       Arrays.asList("C001", "C002")),
            new Course("C007", "Software Engineering",     3, 3, 75,
                       Arrays.asList("C001", "C002")),
            new Course("C008", "Machine Learning",         4, 5, 88,
                       Arrays.asList("C002", "C003")),
            new Course("C009", "Web Development",          3, 2, 92,
                       Arrays.asList("C001")),
            new Course("C010", "Artificial Intelligence",  4, 5, 82,
                       Arrays.asList("C003", "C008")),
            new Course("C011", "Computer Graphics",        3, 3, 55,
                       Arrays.asList("C001", "C002")),
            new Course("C012", "Cyber Security",           3, 4, 78,
                       Arrays.asList("C004", "C006")),
            new Course("C013", "Mobile App Development",   3, 3, 72,
                       Arrays.asList("C001", "C009")),
            new Course("C014", "Cloud Computing",          3, 4, 85,
                       Arrays.asList("C004", "C005")),
            new Course("C015", "Blockchain Technology",    3, 5, 60,
                       Arrays.asList("C003", "C008"))
        );

        for (Course c : courses) {
            service.addCourse(c);
        }
    }

    // ========== 50 Assessments ==========
    private static void preloadAssessments(AssessmentService service) {
        // Student assessment data: (id, studentId, courseId, marks)
        List<Assessment> assessments = Arrays.asList(
            // Student S001 - Aarav
            new Assessment("A001", "S001", "C001", 85),
            new Assessment("A002", "S001", "C002", 78),
            new Assessment("A003", "S001", "C003", 72),
            new Assessment("A004", "S001", "C004", 80),

            // Student S002 - Priya
            new Assessment("A005", "S002", "C001", 92),
            new Assessment("A006", "S002", "C002", 88),
            new Assessment("A007", "S002", "C003", 85),
            new Assessment("A008", "S002", "C008", 90),

            // Student S003 - Rahul
            new Assessment("A009", "S003", "C001", 65),
            new Assessment("A010", "S003", "C002", 58),
            new Assessment("A011", "S003", "C004", 62),

            // Student S004 - Sneha
            new Assessment("A012", "S004", "C001", 75),
            new Assessment("A013", "S004", "C002", 70),
            new Assessment("A014", "S004", "C009", 72),

            // Student S005 - Vikram
            new Assessment("A015", "S005", "C001", 55),
            new Assessment("A016", "S005", "C004", 50),

            // Student S006 - Ananya
            new Assessment("A017", "S006", "C001", 80),
            new Assessment("A018", "S006", "C002", 78),
            new Assessment("A019", "S006", "C009", 82),

            // Student S007 - Arjun
            new Assessment("A020", "S007", "C001", 95),
            new Assessment("A021", "S007", "C002", 92),
            new Assessment("A022", "S007", "C003", 90),

            // Student S008 - Divya
            new Assessment("A023", "S008", "C001", 62),
            new Assessment("A024", "S008", "C005", 58),

            // Student S009 - Rohit
            new Assessment("A025", "S009", "C001", 48),
            new Assessment("A026", "S009", "C002", 42),

            // Student S010 - Kavya
            new Assessment("A027", "S010", "C001", 88),
            new Assessment("A028", "S010", "C009", 85),
            new Assessment("A029", "S010", "C013", 82),

            // Student S011 - Manish
            new Assessment("A030", "S011", "C001", 68),
            new Assessment("A031", "S011", "C004", 65),

            // Student S012 - Pooja
            new Assessment("A032", "S012", "C001", 96),
            new Assessment("A033", "S012", "C002", 94),
            new Assessment("A034", "S012", "C003", 95),
            new Assessment("A035", "S012", "C008", 92),

            // Student S013 - Karan
            new Assessment("A036", "S013", "C001", 52),

            // Student S014 - Isha
            new Assessment("A037", "S014", "C001", 72),
            new Assessment("A038", "S014", "C009", 70),

            // Student S015 - Siddharth
            new Assessment("A039", "S015", "C001", 78),
            new Assessment("A040", "S015", "C002", 75),
            new Assessment("A041", "S015", "C003", 72),
            new Assessment("A042", "S015", "C008", 68),

            // Student S016 - Neha
            new Assessment("A043", "S016", "C001", 65),
            new Assessment("A044", "S016", "C004", 60),

            // Student S017 - Aditya
            new Assessment("A045", "S017", "C001", 76),

            // Student S018 - Riya
            new Assessment("A046", "S018", "C001", 88),
            new Assessment("A047", "S018", "C002", 84),
            new Assessment("A048", "S018", "C009", 86),

            // Student S019 - Harsh
            new Assessment("A049", "S019", "C001", 50),

            // Student S020 - Tanvi
            new Assessment("A050", "S020", "C001", 78)
        );

        for (Assessment a : assessments) {
            service.addAssessment(a);
        }
    }
}

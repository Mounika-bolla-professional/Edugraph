package service;

import algorithms.AVLTree;
import model.Student;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for Student management operations.
 * Uses AVL Tree for efficient storage and retrieval.
 */
public class StudentService {

    private AVLTree studentTree;

    public StudentService() {
        this.studentTree = new AVLTree();
    }

    // -------- Data Population --------

    /** Adds a student to the AVL tree (also used for preloading). */
    public void addStudent(Student student) {
        studentTree.insert(student);
    }

    // -------- CRUD Operations --------

    /** Adds a new student via user input. */
    public void addStudent(Scanner scanner) {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        // Check if already exists
        if (studentTree.search(id) != null) {
            System.out.println("Student with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine().trim();

        System.out.print("Enter Semester: ");
        int sem = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter GPA (0.0 - 4.0): ");
        double gpa = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Progress % (0 - 100): ");
        double progress = Double.parseDouble(scanner.nextLine().trim());

        Student student = new Student(id, name, dept, sem, gpa, progress);
        studentTree.insert(student);
        System.out.println("Student added successfully!");
    }

    /** Searches and displays a student by ID. */
    public void searchStudent(Scanner scanner) {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        Student student = studentTree.search(id);
        if (student == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println(Student.getSeparator());
            System.out.println(Student.getHeader());
            System.out.println(Student.getSeparator());
            System.out.println(student);
            System.out.println(Student.getSeparator());
        }
    }

    /** Updates an existing student's information. */
    public void updateStudent(Scanner scanner) {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine().trim();

        Student student = studentTree.search(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Current details: " + student);
        System.out.println("Enter new values (press Enter to keep current):");

        System.out.print("Name [" + student.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) student.setName(name);

        System.out.print("Department [" + student.getDepartment() + "]: ");
        String dept = scanner.nextLine().trim();
        if (!dept.isEmpty()) student.setDepartment(dept);

        System.out.print("Semester [" + student.getSemester() + "]: ");
        String semStr = scanner.nextLine().trim();
        if (!semStr.isEmpty()) student.setSemester(Integer.parseInt(semStr));

        System.out.print("GPA [" + String.format("%.2f", student.getGpa()) + "]: ");
        String gpaStr = scanner.nextLine().trim();
        if (!gpaStr.isEmpty()) student.setGpa(Double.parseDouble(gpaStr));

        System.out.print("Progress % [" + String.format("%.2f", student.getProgress()) + "]: ");
        String progStr = scanner.nextLine().trim();
        if (!progStr.isEmpty()) student.setProgress(Double.parseDouble(progStr));

        // Re-insert to update in AVL tree
        studentTree.insert(student);
        System.out.println("Student updated successfully!");
    }

    /** Deletes a student by ID. */
    public void deleteStudent(Scanner scanner) {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine().trim();

        Student student = studentTree.search(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        studentTree.delete(id);
        System.out.println("Student deleted successfully!");
    }

    /** Displays all students sorted by ID. */
    public void displayAllStudents() {
        System.out.println("\n--- All Students (Sorted by ID) ---");
        List<Student> students = studentTree.inorder();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println(Student.getSeparator());
        System.out.println(Student.getHeader());
        System.out.println(Student.getSeparator());
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println(Student.getSeparator());
        System.out.println("Total Students: " + students.size());
    }

    /** Returns the AVL tree for external use. */
    public AVLTree getStudentTree() { return studentTree; }

    /** Returns all students as a list. */
    public List<Student> getAllStudents() { return studentTree.inorder(); }

    /** Returns a student by ID. */
    public Student getStudentById(String id) { return studentTree.search(id); }

    /** Returns count of students. */
    public int getStudentCount() { return studentTree.inorder().size(); }
}

package algorithms;

import model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * B-Tree for indexing large student performance datasets.
 * A multi-way search tree where each node can have multiple children and keys.
 *
 * Time Complexities:
 * - Insert: O(t * log_t(n))
 * - Search: O(t * log_t(n))
 * - Traversal: O(n)
 * where t = minimum degree, n = number of keys
 */
public class BTree {

    private int t; // Minimum degree
    private BTreeNode root;

    /** B-Tree node. */
    private class BTreeNode {
        int n;                       // Current number of keys
        Student[] students;          // Array of students (keys)
        BTreeNode[] children;        // Array of child pointers
        boolean leaf;                // Is this a leaf?

        BTreeNode(boolean leaf) {
            this.leaf = leaf;
            this.students = new Student[2 * t - 1];
            this.children = new BTreeNode[2 * t];
            this.n = 0;
        }
    }

    public BTree(int degree) {
        this.t = Math.max(2, degree);
        this.root = new BTreeNode(true);
    }

    // ---------- Search ----------

    /** Searches for a student by Student ID. */
    public Student search(String studentId) {
        return searchRec(root, studentId);
    }

    private Student searchRec(BTreeNode node, String studentId) {
        int i = 0;
        while (i < node.n && studentId.compareTo(node.students[i].getStudentId()) > 0)
            i++;

        if (i < node.n && studentId.equals(node.students[i].getStudentId()))
            return node.students[i];

        if (node.leaf) return null;

        return searchRec(node.children[i], studentId);
    }

    // ---------- Insert ----------

    /** Inserts a student into the B-Tree. */
    public void insert(Student student) {
        BTreeNode r = root;
        if (r.n == 2 * t - 1) {
            BTreeNode s = new BTreeNode(false);
            s.children[0] = r;
            splitChild(s, 0, r);
            root = s;
            insertNonFull(s, student);
        } else {
            insertNonFull(r, student);
        }
    }

    private void insertNonFull(BTreeNode node, Student student) {
        int i = node.n - 1;

        if (node.leaf) {
            // Find position and shift keys
            while (i >= 0 && student.getStudentId().compareTo(node.students[i].getStudentId()) < 0) {
                node.students[i + 1] = node.students[i];
                i--;
            }
            node.students[i + 1] = student;
            node.n++;
        } else {
            while (i >= 0 && student.getStudentId().compareTo(node.students[i].getStudentId()) < 0)
                i--;
            i++;

            if (node.children[i].n == 2 * t - 1) {
                splitChild(node, i, node.children[i]);
                if (student.getStudentId().compareTo(node.students[i].getStudentId()) > 0)
                    i++;
            }
            insertNonFull(node.children[i], student);
        }
    }

    private void splitChild(BTreeNode parent, int i, BTreeNode child) {
        BTreeNode newNode = new BTreeNode(child.leaf);
        newNode.n = t - 1;

        // Copy last t-1 keys to new node
        for (int j = 0; j < t - 1; j++)
            newNode.students[j] = child.students[j + t];

        // Copy last t children to new node
        if (!child.leaf) {
            for (int j = 0; j < t; j++)
                newNode.children[j] = child.children[j + t];
        }

        child.n = t - 1;

        // Shift parent children to make room
        for (int j = parent.n; j >= i + 1; j--)
            parent.children[j + 1] = parent.children[j];

        parent.children[i + 1] = newNode;

        // Shift parent keys
        for (int j = parent.n - 1; j >= i; j--)
            parent.students[j + 1] = parent.students[j];

        parent.students[i] = child.students[t - 1];
        parent.n++;
    }

    // ---------- Traversal ----------

    /** Returns all students sorted by ID (inorder traversal of B-Tree). */
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(BTreeNode node, List<Student> result) {
        if (node == null) return;
        int i;
        for (i = 0; i < node.n; i++) {
            if (!node.leaf) traverse(node.children[i], result);
            result.add(node.students[i]);
        }
        if (!node.leaf) traverse(node.children[i], result);
    }
}

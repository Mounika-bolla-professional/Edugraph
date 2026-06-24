package algorithms;

import model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * AVL Tree for managing Student records keyed by StudentID.
 * Self-balancing BST with height difference ≤ 1 at every node.
 *
 * Time Complexities:
 * - Insert: O(log n)
 * - Search: O(log n)
 * - Delete: O(log n)
 * - Rotations: O(1)
 */
public class AVLTree {

    /** Node class for AVL Tree. */
    private class Node {
        Student student;
        Node left, right;
        int height;

        Node(Student student) {
            this.student = student;
            this.height = 1;
        }
    }

    private Node root;

    // ---------- Height & Balance ----------

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private int max(int a, int b) { return (a > b) ? a : b; }

    // ---------- Rotations ----------

    /** Right rotate subtree rooted with y. */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    /** Left rotate subtree rooted with x. */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // ---------- Insert ----------

    /** Inserts a student record into the AVL tree. */
    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) return new Node(student);

        int cmp = student.getStudentId().compareTo(node.student.getStudentId());
        if (cmp < 0)
            node.left = insertRec(node.left, student);
        else if (cmp > 0)
            node.right = insertRec(node.right, student);
        else {
            node.student = student; // Update existing
            return node;
        }

        // Update height
        node.height = 1 + max(height(node.left), height(node.right));

        // Balance the node
        return balance(node);
    }

    // ---------- Delete ----------

    /** Deletes a student record by Student ID. */
    public void delete(String studentId) {
        root = deleteRec(root, studentId);
    }

    private Node deleteRec(Node node, String studentId) {
        if (node == null) return null;

        int cmp = studentId.compareTo(node.student.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            // Node to delete found
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                node = temp; // temp may be null (0 children)
            } else {
                // Node with two children: get inorder successor
                Node minNode = minValueNode(node.right);
                node.student = minNode.student;
                node.right = deleteRec(node.right, minNode.student.getStudentId());
            }
        }

        if (node == null) return null;

        // Update height
        node.height = 1 + max(height(node.left), height(node.right));

        // Balance the node
        return balance(node);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // ---------- Balance ----------

    /** Apply rotations to maintain AVL balance. */
    private Node balance(Node node) {
        int balance = getBalance(node);

        // Left-Left case
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // Left-Right case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Right case
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // Right-Left case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // ---------- Search ----------

    /** Searches for a student by Student ID. */
    public Student search(String studentId) {
        Node result = searchRec(root, studentId);
        return (result == null) ? null : result.student;
    }

    private Node searchRec(Node node, String studentId) {
        if (node == null) return null;
        int cmp = studentId.compareTo(node.student.getStudentId());
        if (cmp < 0) return searchRec(node.left, studentId);
        if (cmp > 0) return searchRec(node.right, studentId);
        return node;
    }

    // ---------- Traversals ----------

    /** Returns all students sorted by Student ID (inorder). */
    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node node, List<Student> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.student);
            inorderRec(node.right, result);
        }
    }

    public boolean isEmpty() { return root == null; }
}

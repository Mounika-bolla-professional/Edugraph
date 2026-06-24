package algorithms;

import model.Course;
import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree for managing Course records keyed by CourseID.
 *
 * Time Complexities:
 * - Insert: O(h) average O(log n), worst O(n)
 * - Search: O(h) average O(log n), worst O(n)
 * - Delete: O(h) average O(log n), worst O(n)
 * - Inorder Traversal: O(n)
 */
public class BST {

    /** Node class for BST. */
    private class Node {
        Course course;
        Node left, right;

        Node(Course course) {
            this.course = course;
        }
    }

    private Node root;

    /** Inserts a course into the BST by Course ID. */
    public void insert(Course course) {
        root = insertRec(root, course);
    }

    private Node insertRec(Node node, Course course) {
        if (node == null) return new Node(course);

        int cmp = course.getCourseId().compareTo(node.course.getCourseId());
        if (cmp < 0)
            node.left = insertRec(node.left, course);
        else if (cmp > 0)
            node.right = insertRec(node.right, course);
        else
            node.course = course; // Update existing
        return node;
    }

    /** Searches for a course by Course ID. */
    public Course search(String courseId) {
        Node result = searchRec(root, courseId);
        return (result == null) ? null : result.course;
    }

    private Node searchRec(Node node, String courseId) {
        if (node == null) return null;
        int cmp = courseId.compareTo(node.course.getCourseId());
        if (cmp < 0) return searchRec(node.left, courseId);
        if (cmp > 0) return searchRec(node.right, courseId);
        return node;
    }

    /** Deletes a course by Course ID. */
    public void delete(String courseId) {
        root = deleteRec(root, courseId);
    }

    private Node deleteRec(Node node, String courseId) {
        if (node == null) return null;

        int cmp = courseId.compareTo(node.course.getCourseId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, courseId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, courseId);
        } else {
            // Node to delete found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // Node with two children: get inorder successor
            node.course = minValue(node.right);
            node.right = deleteRec(node.right, node.course.getCourseId());
        }
        return node;
    }

    private Course minValue(Node node) {
        Course min = node.course;
        while (node.left != null) {
            min = node.left.course;
            node = node.left;
        }
        return min;
    }

    /** Returns all courses in sorted order (by Course ID). */
    public List<Course> inorder() {
        List<Course> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node node, List<Course> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.course);
            inorderRec(node.right, result);
        }
    }

    /** Returns all courses in reverse sorted order. */
    public List<Course> reverseInorder() {
        List<Course> result = new ArrayList<>();
        reverseInorderRec(root, result);
        return result;
    }

    private void reverseInorderRec(Node node, List<Course> result) {
        if (node != null) {
            reverseInorderRec(node.right, result);
            result.add(node.course);
            reverseInorderRec(node.left, result);
        }
    }

    public boolean isEmpty() { return root == null; }
}

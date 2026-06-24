package algorithms;

import java.util.*;

/**
 * Activity Selection algorithm to schedule optimal study sessions.
 * Greedy approach that selects maximum number of non-overlapping activities.
 *
 * Time Complexity: O(n log n) for sorting, O(n) for selection
 * Space Complexity: O(n) for storing selected activities
 */
public class ActivitySelection {

    /** Represents a study session with start and end time. */
    public static class StudySession {
        String topic;
        int start; // Start hour (0-23)
        int end;   // End hour (0-23)

        public StudySession(String topic, int start, int end) {
            this.topic = topic;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("  %-25s %02d:00 - %02d:00",
                    topic, start, end);
        }
    }

    /**
     * Selects maximum number of non-overlapping study sessions.
     */
    public List<StudySession> selectMaxSessions(List<StudySession> sessions) {
        if (sessions == null || sessions.isEmpty())
            return new ArrayList<>();

        // Sort by end time (greedy choice)
        List<StudySession> sorted = new ArrayList<>(sessions);
        sorted.sort(Comparator.comparingInt(s -> s.end));

        List<StudySession> selected = new ArrayList<>();
        selected.add(sorted.get(0));
        int lastEnd = sorted.get(0).end;

        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i).start >= lastEnd) {
                selected.add(sorted.get(i));
                lastEnd = sorted.get(i).end;
            }
        }

        return selected;
    }

    /**
     * Demonstrates the activity selection algorithm.
     */
    public void demonstrate() {
        System.out.println("\n=== ACTIVITY SELECTION: Study Schedule Optimization ===");
        System.out.println("Time Complexity: O(n log n) for sorting, O(n) for selection");
        System.out.println("Problem: Select maximum non-overlapping study sessions\n");

        List<StudySession> sessions = new ArrayList<>();
        sessions.add(new StudySession("Data Structures Review", 8, 10));
        sessions.add(new StudySession("Algorithm Practice", 9, 11));
        sessions.add(new StudySession("Database Systems", 10, 12));
        sessions.add(new StudySession("Machine Learning", 11, 13));
        sessions.add(new StudySession("Web Development", 13, 15));
        sessions.add(new StudySession("Software Engineering", 14, 16));
        sessions.add(new StudySession("Networking Basics", 15, 17));
        sessions.add(new StudySession("Project Work", 16, 18));

        System.out.println("Available Study Sessions:");
        System.out.println("  " + "-".repeat(45));
        for (StudySession s : sessions) {
            System.out.println(s.toString());
        }

        List<StudySession> selected = selectMaxSessions(sessions);

        System.out.println("\nSelected Optimal Schedule (" + selected.size() + " sessions):");
        System.out.println("  " + "-".repeat(45));
        for (StudySession s : selected) {
            System.out.println(s.toString());
        }
    }
}

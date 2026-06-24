# EduGraph – Adaptive Learning & Course Recommendation Platform

A complete Java console application demonstrating **Data Structures and Algorithms** through a personalized learning and academic recommendation platform.

## Features

- **Student Management** – CRUD operations for 20+ students using AVL Tree
- **Course Management** – CRUD operations for 15+ courses using Binary Search Tree
- **Assessment Management** – Marks, grades, GPA calculation, result reports
- **Tree Algorithms (CO1)** – BST, AVL Tree, B-Tree indexing
- **Graph Algorithms (CO3)** – BFS, DFS, Cycle Detection, Prim's MST
- **Shortest Path (CO4)** – Dijkstra, Bellman-Ford, Floyd-Warshall
- **Sorting (CO5)** – Merge Sort, Quick Sort, Heap Sort, Counting Sort, Radix Sort
- **Greedy & DP (CO6)** – Activity Selection, Fractional Knapsack, 0/1 Knapsack, LIS
- **Adaptive Recommendations** – Multi-factor course recommendation engine
- **Analytics Dashboard** – Top performers, course rankings, assessment statistics

---

## Project Structure

```
EduGraph/
│
├── src/
│   ├── model/
│   │   ├── Student.java          # Student entity with GPA, progress
│   │   ├── Course.java           # Course entity with prereqs, credits
│   │   └── Assessment.java       # Assessment entity with marks, grades
│   │
│   ├── algorithms/
│   │   ├── BST.java              # Binary Search Tree (Course storage)
│   │   ├── AVLTree.java          # AVL Tree (Student storage)
│   │   ├── BTree.java            # B-Tree (Student indexing)
│   │   ├── Graph.java            # Graph (prerequisites: BFS, DFS, Cycle, MST)
│   │   ├── Dijkstra.java         # Shortest learning path
│   │   ├── BellmanFord.java      # Weighted dependency analysis
│   │   ├── FloydWarshall.java    # All-pairs shortest paths
│   │   ├── MergeSort.java        # Students by GPA
│   │   ├── QuickSort.java        # Courses by popularity
│   │   ├── HeapSort.java         # Top performers
│   │   ├── CountingSort.java     # Exam IDs
│   │   ├── RadixSort.java        # Roll numbers
│   │   ├── ActivitySelection.java# Study schedule optimization
│   │   ├── Knapsack01.java       # Course selection (credit limit)
│   │   ├── FractionalKnapsack.java# Learning time allocation
│   │   └── LIS.java              # Performance trend analysis
│   │
│   ├── service/
│   │   ├── StudentService.java    # Student operations
│   │   ├── CourseService.java     # Course operations
│   │   ├── AssessmentService.java # Assessment operations
│   │   └── RecommendationService.java # Adaptive course recommendations
│   │
│   ├── util/
│   │   └── SampleData.java       # Preloads 20 students, 15 courses, 50 assessments
│   │
│   └── Main.java                 # Main entry point with menu system
│
└── README.md                     # This file
```

---

## UML Diagram

```
┌─────────────────────────────────────────────────────────────────────┐
│                        EduGraph Application                        │
├─────────────────────────────────────────────────────────────────────┤
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐             │
│  │   Student    │  │    Course    │  │  Assessment  │             │
│  ├──────────────┤  ├──────────────┤  ├──────────────┤             │
│  │-studentId    │  │-courseId     │  │-assessmentId │             │
│  │-name         │  │-courseName   │  │-studentId    │             │
│  │-department   │  │-credits      │  │-courseId     │             │
│  │-semester     │  │-difficulty   │  │-marks        │             │
│  │-gpa          │  │-popularity   │  │-grade        │             │
│  │-progress     │  │-prereqs[]    │  └──────────────┘             │
│  └──────────────┘  └──────────────┘                               │
│         ▲                                                          │
│         │ uses                                                     │
│  ┌──────┴─────────────────────────────────────────────────────┐   │
│  │                    Service Layer                            │   │
│  │  ┌────────────────┐ ┌────────────────┐ ┌────────────────┐  │   │
│  │  │ StudentService │ │ CourseService  │ │AssessmentSvc   │  │   │
│  │  │ (AVLTree)      │ │ (BST)          │ │ (ArrayList)    │  │   │
│  │  └────────────────┘ └────────────────┘ └────────────────┘  │   │
│  │  ┌──────────────────────────────────────────────────────┐  │   │
│  │  │ RecommendationService                                │  │   │
│  │  │ (Multi-factor scoring engine)                        │  │   │
│  │  └──────────────────────────────────────────────────────┘  │   │
│  └──────────────────────────────────────────────────────────┘   │
│         ▲                                                          │
│         │ uses                                                     │
│  ┌──────┴─────────────────────────────────────────────────────┐   │
│  │                  Algorithm Layer                            │   │
│  │  ┌───────┐┌──────┐┌─────┐┌───────┐┌──────────┬─────────┐  │   │
│  │  │ BST   ││ AVL  ││BTree││ Graph ││ Dijkstra │Bellman  │  │   │
│  │  │       ││ Tree ││     ││BFS/DFS││          │ Ford    │  │   │
│  │  └───────┘└──────┘└─────┘└───────┘└──────────┴─────────┘  │   │
│  │  ┌───────┐┌──────┐┌─────┐┌───────┐┌──────────┬─────────┐  │   │
│  │  │Merge  ││Quick ││Heap ││Count  ││Activity  │Knapsack │  │   │
│  │  │Sort   ││Sort  ││Sort ││Sort   ││Selection │(0/1,Frac)│  │   │
│  │  └───────┘└──────┘└─────┘└───────┘└──────────┴─────────┘  │   │
│  │  ┌───────┐┌──────┐                                         │   │
│  │  │Radix  ││LIS   │                                         │   │
│  │  │Sort   ││      │                                         │   │
│  │  └───────┘└──────┘                                         │   │
│  └──────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## Time Complexities of Every Algorithm

### CO1 – Trees & Balanced Search Structures

| Algorithm | Operation | Time Complexity | Space Complexity |
|-----------|-----------|----------------|------------------|
| **BST** | Insert | O(h) avg O(log n), worst O(n) | O(n) |
| | Search | O(h) avg O(log n), worst O(n) | O(1) |
| | Delete | O(h) avg O(log n), worst O(n) | O(n) |
| | Inorder | O(n) | O(n) |
| **AVL Tree** | Insert | O(log n) | O(log n) |
| | Search | O(log n) | O(1) |
| | Delete | O(log n) | O(log n) |
| | Rotations | O(1) each | O(1) |
| **B-Tree** | Insert | O(t · log_t n) | O(n) |
| | Search | O(t · log_t n) | O(1) |
| | Traversal | O(n) | O(n) |

### CO2 – Multiway Trees & Range Query Structures

| Algorithm | Operation | Time Complexity | Space Complexity |
|-----------|-----------|----------------|------------------|
| **B-Tree** (indexing) | Search | O(t · log_t n) | O(n) |
| **B+ Tree** (range) | Range Query | O(log n + k) | O(n) |
| **Segment Tree** | Build | O(n) | O(n) |
| | Range Sum | O(log n) | O(log n) |
| | Range Max | O(log n) | O(log n) |
| **Fenwick Tree** | Prefix Sum | O(log n) | O(n) |
| | Update | O(log n) | O(1) |

### CO3 – Graph Algorithms for Learning Networks

| Algorithm | Operation | Time Complexity | Space Complexity |
|-----------|-----------|----------------|------------------|
| **Graph** | BFS | O(V + E) | O(V) |
| | DFS | O(V + E) | O(V) |
| | Cycle Detection | O(V + E) | O(V) |
| | Prim's MST | O(E log V) | O(V) |
| | Topological Sort | O(V + E) | O(V) |

### CO4 – Shortest Path Optimization

| Algorithm | Operation | Time Complexity | Space Complexity |
|-----------|-----------|----------------|------------------|
| **Dijkstra** | Single-source | O((V+E) log V) | O(V) |
| **Bellman-Ford** | Single-source | O(V · E) | O(V) |
| **Floyd-Warshall** | All-pairs | O(V³) | O(V²) |

### CO5 – Advanced Sorting & Data Ranking

| Algorithm | Operation | Time Complexity | Space Complexity |
|-----------|-----------|----------------|------------------|
| **Merge Sort** | Sort by GPA | O(n log n) | O(n) |
| **Quick Sort** | Sort by Popularity | O(n log n) avg, O(n²) worst | O(log n) |
| **Heap Sort** | Top Performers | O(n log n) | O(1) |
| **Counting Sort** | Exam IDs | O(n + k) | O(k) |
| **Radix Sort** | Roll Numbers | O(d · (n + k)) | O(n + k) |

### CO6 – Greedy Algorithms & Dynamic Programming

| Algorithm | Operation | Time Complexity | Space Complexity |
|-----------|-----------|----------------|------------------|
| **Activity Selection** | Schedule | O(n log n) | O(n) |
| **Fractional Knapsack** | Time Allocation | O(n log n) | O(n) |
| **0/1 Knapsack** | Course Selection | O(n · W) | O(n · W) |
| **LIS** | Trend Analysis | O(n log n) | O(n) |

---

## How Algorithms Are Used in EduGraph

### CO1: Trees & Balanced Search Structures
- **Binary Search Tree** stores all courses keyed by CourseID. Enables fast lookup by course code and sorted traversal for course catalogs.
- **AVL Tree** stores all students keyed by StudentID. Self-balancing ensures O(log n) performance for insert/search/delete regardless of insertion order.
- **Tree Traversals** (inorder) generate sorted course lists and sorted student rosters.

### CO2: Multiway Trees & Range Query Structures
- **B-Tree** indexes larger student datasets with efficient disk-I/O-like operations. Used to demonstrate student indexing.
- **Segment Trees** are used conceptually for range sum/max queries on assessment scores.
- **Fenwick Trees** enable efficient prefix sum calculations for cumulative score analytics.

### CO3: Graph Algorithms for Learning Networks
- **Graph** represents course prerequisites as a directed graph where edges point from prerequisite to dependent course.
- **BFS** explores the breadth of available courses a student can take after completing a given course.
- **DFS** deeply traverses prerequisite chains to understand full dependency trees.
- **Cycle Detection** ensures no circular prerequisite relationships exist.
- **Prim's MST** finds the minimum-weight set of prerequisite edges connecting all courses (optimal curriculum).

### CO4: Shortest Path Optimization
- **Dijkstra** finds the shortest (least difficulty) learning path from one course to another.
- **Bellman-Ford** handles weighted dependencies including scenarios with negative weights.
- **Floyd-Warshall** computes all-pairs shortest paths, useful for comprehensive course planning.
- **Topological Sort** determines the correct order to complete courses based on prerequisites.

### CO5: Advanced Sorting & Data Ranking
- **Merge Sort** stably sorts students by GPA (descending) for ranking.
- **Quick Sort** efficiently ranks courses by popularity score.
- **Heap Sort** extracts top N performers using a max-heap.
- **Counting Sort** sorts assessment/exam IDs in linear time.
- **Radix Sort** sorts student roll numbers by processing digits.

### CO6: Greedy Algorithms & Dynamic Programming
- **Activity Selection** finds the maximum number of non-overlapping study sessions a student can attend.
- **Fractional Knapsack** helps allocate limited study time across courses to maximize learning value.
- **0/1 Knapsack** selects an optimal set of courses within a credit limit (each course taken once).
- **LIS** analyzes student assessment scores over time to detect improvement trends.

---

## VS Code Execution Instructions

### Prerequisites
- **Java JDK 17+** installed and on system PATH
- **Visual Studio Code** with Java Extension Pack (optional but recommended)

### Steps to Run

#### Method 1: VS Code (Recommended)

1. **Open the project folder** in VS Code:
   - `File → Open Folder...` → Select `EduGraph/`

2. **Install Java Extension Pack** (if not already installed):
   - Open Extensions panel (`Ctrl+Shift+X`)
   - Search for "Extension Pack for Java" by Microsoft
   - Click Install

3. **Run the application**:
   - Open `src/Main.java`
   - Click the "Run" button (▶) above the `main` method
   - OR press `F5` to start debugging
   - OR right-click in the editor → "Run Java"

#### Method 2: Command Line

```bash
# Navigate to the EduGraph directory
cd path\to\EduGraph

# Compile all Java files
javac -d . src\model\*.java src\algorithms\*.java src\service\*.java src\util\*.java src\Main.java

# Run the application
java Main
```

Or simply:

```bash
cd path\to\EduGraph
javac -d . -sourcepath src src\Main.java
java Main
```

### Sample Output

```
==================================================
          EDUGRAPH PLATFORM
  Adaptive Learning & Course Recommendation
==================================================
  1.  Student Management
  2.  Course Management
  3.  Assessment Management
  4.  Tree Algorithms (CO1 - BST, AVL)
  5.  Graph Algorithms (CO3 - BFS, DFS, MST)
  6.  Shortest Path Algorithms (CO4)
  7.  Sorting Algorithms (CO5)
  8.  Dynamic Programming & Greedy (CO6)
  9.  Recommendations
  10. Analytics Dashboard
  11. Exit
--------------------------------------------------
Enter your choice:
```

---

## Sample Data

The application automatically preloads:
- **20 Students** from various departments (Computer Science, IT, Electrical, Mechanical)
- **15 Courses** with prerequisite relationships (Programming Fundamentals → Data Structures → Algorithms → Machine Learning, etc.)
- **50 Assessments** linking students to courses with marks and computed grades

---

## Architecture

The project follows a **layered architecture**:

- **Model Layer** (`model/`): POJO classes with fields, constructors, getters/setters
- **Algorithm Layer** (`algorithms/`): Pure DSA implementations independent of business logic
- **Service Layer** (`service/`): Business logic using the algorithm classes
- **Utility Layer** (`util/`): Sample data generation
- **Presentation Layer** (`Main.java`): Console-based menu system

Each class is in a separate file following OOP principles.
No external libraries, databases, or frameworks are used.

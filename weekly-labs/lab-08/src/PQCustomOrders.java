import java.util.*;

class Task {
    final String name;
    final int priority;
    final int deadline;

    Task(String name, int priority, int deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("{%s, p=%d, d=%d}", name, priority, deadline);
    }
}

public class PQCustomOrders {
    public static void main(String[] args) {
        List<Task> tasks = Arrays.asList(
                new Task("Email", 2, 30),
                new Task("Report", 1, 120),
                new Task("Slides", 1, 90),
                new Task("Backup", 3, 15),
                new Task("Refactor", 2, 15),
                new Task("Deploy", 1, 15));

        class priorityFirst implements Comparator<Task> {
            public int compare(Task a, Task b) {
                if (a.priority != b.priority)
                    return Integer.compare(a.priority, b.priority);
                return Integer.compare(a.deadline, b.deadline);
            }
        }

        class deadlineFirst implements Comparator<Task> {
            public int compare(Task a, Task b) {
                if (a.deadline != b.deadline)
                    return Integer.compare(a.deadline, b.deadline);
                return Integer.compare(a.priority, b.priority);
            }
        }

        PriorityQueue<Task> A = new PriorityQueue<>(new priorityFirst());
        PriorityQueue<Task> B = new PriorityQueue<>(new deadlineFirst());

        for (int i = 0; i < tasks.size(); i++) {
            A.add(tasks.get(i));
            B.add(tasks.get(i));
        }

        System.out.println("Queue A:");
        while (!A.isEmpty()) {
            System.out.println(A.remove());
        }

        System.out.println("\nQueue B:");
        while (!B.isEmpty()) {
            System.out.println(B.remove());
        }
    }
}
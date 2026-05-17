import java.util.*;

class Patient {

    String name;
    int severity;
    int arrivalTime;

    Patient(String name, int severity, int arrivalTime) {
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    public String toString() {
        return name + " (severity=" + severity + ", arrival=" + arrivalTime + ")";
    }
}

public class lab8_3 {
    public static void main(String[] args) {
        Patient p1 = new Patient("Alice", 7, 1);
        Patient p2 = new Patient("Bob", 9, 2);
        Patient p3 = new Patient("Charlie", 7, 3);
        Patient p4 = new Patient("Dina", 10, 4);

        class FirstSE implements Comparator<Patient> {
            public int compare(Patient a, Patient b) {
                if (a.severity != b.severity) {
                    if (a.severity > b.severity) {
                        return -1;
                    } else if (a.severity < b.severity) {
                        return 1;
                    }
                }
                return Integer.compare(a.arrivalTime, b.arrivalTime);
            }
        }

        PriorityQueue<Patient> list = new PriorityQueue<>(new FirstSE());
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        while (!list.isEmpty()) {
            System.out.println("Treating: " + list.remove());
        }
    }
}

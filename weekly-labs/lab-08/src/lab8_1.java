
import java.util.*;

class Student {

    private int id;
    private String firstName;
    private String lastName;

    public Student() {
    }

    public Student(String fname, String lname, int id) {
        firstName = fname;
        lastName = lname;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String fname) {
        firstName = fname;
    }

    public void setLastName(String lname) {
        lastName = lname;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String toString() {
        return firstName + " " + lastName + ", ID: " + id;
    }
}

public class lab8_1 {

    public static void main(String[] args) {
        ArrayList<Student> x = new ArrayList<>();
        Student s1 = new Student("Joe", "leo", 110);
        Student s2 = new Student("Mike", "Smith", 111);
        Student s3 = new Student("Ali", "Ward", 112);
        Student s4 = new Student("Hadi", "Audi", 113);
        Student s5 = new Student("Laure", "Fani", 114);
        x.add(s1);
        x.add(s2);
        x.add(s3);
        x.add(s4);
        x.add(s5);

        class SortByLast implements Comparator<Student> {
            public int compare(Student st1, Student st2) {
                int r = st1.getLastName().compareTo(st2.getLastName());
                return (r != 0) ? r : st1.getFirstName().compareTo(st2.getFirstName());
            }
        }

        class SortByLength implements Comparator<Student> {
            public int compare(Student st1, Student st2) {
                return st1.getFirstName().length() - st2.getFirstName().length();
            }
        }

        class SortById implements Comparator<Student> {
            public int compare(Student st1, Student st2) {
                return st1.getId() - st2.getId();
            }
        }

        Collections.sort(x, new SortByLast());
        System.out.println("Sort by last name:");
        System.out.println(x);

        Collections.sort(x, new SortByLength());
        System.out.println("Sort by first name length:");
        System.out.println(x);

        Collections.sort(x, new SortById());
        System.out.println("Sort by ID:");
        System.out.println(x);
    }
}

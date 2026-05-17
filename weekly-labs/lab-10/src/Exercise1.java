import java.util.*;

public class Exercise1 {

    public static Set<String> uniqueNames(ArrayList<String> names) {
        Set<String> set = new HashSet<>();
        for (String name : names) {
            set.add(name);
        }
        return set;
        // return new HashSet<>(names);
    }

    public static Set<String> commonNames(ArrayList<String> list1, ArrayList<String> list2) {
        Set<String> set = new HashSet<>(list1);
        set.retainAll(list2);
        return set;
    }

    public static Set<String> onlyInFirst(ArrayList<String> list1, ArrayList<String> list2) {
        Set<String> set = new HashSet<>(list1);
        set.removeAll(list2);
        return set;
    }

    public static void main(String[] args) {

        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(
                "Ali", "Sara", "John", "Ali", "Maya", "John", "Lea"));

        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(
                "John", "Mira", "Sara", "Sara", "Omar", "Lea"));

        System.out.println(uniqueNames(list1));
        System.out.println(commonNames(list1, list2));
        System.out.println(onlyInFirst(list1, list2));
    }
}
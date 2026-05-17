import java.util.*;

public class Exercise4 {

    public static void removeFailing(Set<Integer> scores) {
        Iterator<Integer> it = scores.iterator();
        while (it.hasNext()) {
            int x = it.next();
            if (x < 60) {
                it.remove();
            }
        }
    }

    public static void removeFailingStudents(Map<String, Integer> scores) {
        Iterator<String> it = scores.keySet().iterator();
        while (it.hasNext()) {
            String name = it.next();
            int grade = scores.get(name);
            if (grade < 60) {
                it.remove();
            }
        }
    }

    public static void main(String[] args) {
        Set<Integer> setScores = new HashSet<>(Arrays.asList(94, 38, 87, 43, 72));
        removeFailing(setScores);
        System.out.println(setScores);

        Map<String, Integer> mapScores = new HashMap<>();
        mapScores.put("Jenny", 38);
        mapScores.put("Stef", 94);
        mapScores.put("Greg", 87);
        mapScores.put("Marty", 43);
        mapScores.put("Angela", 72);

        removeFailingStudents(mapScores);
        System.out.println(mapScores);
    }
}
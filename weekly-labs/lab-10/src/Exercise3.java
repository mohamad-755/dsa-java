import java.util.*;

public class Exercise3 {

    public static Map<Integer, Set<String>> reverseGrades(Map<String, Integer> userGrades) {
        Map<Integer, Set<String>> result = new HashMap<>();

        for (String student : userGrades.keySet()) {
            int grade = userGrades.get(student);

            if (!result.containsKey(grade)) {
                result.put(grade, new HashSet<>());
            }

            result.get(grade).add(student);
        }

        return result;
    }

    public static int gradeWithMostStudents(Map<Integer, Set<String>> gradeMap) {
        int maxGrade = -1;
        int maxCount = 0;

        for (int grade : gradeMap.keySet()) {
            int size = gradeMap.get(grade).size();
            if (size > maxCount) {
                maxCount = size;
                maxGrade = grade;
            }
        }

        return maxGrade;
    }

    public static Set<String> studentsWithSameGradeInBothExams(
            Map<String, Integer> exam1,
            Map<String, Integer> exam2) {

        Set<String> result = new HashSet<>();

        for (String student : exam1.keySet()) {
            if (exam2.containsKey(student) &&
                    exam1.get(student).equals(exam2.get(student))) {
                result.add(student);
            }
        }

        return result;
    }

    // Optional Part
    public static Map<Integer, Integer> gradeFrequencies(Map<Integer, Set<String>> gradeMap) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int grade : gradeMap.keySet()) {
            freq.put(grade, gradeMap.get(grade).size());
        }

        return freq;
    }

    public static Set<Integer> gradesWithAtLeastKStudents(
            Map<Integer, Integer> freqMap, int k) {

        Set<Integer> result = new HashSet<>();

        for (int grade : freqMap.keySet()) {
            if (freqMap.get(grade) >= k) {
                result.add(grade);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Map<String, Integer> studentGrades = new HashMap<>();
        studentGrades.put("Ali", 90);
        studentGrades.put("Sara", 85);
        studentGrades.put("John", 90);
        studentGrades.put("Maya", 85);
        studentGrades.put("Omar", 70);
        studentGrades.put("Lea", 90);

        Map<Integer, Set<String>> reversed = reverseGrades(studentGrades);
        System.out.println(reversed);
        System.out.println(gradeWithMostStudents(reversed));

        Map<Integer, Integer> freqMap = gradeFrequencies(reversed);
        System.out.println(freqMap);
        System.out.println(gradesWithAtLeastKStudents(freqMap, 2));

        Map<String, Integer> exam1 = new HashMap<>();
        exam1.put("Ali", 90);
        exam1.put("Sara", 85);
        exam1.put("John", 90);
        exam1.put("Maya", 70);

        Map<String, Integer> exam2 = new HashMap<>();
        exam2.put("Ali", 90);
        exam2.put("Sara", 80);
        exam2.put("John", 90);
        exam2.put("Omar", 70);

        System.out.println(studentsWithSameGradeInBothExams(exam1, exam2));
    }
}
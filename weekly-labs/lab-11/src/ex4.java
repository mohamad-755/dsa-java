import java.util.*;

public class ex4 {
    public static Map<Integer, Integer> union(HashMap<Integer, Integer> a, HashMap<Integer, Integer> b) {
        Map<Integer, Integer> x = new HashMap<>();
        for (Integer q : a.keySet()) {
            if (b.containsKey(q)) {
                x.put(q, b.get(q) + a.get(q));
            } else {
                x.put(q, a.get(q));
            }
        }
        for (Integer q : b.keySet()) {
            if (!x.containsKey(q)) {
                x.put(q, b.get(q));
            }
        }
        return x;
        /*
         * Map<Integer, Integer> y = new HashMap<>(a);
         * for(Integer o: b.keySet()){
         * if(y.containsKey(o)){
         * y.put(o, y.get(o)+b.get(o));
         * }
         * else{
         * y.put(o, b.get(o));
         * }
         * }
         */
    }

    public static Map<String, Integer> reverse(Map<Integer, String> a) {
        Map<String, Integer> x = new HashMap<>();
        for (Integer q : a.keySet()) {
            x.put(a.get(q), q);
        }
        return x;
    }

    public static Map<String, Integer> intersect(Map<String, Integer> a, Map<String, Integer> b) {
        Map<String, Integer> x = new HashMap<>();
        for (String q : a.keySet()) {
            if (b.containsKey(q) && a.get(q).equals(b.get(q))) {
                x.put(q, a.get(q));
            }
        }
        return x;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> x = new HashMap<>();
        HashMap<Integer, Integer> a = new HashMap<>();
        HashMap<Integer, Integer> b = new HashMap<>();
        a.put(7, 1);
        a.put(18, 5);
        a.put(42, 3);
        a.put(76, 10);
        a.put(98, 2);
        a.put(234, 50);

        b.put(7, 2);
        b.put(11, 9);
        b.put(42, -12);
        b.put(98, 4);
        b.put(234, 0);
        b.put(9999, 3);

        x = union(a, b);
        System.out.println(x);

        Map<Integer, String> y = new HashMap<>();
        y.put(42, "Marty");
        y.put(81, "Sue");
        y.put(17, "Ed");
        y.put(31, "Dave");
        y.put(56, "Ed");
        y.put(3, "Marty");
        y.put(29, "Ed");
        Map<String, Integer> w = reverse(y);
        System.out.println(w);

        Map<String, Integer> m = new HashMap<>();
        Map<String, Integer> z = new HashMap<>();

        m.put("Janet", 87);
        m.put("Logan", 62);
        m.put("Whitaker", 46);
        m.put("Alyssa", 100);
        m.put("Stefanie", 80);
        m.put("Jeff", 88);
        m.put("Kim", 52);
        m.put("Sylvia", 95);

        z.put("Logan", 62);
        z.put("Kim", 52);
        z.put("Whitaker", 52);
        z.put("Jeff", 88);
        z.put("Stefanie", 80);
        z.put("Brian", 60);
        z.put("Lisa", 83);
        z.put("Sylvia", 87);

        Map<String, Integer> result = intersect(m, z);
        System.out.println(result);

    }
}

import java.util.*;

public class ex5 {
    private static final int N = 11;
    private static final int Iterations = 4;
    private static final int AVAILABLE = Integer.MIN_VALUE;

    private int[] table1 = new int[N];
    private int[] table2 = new int[N];

    public ex5() {
        Arrays.fill(table1, AVAILABLE);
        Arrays.fill(table2, AVAILABLE);
    }

    public int h1(int key) {
        return key % N;
    }

    public int h2(int key) {
        return (key / N) % N;
    }

    public boolean insert(int key) {
        int[] oldTable1 = Arrays.copyOf(table1, N);
        int[] oldTable2 = Arrays.copyOf(table2, N);

        int current = key;
        boolean inTable1 = true;

        for (int i = 0; i < Iterations; i++) {
            if (inTable1) {
                int p = h1(current);

                if (table1[p] == AVAILABLE) {
                    table1[p] = current;
                    return true;
                } else {
                    int temp = table1[p];
                    table1[p] = current;
                    current = temp;
                    inTable1 = false;
                }
            } else {
                int p = h2(current);

                if (table2[p] == AVAILABLE) {
                    table2[p] = current;
                    return true;
                } else {
                    int temp = table2[p];
                    table2[p] = current;
                    current = temp;
                    inTable1 = true;
                }
            }
        }

        table1 = oldTable1;
        table2 = oldTable2;
        System.out.println("Insertion failed for key " + key);
        return false;
    }

    public boolean contains(int key) {
        return table1[h1(key)] == key || table2[h2(key)] == key;
    }

    public void printTables() {
        System.out.printf("%-6s %-8s %-8s%n", "Index", "Table1", "Table2");

        for (int i = 0; i < N; i++) {
            String t1 = (table1[i] == AVAILABLE) ? "-" : String.valueOf(table1[i]);
            String t2 = (table2[i] == AVAILABLE) ? "-" : String.valueOf(table2[i]);

            System.out.printf("%-6d %-8s %-8s%n", i, t1, t2);
        }
    }

    public static void main(String[] args) {
        ex5 c = new ex5();

        c.insert(22);
        c.insert(33);
        c.insert(44);
        c.insert(55);
        c.insert(56);
        c.insert(37);
        c.insert(66);
        c.insert(67);
        c.insert(77);
        c.insert(78);

        c.printTables();
    }

    /*
     * Final state:
     *
     * Index: 0 1 2 3 4 5 6 7 8 9 10
     * Table1: 55 67 - - 37 - - - - - -
     * Table2: - - 22 33 44 56 66 - - - -
     *
     * Failed insertions: 77, 78
     */
}
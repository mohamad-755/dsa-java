import java.util.LinkedList;

public class HashTables {

    // -------------------------------------------------------
    // Collision resolution strategy selector
    // -------------------------------------------------------
    public enum Method {
        LINEAR_PROBING,
        SEPARATE_CHAINING,
        QUADRATIC_PROBING,
        DOUBLE_HASHING
    }

    // -------------------------------------------------------
    // Constants
    // -------------------------------------------------------
    private static final int CAPACITY = 11; // N = 11 (prime)
    private static final int Q = 7; // secondary prime for double hashing: d(k) = Q - (k mod Q)
    private static final int AVAILABLE = Integer.MIN_VALUE; // sentinel for lazy deletion

    // -------------------------------------------------------
    // Internal storage
    // -------------------------------------------------------

    // Used by LINEAR_PROBING, QUADRATIC_PROBING, DOUBLE_HASHING
    private int[] table;

    // Used by SEPARATE_CHAINING
    private LinkedList<Integer>[] chains;

    private final Method method;

    // -------------------------------------------------------
    // Constructor
    // -------------------------------------------------------
    @SuppressWarnings("unchecked")
    public HashTables(Method method) {
        this.method = method;

        if (method == Method.SEPARATE_CHAINING) {
            chains = new LinkedList[CAPACITY];
            for (int i = 0; i < CAPACITY; i++) {
                chains[i] = new LinkedList<>();
            }
        } else {
            table = new int[CAPACITY];
            // Fill with a sentinel meaning "empty"
            java.util.Arrays.fill(table, Integer.MAX_VALUE);
        }
    }

    // -------------------------------------------------------
    // Primary hash function h(k) = k mod N
    // -------------------------------------------------------
    private int hash(int key) {
        return key % CAPACITY;
    }

    // -------------------------------------------------------
    // Secondary hash function for Double Hashing
    // d(k) = Q - (k mod Q)
    // -------------------------------------------------------
    private int secondaryHash(int key) {
        return Q - (key % Q);
    }

    // ===============================================================
    // INSERT
    // ===============================================================
    public void insert(int key) {
        switch (method) {
            case LINEAR_PROBING:
                insertLinear(key);
                break;
            case SEPARATE_CHAINING:
                insertChaining(key);
                break;
            case QUADRATIC_PROBING:
                insertQuadratic(key);
                break;
            case DOUBLE_HASHING:
                insertDouble(key);
                break;
        }
    }

    // --- 1. Linear Probing ---
    private void insertLinear(int key) {
        int index = hash(key);
        int count = 0;
        while (count < CAPACITY) {
            if (table[index] == Integer.MAX_VALUE || table[index] == AVAILABLE) {
                table[index] = key;
                break;
            } else {
                index = (index + 1) % CAPACITY;
                count++;
            }
        }

        /*
         * for (int i = 0; i < CAPACITY; i++) {
         * if (table[index] == Integer.MAX_VALUE || table[index] == AVAILABLE) {
         * table[index] = key;
         * return;
         * }
         * index = (index + 1) % CAPACITY; // move circularly
         * }
         */

        // TODO: probe circularly until an empty slot (Integer.MAX_VALUE)
        // or an AVAILABLE slot is found, then place the key there.
        //
        // Hint:
        // index = (index + 1) % CAPACITY to move to the next slot
        // A slot is free if table[index] == Integer.MAX_VALUE || table[index] ==
        // AVAILABLE
    }

    // --- 2. Separate Chaining ---
    private void insertChaining(int key) {
        int index = hash(key);
        if (!chains[index].contains(key)) {
            chains[index].add(key);
        }

        // TODO: append key to the linked list at chains[index]
        // (only if not already present)
    }

    // --- 3. Quadratic Probing ---
    private void insertQuadratic(int key) {
        int base = hash(key);
        for (int i = 0; i < CAPACITY; i++) {
            int index = (base + i * i) % CAPACITY;
            if (table[index] == Integer.MAX_VALUE || table[index] == AVAILABLE) {
                table[index] = key;
                return;
            }
        }

        // TODO: for i = 0, 1, 2, ..., CAPACITY-1:
        // index = (base + i*i) % CAPACITY
        // place key in the first empty slot found.
    }

    // --- 4. Double Hashing ---
    private void insertDouble(int key) {
        int base = hash(key);
        int step = secondaryHash(key);
        for (int i = 0; i < CAPACITY; i++) {
            int index = (base + i * step) % CAPACITY;
            if (table[index] == Integer.MAX_VALUE || table[index] == AVAILABLE) {
                table[index] = key;
                return;
            }
        }
        // TODO: for i = 0, 1, 2, ..., CAPACITY-1:
        // index = (base + i * step) % CAPACITY
        // place key in the first empty slot found.
    }

    // ===============================================================
    // CONTAINS
    // ===============================================================
    public boolean contains(int key) {
        switch (method) {
            case LINEAR_PROBING:
                return containsLinear(key);
            case SEPARATE_CHAINING:
                return containsChaining(key);
            case QUADRATIC_PROBING:
                return containsQuadratic(key);
            case DOUBLE_HASHING:
                return containsDouble(key);
            default:
                return false;
        }
    }

    // --- 1. Linear Probing ---
    private boolean containsLinear(int key) {
        int index = hash(key);
        for (int i = 0; i < CAPACITY; i++) {
            if (table[index] == Integer.MAX_VALUE) {
                return false;
            } else if (table[index] == key) {
                return true;
            }
            index = (index + 1) % CAPACITY;
        }
        return false;
        // TODO: probe circularly.
        // - If table[index] == Integer.MAX_VALUE → key is not in table, return false
        // - If table[index] == AVAILABLE → slot was deleted, keep probing
        // - If table[index] == key → found, return true
        // - Stop after CAPACITY probes
    }

    // --- 2. Separate Chaining ---
    private boolean containsChaining(int key) {
        int index = hash(key);
        // TODO: return true if chains[index] contains key
        if (chains[index].contains(key)) {
            return true;
        }
        return false;
    }

    // --- 3. Quadratic Probing ---
    private boolean containsQuadratic(int key) {
        int base = hash(key);
        // TODO: mirror the insertQuadratic probe sequence;
        // return true if key is found before an empty slot
        for (int i = 0; i < CAPACITY; i++) {
            int index = (base + i * i) % CAPACITY;
            if (table[index] == Integer.MAX_VALUE) {
                return false;
            } else if (table[index] == key) {
                return true;
            }
        }
        return false;
    }

    // --- 4. Double Hashing ---
    private boolean containsDouble(int key) {
        int base = hash(key);
        int step = secondaryHash(key);
        // TODO: mirror the insertDouble probe sequence;
        // return true if key is found before an empty slot
        for (int i = 0; i < CAPACITY; i++) {
            int index = (base + step * i) % CAPACITY;
            if (table[index] == Integer.MAX_VALUE) {
                return false;
            } else if (table[index] == key) {
                return true;
            }
        }
        return false;
    }

    // ===============================================================
    // REMOVE (Linear Probing only — lazy deletion)
    // ===============================================================
    public void remove(int key) {
        if (method != Method.LINEAR_PROBING) {
            throw new UnsupportedOperationException("remove() only implemented for LINEAR_PROBING");
        }

        int index = hash(key);
        for (int i = 0; i < CAPACITY; i++) {
            if (table[index] == Integer.MAX_VALUE) {
                return;
            } else if (table[index] == key) {
                table[index] = AVAILABLE;
                return;
            }
            index = (index + 1) % CAPACITY;
        }
        // TODO: probe circularly to find the key.
        // When found, set table[index] = AVAILABLE (do NOT set it to Integer.MAX_VALUE
        // / null).
        // we don't set the deleted slot to Integer.MAX_VALUE (null) because
        // Integer.MAX_VALUE means that the probe sequence stops.
        // If we set it to null, contains() might stop early think that this element is
        // not in the table, even if it exists later in the probe sequence.
        // We put AVAILBLE to indicate that probing should continue.
        //
        // ---------------------------------------------------------------------------------
    }

    // ===============================================================
    // DISPLAY (helper — already implemented)
    // ===============================================================
    public void display() {
        System.out.println("=== " + method + " ===");
        if (method == Method.SEPARATE_CHAINING) {
            for (int i = 0; i < CAPACITY; i++) {
                System.out.print("  [" + i + "] ");
                if (chains[i].isEmpty()) {
                    System.out.println("(empty)");
                } else {
                    System.out.println(chains[i]);
                }
            }
        } else {
            System.out.print("  Index: ");
            for (int i = 0; i < CAPACITY; i++)
                System.out.printf("%5d", i);
            System.out.print("\n  Value: ");
            for (int v : table) {
                if (v == Integer.MAX_VALUE)
                    System.out.printf("%5s", "-");
                else if (v == AVAILABLE)
                    System.out.printf("%5s", "DEL");
                else
                    System.out.printf("%5d", v);
            }
            System.out.println();
        }
        System.out.println();
    }

    // ===============================================================
    // MAIN — test all four strategies with the lab's key sequence
    // ===============================================================
    public static void main(String[] args) {

        int[] keys = { 9, 26, 50, 15, 2, 21, 36, 22, 32 };

        // --- Linear Probing ---
        HashTables lp = new HashTables(Method.LINEAR_PROBING);
        for (int k : keys)
            lp.insert(k);
        lp.display();

        // --- Separate Chaining ---
        HashTables sc = new HashTables(Method.SEPARATE_CHAINING);
        for (int k : keys)
            sc.insert(k);
        sc.display();

        // --- Quadratic Probing ---
        HashTables qp = new HashTables(Method.QUADRATIC_PROBING);
        for (int k : keys)
            qp.insert(k);
        qp.display();

        // --- Double Hashing ---
        HashTables dh = new HashTables(Method.DOUBLE_HASHING);
        for (int k : keys)
            dh.insert(k);
        dh.display();

        // --- Test remove (Linear Probing) ---
        System.out.println("=== Remove test (Linear Probing) ===");
        lp.remove(26);
        System.out.println("After removing 26:");
        lp.display();
        System.out.println("contains(26): " + lp.contains(26)); // expected: false
        System.out.println("contains(50): " + lp.contains(50)); // expected: true
    }

    // Linear Probing
    // 22, 32, 2, 36, 26, 15, 50, null, null, 9, 21

    // Quadratic Probing
    // 22, null, 2, 36, 26, 15, 50, null, 32, 9, 21

    // Separate Chaining
    /*
     * 0 → 22 → null
     * 1 → null
     * 2 → 2 → null
     * 3 → 36 → null
     * 4 → 26 → 15 → null
     * 5 → null
     * 6 → 50 → null
     * 7 → null
     * 8 → null
     * 9 → 9 → null
     * 10 → 21 → 32 → null
     */

    // Double Hashing
    // 22, null, 2, 36, 26, 21, 50, null, 32, 9, 15
}
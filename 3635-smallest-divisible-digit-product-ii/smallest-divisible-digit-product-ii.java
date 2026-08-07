class Solution {
    private static final Map<Integer, Map<Integer, Integer>> F = Map.of(
        0, Map.of(),
        1, Map.of(),
        2, Map.of(2, 1),
        3, Map.of(3, 1),
        4, Map.of(2, 2),
        5, Map.of(5, 1),
        6, Map.of(2, 1, 3, 1),
        7, Map.of(7, 1),
        8, Map.of(2, 3),
        9, Map.of(3, 2)
    );

    public String smallestNumber(String num, long t) {
        Map<Integer, Integer> need = factor(t);

        if (need == null)
            return "-1";

        Map<Integer, Integer> fc = factors(need);

        if (sum(fc) > num.length())
            return build(fc);

        Map<Integer, Integer> cur = count(num);

        int zero = num.indexOf('0');

        if (zero == -1 && subset(need, cur))
            return num;

        if (zero == -1)
            zero = num.length();

        for (int i = num.length() - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';

            cur = sub(cur, F.get(d));

            if (i > zero)
                continue;

            int space = num.length() - 1 - i;

            for (int nd = d + 1; nd <= 9; nd++) {
                Map<Integer, Integer> r =
                    sub(sub(need, cur), F.get(nd));

                Map<Integer, Integer> f = factors(r);

                if (sum(f) <= space) {
                    int ones = space - sum(f);

                    return num.substring(0, i)
                         + nd
                         + "1".repeat(ones)
                         + build(f);
                }
            }
        }

        fc = factors(need);

        return "1".repeat(num.length() + 1 - sum(fc))
             + build(fc);
    }

    private Map<Integer, Integer> factor(long t) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(2, 0);
        m.put(3, 0);
        m.put(5, 0);
        m.put(7, 0);

        int[] p = {2, 3, 5, 7};

        for (int x : p) {
            while (t % x == 0) {
                t /= x;
                m.put(x, m.get(x) + 1);
            }
        }

        return t == 1 ? m : null;
    }

    private Map<Integer, Integer> count(String s) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(2, 0);
        m.put(3, 0);
        m.put(5, 0);
        m.put(7, 0);

        for (char c : s.toCharArray()) {
            for (Map.Entry<Integer, Integer> e :
                    F.get(c - '0').entrySet()) {
                m.put(e.getKey(),
                      m.get(e.getKey()) + e.getValue());
            }
        }

        return m;
    }

    private Map<Integer, Integer> factors(Map<Integer, Integer> c) {
        int c2 = c.get(2);
        int c3 = c.get(3);

        int c8 = c2 / 3;
        c2 %= 3;

        int c9 = c3 / 2;
        c3 %= 2;

        int c4 = c2 / 2;
        c2 %= 2;

        int c6 = 0;

        if (c2 == 1 && c3 == 1) {
            c2 = 0;
            c3 = 0;
            c6 = 1;
        }

        if (c3 == 1 && c4 == 1) {
            c2 = 1;
            c3 = 0;
            c4 = 0;
            c6 = 1;
        }

        Map<Integer, Integer> r = new HashMap<>();

        r.put(2, c2);
        r.put(3, c3);
        r.put(4, c4);
        r.put(5, c.get(5));
        r.put(6, c6);
        r.put(7, c.get(7));
        r.put(8, c8);
        r.put(9, c9);

        return r;
    }

    private Map<Integer, Integer> sub(
            Map<Integer, Integer> a,
            Map<Integer, Integer> b) {

        Map<Integer, Integer> r = new HashMap<>(a);

        for (Map.Entry<Integer, Integer> e : b.entrySet()) {
            int k = e.getKey();
            r.put(k, Math.max(0, r.get(k) - e.getValue()));
        }

        return r;
    }

    private boolean subset(
            Map<Integer, Integer> a,
            Map<Integer, Integer> b) {

        for (int p : a.keySet()) {
            if (b.get(p) < a.get(p))
                return false;
        }

        return true;
    }

    private int sum(Map<Integer, Integer> m) {
        int s = 0;

        for (int x : m.values())
            s += x;

        return s;
    }

    private String build(Map<Integer, Integer> m) {
        StringBuilder s = new StringBuilder();

        for (int d = 2; d <= 9; d++)
            s.append(String.valueOf(d).repeat(m.get(d)));

        return s.toString();
    }
}
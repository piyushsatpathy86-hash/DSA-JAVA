class Solution {
    class Node {
        int l, r, size, lmx, rmx, mx;
        char lc, rc;
    }

    private String s;
    private Node[] tr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s;
        int n = s.length();
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; i++) tr[i] = new Node();
        build(1, 1, n);

        int k = queryCharacters.length();
        int[] ans = new int[k];
        char[] arr = s.toCharArray();
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i] + 1; // 1-indexed
            char c = queryCharacters.charAt(i);
            arr[idx - 1] = c;
            modify(1, idx, c);
            ans[i] = tr[1].mx;
        }
        return ans;
    }

    private void build(int u, int l, int r) {
        tr[u].l = l; tr[u].r = r;
        if (l == r) {
            tr[u].lmx = tr[u].rmx = tr[u].mx = tr[u].size = 1;
            tr[u].lc = tr[u].rc = s.charAt(l - 1);
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    private void modify(int u, int x, char v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].lc = v;
            tr[u].rc = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) modify(u << 1, x, v);
        else modify(u << 1 | 1, x, v);
        pushup(u);
    }

    private void pushup(int u) {
        Node left = tr[u << 1];
        Node right = tr[u << 1 | 1];
        Node root = tr[u];

        root.size = left.size + right.size;
        root.lc = left.lc;
        root.rc = right.rc;

        root.lmx = left.lmx;
        if (left.lmx == left.size && left.rc == right.lc) {
            root.lmx += right.lmx;
        }

        root.rmx = right.rmx;
        if (right.rmx == right.size && right.lc == left.rc) {
            root.rmx += left.rmx;
        }

        root.mx = Math.max(left.mx, right.mx);
        if (left.rc == right.lc) {
            root.mx = Math.max(root.mx, left.rmx + right.lmx);
        }
    }
}
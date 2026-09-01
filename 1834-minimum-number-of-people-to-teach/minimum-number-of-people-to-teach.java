class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length; // users indexed 1..m
        Set<Integer>[] userLang = new HashSet[m + 1];
        for (int i = 1; i <= m; i++) {
            userLang[i] = new HashSet<>();
            for (int lang : languages[i - 1]) {
                userLang[i].add(lang);
            }
        }

        // Step 1: find users involved in "no common language" friendships
        Set<Integer> needTeach = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0], v = f[1];
            boolean common = false;
            for (int lang : userLang[u]) {
                if (userLang[v].contains(lang)) {
                    common = true;
                    break;
                }
            }
            if (!common) {
                needTeach.add(u);
                needTeach.add(v);
            }
        }

        if (needTeach.isEmpty()) return 0;

        // Step 2: try teaching each language, find min cost
        int minTeach = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int cnt = 0;
            for (int user : needTeach) {
                if (!userLang[user].contains(lang)) cnt++;
            }
            minTeach = Math.min(minTeach, cnt);
        }

        return minTeach;
    }
}
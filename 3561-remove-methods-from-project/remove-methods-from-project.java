class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invalidMethods) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : invalidMethods) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
        }
        
        Set<Integer> suspicious = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(k);
        suspicious.add(k);
        
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!suspicious.contains(next)) {
                    suspicious.add(next);
                    stack.push(next);
                }
            }
        }
        
        for (int[] edge : invalidMethods) {
            if (!suspicious.contains(edge[0]) && suspicious.contains(edge[1])) {
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) all.add(i);
                return all;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious.contains(i)) result.add(i);
        }
        return result;
    }
}
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        if (s.length() < totalLen) return result;

        // Frequency map of the words we need
        Map<String, Integer> need = new HashMap<>();
        for (String w : words) {
            need.put(w, need.getOrDefault(w, 0) + 1);
        }

        // Try each possible alignment offset (0 .. wordLen-1)
        for (int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int matched = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (need.containsKey(word)) {
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    matched++;

                    // Too many copies of this word: shrink from the left
                    while (window.get(word) > need.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        matched--;
                        left += wordLen;
                    }

                    // Window contains exactly all the words
                    if (matched == wordCount) {
                        result.add(left);

                        // Slide left by one word to look for the next match
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        matched--;
                        left += wordLen;
                    }
                } else {
                    // Invalid word: reset the window
                    window.clear();
                    matched = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }
}
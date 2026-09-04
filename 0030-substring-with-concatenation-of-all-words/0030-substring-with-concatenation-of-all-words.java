class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
      List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return res;

        int w = words[0].length();
        int wordCount = words.length;
        int n = w * wordCount;
        if (n > s.length()) return res;
        
        // Target map of expected word frequencies
        Map<String, Integer> map = new HashMap<>();
        for (String e : words) map.put(e, map.getOrDefault(e, 0) + 1);

        // Run the window starting at different offsets (0 to w-1)
        for (int i = 0; i < w; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentMap = new HashMap<>();
            int matchedWords = 0;

            // Slide the window to the right by step size 'w'
            while (right + w <= s.length()) {
                String ss = s.substring(right, right + w);
                right += w;

                if (map.containsKey(ss)) {
                    currentMap.put(ss, currentMap.getOrDefault(ss, 0) + 1);
                    matchedWords++;

                    // If we have too many copies of 'ss', shrink the window from the left
                    while (currentMap.get(ss) > map.get(ss)) {
                        String leftWord = s.substring(left, left + w);
                        currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                        matchedWords--;
                        left += w;
                    }

                    // Found a complete valid concatenation
                    if (matchedWords == wordCount) {
                        res.add(left);
                    }
                } else {
                    // Not a valid word: wipe the window tracking clean and jump 'left' forward
                    currentMap.clear();
                    matchedWords = 0;
                    left = right;
                }
            }
        }
        return res;
         
    }
}
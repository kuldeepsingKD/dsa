class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int i = 0;

        int ones = 0;
        int minLen = Integer.MAX_VALUE;
        String bestSubstring = "";

        for (int j = 0; j < n; j++) {
            if (s.charAt(j) == '1') {
                ones++;
            }

            while (ones == k) {

                if (s.charAt(i) == '1') {
                    int currLen = j - i + 1;
                    String currSub = s.substring(i, j + 1);

                    if (currLen < minLen) {
                        minLen = currLen;
                        bestSubstring = currSub;

                    } else if (currLen == minLen) {
                        if (currSub.compareTo(bestSubstring) < 0) {
                            bestSubstring = currSub;
                        }
                    }

                    ones--;

                }

                i++;

            }

        }

        return bestSubstring;

    }
}
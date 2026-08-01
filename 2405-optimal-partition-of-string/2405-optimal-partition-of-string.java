class Solution {
    public int partitionString(String s) {
           int[] lastSeen = new int[26];

        for (int i = 0; i < 26; i++) {
            lastSeen[i] = -1;
        }

        int count = 0;
        int substringStart = 0;

        for (int i = 0; i < s.length(); i++) {

            int idx = s.charAt(i) - 'a';

            if (lastSeen[idx] >= substringStart) {
                count++;
                substringStart = i;
            }

            lastSeen[idx] = i;
        }

        return count + 1;
    }
}
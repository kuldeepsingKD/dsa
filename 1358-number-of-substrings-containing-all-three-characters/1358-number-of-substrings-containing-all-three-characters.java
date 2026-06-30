class Solution {
    public int numberOfSubstrings(String s) {
        int[] p = {-1, -1, -1};
        int total = 0;

        for(int i = 0; i < s.length(); i++){
            p[s.charAt(i) - 'a'] = i;
            total += Math.min(p[0], Math.min(p[1], p[2])) + 1;
        }

        return total;
    }
}
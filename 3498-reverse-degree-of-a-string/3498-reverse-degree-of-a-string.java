class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        for(int i = 0; i < s.length(); i++) {
            int reversed = 26 - (s.charAt(i) - 'a');

            ans += reversed * (i + 1);
        }

        return ans;
    }
}
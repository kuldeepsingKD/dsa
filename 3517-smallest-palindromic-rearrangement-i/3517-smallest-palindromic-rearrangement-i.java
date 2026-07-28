class Solution {
    public String smallestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int mid = n/2;

        Arrays.sort(chars, 0, mid);

        for(int i = 0; i < mid; i++) {
            chars[n-1-i] = chars[i];

        }

        return new String(chars);
    }
}
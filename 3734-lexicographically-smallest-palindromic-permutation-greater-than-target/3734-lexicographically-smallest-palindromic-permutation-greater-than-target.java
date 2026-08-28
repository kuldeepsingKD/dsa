class Solution {
    String result = "";
    char midChar = '$';
    int halflen = 0;

    boolean solve(StringBuilder curr, int[] count, String target, int i, boolean greater) {
        if (i == halflen) {
            String leftHalf = curr.toString(); // left half
            String rightHalf = new StringBuilder(leftHalf)
                    .reverse().toString();

            String candidate = leftHalf;
            if (midChar != '$')
                candidate += midChar; // mid character
            candidate += rightHalf;

               if (candidate.compareTo(target) > 0) {                   // strictly greater
                result = candidate;
                return true;
            }

            return false;
        }
          for (char ch = 'a'; ch <= 'z'; ch++) {
            if (count[ch - 'a'] == 0)
                continue;

            if (!greater && ch < target.charAt(i))
                continue;

            curr.append(ch);
            count[ch - 'a']--;

            boolean isGreater = greater || ch > target.charAt(i);

            if (solve(curr, count, target, i + 1, isGreater))
                return true;

            curr.deleteCharAt(curr.length() - 1);
            count[ch - 'a']++;
        }

        return false;
        }
    

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char ch : s.toCharArray())
            count[ch - 'a']++;

        int oddcount = 0;
        for (int c = 0; c < 26; c++) {
            if (count[c] % 2 == 1) {
                oddcount++;
                midChar = (char)(c + 'a');
            }
        }

        if (oddcount > 1)
            return "";

        for (int c = 0; c < 26; c++) {
            count[c] /= 2;
        }

        halflen = n / 2;

        StringBuilder curr = new StringBuilder();

        solve(curr, count, target, 0, false);

        return result;
    }
}
class Solution {
    private boolean isSorted(String s) {
        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) > s.charAt(i+1)) {
                return false;
            }
        }

        return true;
    }
    public int minOperations(String s) {
        int n = s.length();

        if(isSorted(s)) {
            return 0;
        }

        if(n == 2) {
            return -1;
        }


        char min = 'z' + 1;
        char max = 0;

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c < min) min = c;
            if(c > max) max = c;
        }

        if(s.charAt(0) == min || s.charAt(n-1) == max) {
            return 1;
        }

        char maxMid = 0;
        char minMid = 'z' + 1;

        for(int i = 1; i < n-1; i++) {
            char c = s.charAt(i);

            if(c > maxMid) maxMid = c;
            if(c < minMid) minMid = c; 
        }

        if(s.charAt(0) > maxMid && s.charAt(n-1) < minMid) {
            return 3;
        }

        return 2;


    }
}
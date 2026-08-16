class Solution {
    public int maximumGap(String skill, String station) {
        int m = skill.length();
        int n = station.length();

        int[] left = new int[m];
        int[] right = new int[m];

        // Find earliest positions
        int j = 0;

        for (int i = 0; i < m; i++) {

            while (station.charAt(j) != skill.charAt(i)) {
                j++;
            }

            left[i] = j;
            j++;
        }

        // Find latest positions
        j = n - 1;

        for (int i = m - 1; i >= 0; i--) {

            while (station.charAt(j) != skill.charAt(i)) {
                j--;
            }

            right[i] = j;
            j--;
        }
        int ans = 0;

        // Gap between consecutive characters of skill
        for (int i = 1; i < m ; i++) {

            int gap = right[i] - left[i-1] ;

            ans = Math.max(ans, gap);
        }

        return ans;
    

    }
}
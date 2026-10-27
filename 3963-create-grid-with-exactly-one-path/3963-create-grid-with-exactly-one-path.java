class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        Arrays.fill(ans, "#".repeat(n-1) + ".");
        ans[0] = ".".repeat(n);
        return ans;
    }
}
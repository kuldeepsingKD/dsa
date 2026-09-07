class Solution {

    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int total = 0;
        int[] end = new int[26];

        for(char c : s.toCharArray()) {
            int index = c - 'a';

            int oldtotal = total;
            int newSubseq = (oldtotal + 1 - end[index] + MOD) % MOD;
            total = (total + newSubseq) % MOD;
            end[index] = (end[index] + newSubseq) % MOD;
        }
        return total;
    }
}
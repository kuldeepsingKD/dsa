class Solution {
    public int binExp(int a, int b, int m) {
        a %= m;
        int ans = 1;
        while(b > 0) {
            if((b&1) == 1) {
                ans = (int)(ans * 1L * a) % m;
            }
            a = (int)(a*1L*a) % m;
            b >>= 1;
        }

        return ans;

    }
    public int superPow(int a, int[] b) {
        int bmod = 0;
        for(int val : b) {
            bmod = (bmod * 10 + val) % 1140;
        }

        if (bmod == 0) bmod = 1140;

        return binExp(a, bmod, 1337);
    }
}
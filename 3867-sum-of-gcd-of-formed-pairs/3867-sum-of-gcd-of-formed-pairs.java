class Solution {
    private int gcd(int a, int b){
        while(b != 0) {
            int temp = a%b;
            a = b;
            b = temp;

        }
        return a;
    }
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int max = 0;
        List<Integer> prefixGcd = new ArrayList<>(n);
        
        for(int i = 0; i < n; i++) {
            max = Math.max(nums[i], max);
           int x = gcd(nums[i], max);
           prefixGcd.add(x);
        }

        Collections.sort(prefixGcd);
        int l = 0,  r = n-1;
         long ans = 0;
         while(l < r) {
            ans += gcd(prefixGcd.get(l), prefixGcd.get(r));
            l++;
            r--;
         }

         return ans;
    }
}
class Solution {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        long sum = 0;
        int[] arr = new int[100];

        for(int i = 0; i < n; i++) {
            int num1 = nums[i];
            int c = 0;
            while(num1 > 0) {
                int d1 = num1 %10;
                num1 = num1/10;
                sum += i - arr[d1+(c*10)];
                arr[d1+(c*10)]++;
                c++;
            }
        }

        return sum;

    } 
}
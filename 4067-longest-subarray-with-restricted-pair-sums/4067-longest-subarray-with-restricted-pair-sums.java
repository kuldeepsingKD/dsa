class Solution {
    public boolean isInvalid(int x, int[] count) {
        // case 1 : a+b = x;
        for(int a = 1; a < x; a++) {
            int b = x - a;
            if(a == b) {
                if(count[a] >= 2) {
                    return true;
                }
            }else{
                if(count[a] > 0 && count[b] > 0) {
                    return true;
                }
            }
        }

        // case 2: x+a = b;

        for(int a = 1; a+x <= 500; a++) {
            int b = x+a;
            if(count[a] > 0 && count[b] > 0) {
                if(a == x && count[x] < 2){
                    continue;
                }
                return true;
            }
        }

        return false;
    }
    public int maxSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int ans = 1;
        int[] count = new int[501];

        for(int right = 0; right < n; right++) {
            int x = nums[right];
            count[x]++;

            while(isInvalid(x, count)) {
                count[nums[left]]--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }

        return ans;

    }
}
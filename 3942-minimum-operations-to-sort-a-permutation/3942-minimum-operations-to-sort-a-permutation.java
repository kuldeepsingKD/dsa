class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int zeroIdx = -1;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }

        boolean isRightSorted = true;
        for(int i = 0; i < n; i++) {
            if(nums[(zeroIdx + i) % n] != i) {
                isRightSorted = false;
                break;
            }
        }

        boolean isLeftSorted = true;
        for(int i = 0; i < n; i++) {
              int targetIdx = (zeroIdx - i % n + n) % n;
            if(nums[targetIdx] != i) {
                isLeftSorted = false;
                break;
            }
        }

        if(!isRightSorted && !isLeftSorted) {
            return -1;

        }

        int minOps = Integer.MAX_VALUE;

        if(isRightSorted) {
            int path1 = zeroIdx;

            int path2 =  2 + (n - zeroIdx); 
           minOps = Math.min(minOps, Math.min(path1, path2));
        }


        if(isLeftSorted) {
            int path3 = n - zeroIdx; 

            int path4 = 1 + ((zeroIdx + 1) % n);

            minOps = Math.min(minOps, Math.min(path3, path4));
        }


        return minOps;
        


    }
}
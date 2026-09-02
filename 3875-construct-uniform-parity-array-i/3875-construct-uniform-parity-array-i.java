class Solution {
    public boolean uniformArray(int[] nums1) {
        int n =nums1.length;
        int oddCount = 0, evenCount = 0;

        for(int num : nums1) {
            if(num %2 ==0) evenCount++;
            else oddCount++;
        }

        boolean allOddPossible = oddCount >= 1;
        boolean allEvenPossible = oddCount == 0 || oddCount >= 2;

        return allOddPossible || allEvenPossible;
    }
}
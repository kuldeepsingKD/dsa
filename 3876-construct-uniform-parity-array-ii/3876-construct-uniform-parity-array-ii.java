class Solution {
    public boolean uniformArray(int[] nums1) {
       int[] sorted = nums1.clone();
        Arrays.sort(sorted);

        return canMakeAllOdd(sorted) || canMakeAllEven(sorted);
    }

    private boolean canMakeAllOdd(int[] sorted) {
        boolean smallerOddExists  =false;
        for(int num : sorted) {
            if(num%2 == 1) {
                smallerOddExists = true;
                
            } else{
                if(!smallerOddExists) return false;
            }
        }
        return true;
    }
     private boolean canMakeAllEven(int[] sorted) {
        boolean smallerOddExists  =false;
        for(int num : sorted) {
            if(num%2 == 1) {
               if(!smallerOddExists) return false;
                smallerOddExists = true;
                
                
            }  
        }
        return true;
     }
}
class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;

        int[] required = new int[n];

        for(int i = 0; i < n; i++) {
            int currently_rock = rocks[i];
            int capacity_rock = capacity[i];

            int required_rock = capacity_rock - currently_rock;

            required[i] = required_rock;
        }

        Arrays.sort(required);
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(required[i] == 0) {
                count++;
            }else if(additionalRocks >= required[i]){
                 additionalRocks -= required[i];
                 count++;
            }else{
                break;
            }
        }

        return count;
    }
}
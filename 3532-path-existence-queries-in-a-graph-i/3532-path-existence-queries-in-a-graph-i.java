class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] group =new int[n];
        int currentGroup = 0;


        for(int i = 1; i < n; i++) {
            if(nums[i] - nums[i-1] > maxDiff) {
                currentGroup++;
            }

            group[i] = currentGroup;

        }

        boolean[] qa = new boolean[queries.length];
        for(int i=0; i<queries.length; i++){
            qa[i] = (group[queries[i][0]] == group[queries[i][1]]);
        }

        return qa;
    }
}
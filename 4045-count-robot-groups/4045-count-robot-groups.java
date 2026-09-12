class Solution {
    public int countGroups(int[] position, int[] speed, int distance) {
        int groups = 0;
        int n = position.length;

        int rightPos = Integer.MAX_VALUE;
        int rightSpeed = Integer.MAX_VALUE;

        for(int i = n - 1; i >= 0; i-- ) {
            int currPos = position[i];
            int currSpeed = speed[i];

            if(rightPos - currPos > distance && currSpeed <= rightSpeed) {
                groups++;
                rightSpeed = currSpeed;
            }

            rightPos = currPos;
        }

        return groups;
    }
}
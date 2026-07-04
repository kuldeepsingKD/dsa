class Solution {
       private int binarySearchUpperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if(arr[mid] > target) {
                result = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return result == -1 ? arr.length : result;
    }

     private int binarySearchLowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if(arr[mid] < target) {
                left = mid+1;
            } else {
                result = mid;
                right = mid-1;
            }
            
        }
  
        return result == -1 ? arr.length : result;
    }
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int m = flowers.length;
        int n = people.length;

        int[] start = new int[m];
        int[] end = new int[m];

        for(int i = 0; i<m; i++) {
            start[i] = flowers[i][0];
            end[i] = flowers[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int[] result = new int[n];

        for(int i=0; i<n; i++) {
            int time = people[i];

            int bloomed_flowered_already = binarySearchUpperBound(start, time);
            int died_already = binarySearchLowerBound(end, time);

            result[i] = bloomed_flowered_already - died_already;
        }

        return result;
    }
}
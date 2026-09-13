class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;


        for(int i = 0; i < n; i++) {
            for(int j = i +1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0; i < n; i++) {
            int left = 0;
            int right = n -1;

            while(left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }


    }
    public boolean isEquals(int[][] mat, int[][] target) {
        int n = mat.length;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }

        return true;

    }
    public boolean findRotation(int[][] mat, int[][] target) {
        for(int k = 0 ; k < 4; k++) {
            if(isEquals(mat, target)) {
                return true;
            }
            rotate(mat);

        }

        return false;
    }
}
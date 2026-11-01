class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] temp = new int[m][n];
        for(int i = 0; i < m; i++) {
            temp[i] = matrix[i].clone();
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {


                    for(int k = 0; k < n; k++) {
                        temp[i][k] = 0;
                    }

                    for(int k = 0; k < m; k++) {
                        temp[k][j] = 0;
                    }
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }
}
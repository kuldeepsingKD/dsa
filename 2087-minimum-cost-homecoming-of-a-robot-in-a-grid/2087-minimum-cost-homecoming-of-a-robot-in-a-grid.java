class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        //Moves in row

        int r1 = startPos[0], r2 = homePos[0];
        int result = 0;

        if(r2 >= r1) {
            for(int r = r1 + 1; r <= r2; r++) {
                result += rowCosts[r];
            }
        }else {
            for(int r = r1-1; r >= r2; r--){
                result += rowCosts[r];
            }
        }

        //moves in column

        int c1 = startPos[1], c2 = homePos[1];

        if(c1 <= c2) {
            for(int c = c1+1; c <= c2; c++) {
                result += colCosts[c];
            }
        }else{
            for(int c = c1-1; c >= c2; c--) {
                result += colCosts[c];
            }
        }

        return result;
    }
}
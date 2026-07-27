class Solution {
    private boolean check(int[][] squares, double mid, double total) {
        double bot_area = 0.00;

        for(int[] square : squares){

            double y = square[1];
            double l = square[2];

            double top_y = y +l;
            double bot_y = y;

            if(mid >= top_y) {
                bot_area += l*l;
            }else if(mid > bot_y){
                bot_area += l *(mid - bot_y);
            }

        }

        return bot_area >= total/2.0;
    }
    public double separateSquares(int[][] squares) {
        double low = Integer.MAX_VALUE;
        double high = Integer.MIN_VALUE;
        double total = 0.0000;

        for(int[] square : squares) {
           double  x = square[0];
           double  y = square[1];
          double l = square[2];
            total += l * l;

            low = Math.min(low, y);
            high = Math.max(high, y+l);
        }

        double result = 0.00;

        while(high - low >= 1e-5){
            double mid = low + (high - low)/2;

               result = mid;

            if(check(squares, mid, total)){
                 
                high = mid;

            }else {
                low = mid;
            }
        } 

        return result;
    }
}
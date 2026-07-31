class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int total = 0;

        for(int cost : costs) {
            if(cost > coins){
                return total;
            }else{
                total++;
                coins -= cost;
            }
        }

        return total;
    }
}
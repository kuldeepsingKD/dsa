class Solution {
    public double minPrice(int[] prices, int[] discounts) {
        Arrays.sort(prices);
        Arrays.sort(discounts);

        double totalCost = 0.0;
        int i = prices.length - 1;
        int j = discounts.length - 1;

        while(i >= 0) {
            if(j >=0) {
                totalCost += prices[i] * (100.0 - discounts[j])/100.0;
                j--;
            }else{
                totalCost += prices[i];
            }
               i--;
        }
       return totalCost;
    }
}
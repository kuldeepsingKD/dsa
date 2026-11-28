class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
         int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        
        // Step 1: Find the maximum factor and the overall cheapest item price
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }
        
        // Step 2: Count frequencies of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }
        
        // Step 3: Use a Sieve-like approach to count how many items have a factor 
        // that is a multiple of f (including f itself)
        int[] multiplesCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] > 0) {
                for (int m = f; m <= maxFactor; m += f) {
                    multiplesCount[f] += freq[m];
                }
            }
        }
        
        // Step 4: Group profitable 2-for-1 deals by their purchase price
        // TreeMap keeps prices sorted from cheapest to most expensive
        TreeMap<Integer, Long> dealsByPrice = new TreeMap<>();
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            
            // Only buy for the freebie if getting 2 items here beats buying minPrice twice
            if (price < 2 * minPrice) {
                // Subtract 1 to exclude the item itself (i != j)
                long freeItemsAvailable = multiplesCount[factor] - 1;
                if (freeItemsAvailable > 0) {
                    dealsByPrice.put(price, dealsByPrice.getOrDefault(price, 0L) + freeItemsAvailable);
                }
            }
        }
        
        // Step 5: Greedily buy the cheapest 2-for-1 deals first
        long totalItems = 0;
        for (Map.Entry<Integer, Long> entry : dealsByPrice.entrySet()) {
            int price = entry.getKey();
            long availableDeals = entry.getValue();
            
            if (budget < price) {
                break;
            }
            
            long canBuy = Math.min(availableDeals, (long) budget / price);
            budget -= canBuy * price;
            totalItems += canBuy * 2;
        }
        
        // Step 6: Spend any leftover budget on the single cheapest item
        totalItems += budget / minPrice;
        
        return (int) totalItems;

    }
}
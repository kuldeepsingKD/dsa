class Solution {
    public int maxProduct(int n) {
        int[] arr = String.valueOf(n).chars().map(c -> c - '0').toArray();
        int a = arr.length;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        int maxProduct = max1 * max2;

        return maxProduct;

    }
}
class Solution {
    public int maxProduct(int n) {
        int[] arr = String.valueOf(n).chars().map(c -> c - '0').toArray();
        int a = arr.length;
        Arrays.sort(arr);
        int maxProduct = arr[a-1] * arr[a-2];

    

return maxProduct;


    }
}
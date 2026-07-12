class Solution {
    public int[] arrayRankTransform(int[] arr) {
         int[] temp = arr.clone();
        Arrays.sort(temp);

        Map<Integer, Integer> rank = new HashMap<>();
        int r = 1;

        for (int x : temp) {
            if (!rank.containsKey(x)) {
                rank.put(x, r++);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rank.get(arr[i]);
        }

        return arr;
    }
}
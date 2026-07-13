class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String s = "123456789";
        List<Integer> ans = new ArrayList<>();

        for(int len = 2; len <= 9; len++) {
            for(int start = 0; start <= s.length() - len; start++) {
                int digit = Integer.parseInt(s.substring(start, start+len));

                if(digit >= low && digit <= high) {
                    ans.add(digit);
                }
            }
        }

        return ans;
    }
}
class SummaryRanges {
    private TreeSet<Integer> set;

    public SummaryRanges() {
        set = new TreeSet<>();
    }
    
    public void addNum(int value) {
        set.add(value);
    }
    
    public int[][] getIntervals() {
        List<Integer> nums = new ArrayList<>(set);
        List<int[]> result = new ArrayList<>();

        int n = nums.size();

        for(int i = 0; i < n; i++) {
            int start = nums.get(i);

            while(i < n-1 && nums.get(i) +1 == nums.get(i+1)){
                i++;
            }

            result.add(new int[]{start, nums.get(i)});
        }
         return result.toArray(new int[result.size()][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
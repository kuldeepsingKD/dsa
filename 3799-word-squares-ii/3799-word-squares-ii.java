class Solution {
    public List<List<String>> wordSquares(String[] words) {
        int n = words.length;

        List<List<String>> res = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            String top = words[i];

            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                    String left = words[j];

                    for(int k = 0; k < n; k++) {
                        if(k == i || k == j) continue;
                        String right = words[k];

                        for(int l = 0; l < n; l++) {
                            if(l == i || l == j || l == k) continue;
                            String bottom = words[l];

                            if(top.charAt(0) == left.charAt(0) && top.charAt(3) == right.charAt(0) && bottom.charAt(0) == left.charAt(3) && bottom.charAt(3) == right.charAt(3)) {
                                res.add(Arrays.asList(top, left, right, bottom));
                            }
                        }
                    }
                
            }
        }

        Collections.sort(res, (a,b) -> {
            for(int i = 0; i < 4; i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if(cmp != 0) return cmp;
             }
             return 0;
        });

        return res;












        
    }
}
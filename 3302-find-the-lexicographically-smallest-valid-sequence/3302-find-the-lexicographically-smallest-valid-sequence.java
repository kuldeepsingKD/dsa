class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        Arrays.fill(last, -1);

        int i = n -1;
        int j = m-1;

        while(j >=0 && i >= 0) {
            if(word1.charAt(i) == word2.charAt(j)){
                last[j] = i;
                j--;
            }
            i--;
        }

        int[] res = new int[m];
        boolean changed = false;
         j = 0;

        for( i=0; i<n; i++) {
            if(j == m) {
                break;
            }

            if(word1.charAt(i) == word2.charAt(j)) {
                res[j] = i;
                j++;
            }else if(!changed && (j == m-1 || last[j+1] > i)) {
                res[j] = i;
                j++;
                changed = true;
            }
        }

         return j == m ? res : new int[0];
        
    }
}
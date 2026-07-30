class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int n = tokens.length;
        int maxScore = 0;
        int score = 0;

        int i = 0, j = n-1;

        while(i <= j) {
            if(power >= tokens[i]) {
                power -= tokens[i];
                score += 1;
                i++; 

                maxScore = Math.max(maxScore, score);
            }else if(score >= 1) {
                power += tokens[j];
                score -= 1;
                j--;
            }else{
                return maxScore;
            }
        }


        return maxScore;

        
    }
}
class Solution {
    public int minimumPushes(String word) {
        int [] mp = new int[26];
        for(char ch : word.toCharArray()){
            mp[ch - 'a']++;
        }
         Integer[] mpInteger = Arrays.stream(mp).boxed().toArray(Integer[]::new);
        Arrays.sort(mpInteger, Comparator.reverseOrder());


        int result = 0;

        for(int i =0; i<26; i++){
            int freq = mpInteger[i];
            int press = i/8 +1;
            result += freq *press; 
        }

        return result;
        
        
    }
}
class Solution {
    public int minimumPushes(String word) {
        int result = 0;
        Map<Integer,Integer> mp = new HashMap<>();
        int assign_key = 2;


        for(char ch : word.toCharArray()){
           if(assign_key > 9){
            assign_key = 2;
           }
           mp.put(assign_key, mp.getOrDefault(assign_key, 0) + 1);
           result +=  mp.getOrDefault(assign_key, 0);
           assign_key++;
        }
        return result;
    }
}
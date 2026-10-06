class Solution {
    public int minAddToMakeValid(String s) {
        int size = 0;
        int open = 0;
        int n = s.length();

        for(int i=0; i<n; i++){
            if(s.charAt(i) == '('){
                size++;
            }else if(size>0){
                size--;
            }else{
                open++;
            }
        }

        return open+size;
    }
}
class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                st.push(0);
            }else{
                int top = st.pop();
                int A = Math.max(2*top, 1);
                int B = st.pop();
                st.push(A+B);
            }
        }

        return st.pop();
    }
}
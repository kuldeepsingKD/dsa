class Solution {
    Set<String> set = new HashSet<>();
    int n;
    int maxLen;

    public void solve(String s, int i, StringBuilder curr, int count) {
        if(count < 0) return;

        if(i == n) {
            if(count == 0) {
                if(curr.length() > maxLen) {
                    maxLen = curr.length();
                    set.clear();
                }

                if(curr.length() == maxLen) {
                    set.add(curr.toString());
                }
            }
            return;
        }

        char c = s.charAt(i);

        if(c != '(' && c != ')') {
            curr.append(c);
            solve( s, i+1, curr, count);
            curr.deleteCharAt(curr.length() - 1);
            return;
        }

        curr.append(c);
        solve(s, i+1, curr, count + (c == '(' ? 1 : -1) );
        curr.deleteCharAt(curr.length() - 1);
        solve(s, i+1, curr, count);
    }


    public List<String> removeInvalidParentheses(String s) {
        n = s.length();
        maxLen = 0;
        set.clear();

        solve(s, 0, new StringBuilder(), 0);

        return new ArrayList<>(set);
    }
}
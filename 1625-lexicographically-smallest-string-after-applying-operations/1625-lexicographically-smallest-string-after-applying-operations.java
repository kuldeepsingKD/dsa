class Solution {
    private String rotate(String s, int b) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        reverseRange(sb, 0, b - 1);
        reverseRange(sb, b, sb.length() - 1);
        return sb.toString();
    }

    private void reverseRange(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }
    public String findLexSmallestString(String s, int a, int b) {
        Set<String> visited = new HashSet<>();
        Queue<String> que = new LinkedList<>();
        String smallestString = s;

        que.add(s);
        visited.add(s);

        while(!que.isEmpty()) {
            String curr = que.poll();

            if(curr.compareTo(smallestString) < 0) {
                smallestString = curr;
            }

            char[] added = curr.toCharArray();
            for(int i=1; i < added.length; i +=2) {
                int newDigit = ((added[i] - '0') + a) % 10;
                added[i] = (char)('0' + newDigit);
            }

            String addedStr = new String(added);
            if(visited.add(addedStr)) {
                que.add(addedStr);
            }

             String rotated = rotate(curr, b);
            if (visited.add(rotated)) {
                que.add(rotated);
            }
        }

        return smallestString;
    }
}
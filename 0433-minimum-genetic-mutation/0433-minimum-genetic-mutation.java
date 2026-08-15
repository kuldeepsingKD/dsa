class Solution {
    public int minMutation(String start, String end, String[] bank) {
       Set<String> bankset = new HashSet<>(Arrays.asList(bank));

       Set<String> visited = new HashSet<>();
       Queue<String> que = new LinkedList<>();

       que.offer(start);
       visited.add(start);

       int level = 0;
       char[] genes = {'A', 'C', 'G', 'T'};

       while(!que.isEmpty()) {
        int n = que.size();

        while(n-- > 0) {
            String curr = que.poll();

            if(curr.equals(end)) {
                return level;
            }

            for(char ch : genes) {
                for(int i=0; i < curr.length(); i++) {
                    char[] chars = curr.toCharArray();
                    chars[i] = ch;
                    String neighbours = new String(chars);

                    if(!visited.contains(neighbours) && bankset.contains(neighbours)) {
                        visited.add(neighbours);
                        que.offer(neighbours);
                    }

                }
            }
        }
        level++;
       }
       return -1;
    }
}
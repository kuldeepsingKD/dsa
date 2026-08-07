class Solution {
     class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py)
                return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        HashMap<String,Integer> emailToaccount = new HashMap<>();
        DSU dsu = new DSU(n);

        for(int i = 0; i <n; i++) {
            List<String> account = accounts.get(i);
            for(int j =1; j < account.size(); j++) {
                String email = account.get(j);

                if(!emailToaccount.containsKey(email)){
                    emailToaccount.put(email,i);
                }else{
                    dsu.union(i, emailToaccount.get(email));
                }
            }
        }

        Map<Integer, TreeSet<String>> groups = new HashMap<>();

        for(Map.Entry<String, Integer> entry : emailToaccount.entrySet()) {
            String email = entry.getKey();
            int accountId = entry.getValue();
            int root = dsu.find(accountId);

            groups.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }

           List<List<String>> ans = new ArrayList<>();

        for (Map.Entry<Integer, TreeSet<String>> entry : groups.entrySet()) {

            int root = entry.getKey();

            List<String> list = new ArrayList<>();

            // Name comes from any account in the component; using root is fine.
            list.add(accounts.get(root).get(0));

            list.addAll(entry.getValue());

            ans.add(list);
        }

        return ans;
    }
}
class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;

        int q = queries.length;

        Arrays.sort(logs, (a,b) -> a[1] - b[1]);

        int[][] timeIndex = new int[q][2];

        for(int i = 0; i < q; i++) {
            timeIndex[i][0] = queries[i];
            timeIndex[i][1] = i;
        }

        Arrays.sort(timeIndex, (a,b) -> a[0] - b[0]);

        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[q];

        int i = 0, j = 0;
        for(int[] pair : timeIndex) {
            int currTime = pair[0];
            int index = pair[1];

            int start = currTime - x;
            int end = currTime;

            while(j < m && logs[j][1] <= end) {
                int serverId = logs[j][0];
                map.put(serverId, map.getOrDefault(serverId, 0) + 1);
                j++;

            }

            while(i < m && logs[i][1] < start) {
                   int serverId = logs[i][0];
                map.put(serverId, map.get(serverId) - 1);
                 if (map.get(serverId) == 0) {
                    map.remove(serverId);
                }
                i++;
            }

            result[index] = n - map.size();
        }

        return result;
    }
}
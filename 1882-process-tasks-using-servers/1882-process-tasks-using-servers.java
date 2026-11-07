class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

         int[] ans = new int[m];

        // 1. Free servers: [weight, index]
        // Weight chhota pehle; agar weight same ho toh index chhota pehle
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // 2. Busy servers: [freeTime, weight, index]
        // Sabse jaldi free hone wala server top par rahega
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });

        // Step 1: Saare servers ko freeServers mein add karein
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        int time = 0;

        // Step 2: Har task ko process karein
        for (int i = 0; i < m; i++) {
            // Current time kam se kam task ke arrival time 'i' ke barabar hona chahiye
            time = Math.max(time, i);

            // Agar koi server free nahi hai, toh time ko next free server ke time par jump karein
            if (freeServers.isEmpty()) {
                time = busyServers.peek()[0];
            }

            // Jo servers current 'time' tak free ho chuke hain, unhe freeServers mein shift karein
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[1], server[2]});
            }

            // Best available server nikalein aur task assign karein
            int[] bestServer = freeServers.poll();
            ans[i] = bestServer[1];

            // Server ko busy queue mein daalein naye freeTime ke saath
            busyServers.offer(new int[]{time + tasks[i], bestServer[0], bestServer[1]});
        }

        return ans;
    }
}
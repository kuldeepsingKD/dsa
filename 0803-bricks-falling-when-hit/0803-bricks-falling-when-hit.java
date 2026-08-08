class Solution {
     class DSU {

        int[] parent;
        int[] size;

        DSU(int n) {

            parent = new int[n];
            size = new int[n];

            // Initially har node khud ka parent hai
            for (int i = 0; i < n; i++) {

                parent[i] = i;
                size[i] = 1;
            }
        }


        // ------------------------------------
        // FIND
        // Root find karta hai
        // ------------------------------------

        int find(int x) {

            if (parent[x] == x) {
                return x;
            }

            // Path compression
            parent[x] = find(parent[x]);

            return parent[x];
        }


        // ------------------------------------
        // UNION
        // Do components ko merge karta hai
        // ------------------------------------

        void union(int a, int b) {

            int rootA = find(a);
            int rootB = find(b);

            // Already same component
            if (rootA == rootB) {
                return;
            }

            // Chhote component ko
            // bade component ke andar merge karo
            if (size[rootA] < size[rootB]) {

                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            // rootB ko rootA ke under attach karo
            parent[rootB] = rootA;

            // Size update
            size[rootA] += size[rootB];
        }


        // ------------------------------------
        // Component size
        // ------------------------------------

        int getSize(int x) {

            return size[find(x)];
        }
     }
       private void buildDSU(
        int[][] grid,
        DSU dsu,
        int roof
    ) {

        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                // Empty cell hai
                if (grid[r][c] == 0) {
                    continue;
                }

                // Current brick ka DSU index
                int current = r * cols + c;

                // ------------------------------------
                // Top row -> Roof se connect
                // ------------------------------------

                if (r == 0) {
                    dsu.union(current, roof);
                }

                // ------------------------------------
                // Down neighbour
                // ------------------------------------

                if (r + 1 < rows &&
                    grid[r + 1][c] == 1) {

                    int down = (r + 1) * cols + c;

                    dsu.union(current, down);
                }

                // ------------------------------------
                // Right neighbour
                // ------------------------------------

                if (c + 1 < cols &&
                    grid[r][c + 1] == 1) {

                    int right = r * cols + (c + 1);

                    dsu.union(current, right);
                }
            }
        }
    }
     
    public int[] hitBricks(int[][] grid, int[][] hits) {
             int rows = grid.length;
        int cols = grid[0].length;

        // Virtual roof node
        int roof = rows * cols;

        // Total nodes = all grid cells + 1 roof node
        DSU dsu = new DSU(rows * cols + 1);

        // Original grid ki copy
        int[][] copy = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            copy[r] = grid[r].clone();
        }

        // ------------------------------------
        // STEP 1: Saare hits ko pehle remove karo
        // ------------------------------------

        for (int[] hit : hits) {

            int r = hit[0];
            int c = hit[1];

            if (copy[r][c] == 1) {
                copy[r][c] = 0;
            }
        }

        // ------------------------------------
        // STEP 2:
        // Remaining bricks ka DSU build karo
        // ------------------------------------

        buildDSU(copy, dsu, roof);

        int[] answer = new int[hits.length];

        // ------------------------------------
        // STEP 3:
        // Hits ko REVERSE order mein add karo
        // ------------------------------------

        for (int i = hits.length - 1; i >= 0; i--) {

            int r = hits[i][0];
            int c = hits[i][1];

            // Agar original grid mein brick hi nahi thi
            // to is hit se kuch nahi gira
            if (grid[r][c] == 0) {
                answer[i] = 0;
                continue;
            }

            // Brick add karne se pehle
            // roof-connected component ka size
            int before = dsu.getSize(roof);

            // Brick ko wapas add karo
            copy[r][c] = 1;

            // Current brick ka DSU index
            int current = r * cols + c;

            // ------------------------------------
            // Agar top row mein hai
            // to directly roof se connect
            // ------------------------------------

            if (r == 0) {
                dsu.union(current, roof);
            }

            // ------------------------------------
            // 4 neighbours check karo
            // ------------------------------------

            int[][] directions = {
                {1, 0},   // down
                {-1, 0},  // up
                {0, 1},   // right
                {0, -1}   // left
            };

            for (int[] dir : directions) {

                int nr = r + dir[0];
                int nc = c + dir[1];

                // Boundary check + neighbour mein brick hai
                if (nr >= 0 && nr < rows &&
                    nc >= 0 && nc < cols &&
                    copy[nr][nc] == 1) {

                    int neighbor = nr * cols + nc;

                    // Dono bricks ko same component mein merge karo
                    dsu.union(current, neighbor);
                }
            }

            // Brick add karne ke baad
            // roof-connected component ka size
            int after = dsu.getSize(roof);

            // Newly stable bricks = after - before
            //
            // Lekin jo brick humne abhi add ki hai
            // wo khud nahi giregi.
            //
            // Isliye -1
            answer[i] = Math.max(0, after - before - 1);
        }

        return answer;
    }
}

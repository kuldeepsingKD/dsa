class Solution {

    public boolean canReachCorner(int X, int Y, int[][] A) {

        int n = A.length;

        // 0 ... n-1 = circles
        // n     = LEFT + TOP
        // n + 1 = RIGHT + BOTTOM
        int[] f = new int[n + 2];

        for (int i = 0; i < n + 2; i++) {
            f[i] = i;
        }

        for (int i = 0; i < n; i++) {

            long x = A[i][0];
            long y = A[i][1];
            long r = A[i][2];

            // Start point (0,0) is inside/touching circle
            if (inside(0, 0, x, y, r)) {
                return false;
            }

            // End point (X,Y) is inside/touching circle
            if (inside(X, Y, x, y, r)) {
                return false;
            }

            // LEFT or TOP
            if ((x - r <= 0 && y >= 0 && y <= Y) ||
                (y + r >= Y && x >= 0 && x <= X)) {

                union(f, n, i);
            }

            // RIGHT or BOTTOM
            if ((x + r >= X && y >= 0 && y <= Y) ||
                (y - r <= 0 && x >= 0 && x <= X)) {

                union(f, n + 1, i);
            }

            // Connect with previous circles
            for (int j = 0; j < i; j++) {

                long x2 = A[j][0];
                long y2 = A[j][1];
                long r2 = A[j][2];

                long dx = x - x2;
                long dy = y - y2;
                long sum = r + r2;

                if (dx * dx + dy * dy <= sum * sum
                        && x * r2 + x2 * r < sum * X
                        && y * r2 + y2 * r < sum * Y) {

                    union(f, i, j);
                }
            }
        }

        // If TOP/LEFT is connected to
        // RIGHT/BOTTOM -> blocking wall
        return find(f, n) != find(f, n + 1);
    }

    private boolean inside(long x, long y,
                           long cx, long cy, long r) {

        long dx = x - cx;
        long dy = y - cy;

        return dx * dx + dy * dy <= r * r;
    }

    private void union(int[] f, int a, int b) {

        int rootA = find(f, a);
        int rootB = find(f, b);

        if (rootA != rootB) {
            f[rootA] = rootB;
        }
    }

    private int find(int[] f, int x) {

        if (f[x] != x) {
            f[x] = find(f, f[x]);
        }

        return f[x];
    }
}
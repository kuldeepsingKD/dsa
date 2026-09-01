class Solution {

    static class State {
        int r;
        int c;
        int energy;
        int mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;

        }

    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;
        int totalLitter = 0;

        int[][] litterNumber = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                litterNumber[i][j] = -1;
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {

                char cell = classroom[i].charAt(j);

                if(cell == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if(cell == 'L') {
                    litterNumber[i][j] = totalLitter;
                    totalLitter++;              
                        }
            }
        }

        int allCollected = (1 << totalLitter) - 1;

        Queue<State> que = new LinkedList<>();
        que.offer(new State(startRow, startCol, energy, 0));

        boolean[][][][] visited = new boolean[m][n][energy+1][1 << totalLitter];

        visited[startRow][startCol][energy][0] = true;

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        int moves = 0;

        while(!que.isEmpty()) {
            int size = que.size();

            while(size-- > 0) {

                State current = que.poll();

                if(current.mask == allCollected) {
                    return moves;
                }

                if(current.energy == 0) {
                    continue;
                }

                for(int[] dir : directions) {
                    int newRow = current.r + dir[0];
                    int newCol = current.c + dir[1];

                    if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                        continue;
                    }

                    if(classroom[newRow].charAt(newCol) == 'X') {
                        continue;
                    }

                    int newEnergy = current.energy - 1;
                    int newMask = current.mask;

                    char cell = classroom[newRow].charAt(newCol);

                    if(cell == 'L') {
                        int litterNumberAtCell = litterNumber[newRow][newCol];
                        newMask |= (1 << litterNumberAtCell);
                    }

                    if(cell == 'R') {
                        newEnergy = energy;
                    }

                    if(visited[newRow][newCol][newEnergy][newMask]) {
                        continue;
                    }

                    visited[newRow][newCol][newEnergy][newMask] = true;

                    que.offer(new State(newRow, newCol, newEnergy, newMask));


                }
            }

            moves++;

            
        }

        return -1;
    }
}
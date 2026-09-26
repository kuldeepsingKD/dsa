class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int totalTime = 0;

        for(String g : garbage) {
            totalTime += g.length();
        }

        boolean foundM = false, foundP = false, foundG = false;

        for(int i = garbage.length - 1; i > 0; i--) {
            if(garbage[i].contains("M")) foundM = true;
            if(garbage[i].contains("P")) foundP = true;
            if(garbage[i].contains("G")) foundG = true;

            int trucksMoving = 0;
            if(foundP) trucksMoving++;
             if(foundM) trucksMoving++;
              if(foundG) trucksMoving++;

              totalTime += trucksMoving * travel[i-1];
        }

        return totalTime;
    }
}
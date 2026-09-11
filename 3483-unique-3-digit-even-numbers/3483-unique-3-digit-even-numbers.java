class Solution {
    public int totalNumbers(int[] digits) {
        int[] digitsCount = new int[10];
        int totalEven = 0;

        for (int i = 0; i < digits.length; i++) {
            digitsCount[digits[i]]++;
        }

        for (int i = 1; i <= 9; i++) {
            if (digitsCount[i] == 0) {
                continue;
            }

            digitsCount[i]--;

            for (int j = 0; j <= 9; j++) {
                if (digitsCount[j] == 0) {
                    continue;
                }

                digitsCount[j]--;

                for (int k = 0; k <= 8; k += 2) {
                    if (digitsCount[k] == 0) {
                        continue;
                    }
                    totalEven++;
                }
                digitsCount[j]++;

            }
            digitsCount[i]++;

        }

        return totalEven;
    }
}
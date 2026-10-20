import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, Queue<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(sorted[i], k -> new LinkedList<>()).add(i);
        }

        int[] targetIndex = new int[n];
        for (int i = 0; i < n; i++) {
            targetIndex[i] = pos.get(nums[i]).poll();
        }

        int result = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            if (isSortable(nums, sorted, targetIndex, n, k)) {
                result += k;
            }
        }
        return result;
    }

    private boolean isSortable(int[] nums, int[] sorted,
                                int[] targetIndex, int n, int k) {
        for (int start = 0; start < n; start += k) {
            // Check all elements map within their block
            for (int i = start; i < start + k; i++) {
                if (targetIndex[i] < start || targetIndex[i] >= start + k) {
                    return false;
                }
            }
            // Check if block is a cyclic rotation
            if (!isCyclicRotation(nums, sorted, start, k)) {  // ✅ fixed
                return false;
            }
        }
        return true;
    }

    private boolean isCyclicRotation(int[] nums, int[] sorted,
                                      int start, int k) {
        // text = sorted[start..start+k] + sorted[start..start+k]
        int[] text = new int[2 * k];          // ✅ int[] not int[][]
        for (int i = 0; i < k; i++) {
            text[i]     = sorted[start + i];  // ✅ fixed + operator
            text[i + k] = sorted[start + i];  // ✅ fixed + operator
        }

        // pattern = nums[start..start+k]
        int[] pattern = Arrays.copyOfRange(nums, start, start + k); // ✅ copyOfRange

        return kmpSearch(text, pattern, k);
    }

    private boolean kmpSearch(int[] text, int[] pattern, int k) {
        // Build KMP failure array
        int[] fail = new int[k];   // ✅ renamed from 'fall' to 'fail'
        fail[0] = 0;
        for (int i = 1; i < k; i++) {
            int j = fail[i - 1];               // ✅ fail not fall
            while (j > 0 && pattern[i] != pattern[j]) j = fail[j - 1]; // ✅
            if (pattern[i] == pattern[j]) j++; // ✅ pattern not patterm
            fail[i] = j;                       // ✅ fail not fall
        }

        // KMP search
        int j = 0;
        for (int i = 0; i < 2 * k; i++) {
            while (j > 0 && text[i] != pattern[j]) j = fail[j - 1]; // ✅
            if (text[i] == pattern[j]) j++;
            if (j == k) return true;
        }
        return false;
    }
}
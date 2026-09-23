/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
           if (head == null || head.next == null) {
            return head;
        }

        // 1. Copy all values from the Linked List to an ArrayList
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }

        // 2. Sort the array list (Uses Timsort: O(n log n))
        Collections.sort(list);

        // 3. Put the sorted values back into the original linked list nodes
        curr = head;
        for (int val : list) {
            curr.val = val;
            curr = curr.next;
        }

        return head;
    }
}
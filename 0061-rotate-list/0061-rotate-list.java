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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) {
            return head;
        }

        int L = 1;
        ListNode tail = head;
        while(tail.next != null) {
            tail = tail.next; 
            L++;
        }

        k = k % L;
        if(k == 0) return head;

        tail.next = head;
 
        int steps = L - k;
        ListNode newTail = head;
        for(int c =1; c < steps; c++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;



    }
}
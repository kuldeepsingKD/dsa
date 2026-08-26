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
    public ListNode mergeKLists(ListNode[] lists) {
           PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (node1, node2) -> node1.val - node2.val
        );

        for(ListNode node : lists) {
            if(node != null) {
                pq.offer(node);
            }
        }

        if(pq.isEmpty()) {
            return null;
        }

        ListNode head = pq.poll();

        if(head.next != null) {
            pq.offer(head.next);
        }

           ListNode tail = head;

        while(!pq.isEmpty()) {
            ListNode curr = pq.poll();

            tail.next = curr;
            tail = tail.next;

            if(curr.next != null) {
                pq.offer(curr.next);
            }


        }

        return head;
    }
}
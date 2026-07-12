/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        // Base case: if the list is empty or has only one element, it's already sorted 🛑
        if (head == null || head.next == null) {
            return head;
        }
            
        ListNode dummy = new ListNode(0); // Dummy anchor for the sorted list
        ListNode prev = dummy;             // Pointer to search for the insertion point
        ListNode curr = head;              // Runner tracking the unsorted nodes
        ListNode next = null;              // Temporary holder for the next unsorted node

        // Step 1: Iterate through the unsorted linked list 🔄
        while (curr != null) {
            next = curr.next; // Save the future path before altering links
            
            // Step 2: Scan the sorted subset from the beginning to find the insert point 🔎
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }
            
            // Step 3: Splice 'curr' into its correct position between 'prev' and 'prev.next' ✂️
            curr.next = prev.next;
            prev.next = curr;
            
            // Step 4: Reset search pointer back to dummy and move 'curr' forward
            prev = dummy;
            curr = next;
        }
        
        return dummy.next; // Return the sorted list starting after our dummy node
    }
}
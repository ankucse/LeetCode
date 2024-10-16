package org.example;



//You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//        Merge all the linked-lists into one sorted linked-list and return it.
//
//
//
//        Example 1:
//
//        Input: lists = [[1,4,5],[1,3,4],[2,6]]
//        Output: [1,1,2,3,4,4,5,6]
//        Explanation: The linked-lists are:
//        [
//        1->4->5,
//        1->3->4,
//        2->6
//        ]
//        merging them into one sorted list:
//        1->1->2->3->4->4->5->6
//        Example 2:
//
//        Input: lists = []
//        Output: []
//        Example 3:
//
//        Input: lists = [[]]
//        Output: []
//
//
//        Constraints:
//
//        k == lists.length
//        0 <= k <= 104
//        0 <= lists[i].length <= 500
//        -104 <= lists[i][j] <= 104
//        lists[i] is sorted in ascending order.
//        The sum of lists[i].length will not exceed 104.






import java.util.PriorityQueue;
/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        // Edge case: if the input is null or empty, return null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap (PriorityQueue) to store the heads of the linked lists
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-null linked list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // Dummy node to start the merged linked list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge process: repeatedly extract the smallest node from the heap
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            // If the extracted node has a next node, add it to the heap
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        // Return the merged linked list starting from the next of dummy
        return dummy.next;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create the test cases for input
        ListNode[] lists = new ListNode[3];

        // List 1: 1 -> 4 -> 5
        lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));

        // List 2: 1 -> 3 -> 4
        lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));

        // List 3: 2 -> 6
        lists[2] = new ListNode(2, new ListNode(6));

        // Create Solution object and merge the lists
        MergeKSortedList mergeKSortedList = new MergeKSortedList();
        ListNode mergedList = mergeKSortedList.mergeKLists(lists);

        // Print the merged linked list
        System.out.print("Merged List: ");
        printList(mergedList);  // Expected output: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    }
}
package org.example.coding.challenges.linkedlist;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        int value;
        int carry = 0;
        while ((l1 != null || l2 != null)) {
            int first = l1 != null ? l1.val : 0;
            int second = l2 != null ? l2.val : 0;
            int sum = first + second + carry;
            if (sum >= 10) {
                carry = 1;
                value = sum % 10;
            } else {
                value = sum;
                carry = 0;
            }
            dummy.next = new ListNode(value);
            dummy = dummy.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) dummy.next = new ListNode(carry);
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1;
        ListNode l2;
        l1 = new ListNode(3);
        l1.next = new ListNode(7);

        l2 = new ListNode(9);
        l2.next = new ListNode(2);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(addTwoNumbers(l1, l2));
    }
}


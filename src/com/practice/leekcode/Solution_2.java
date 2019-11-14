package com.practice.leekcode;

public class Solution_2 {
    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        return addTwoNumbers(false,l1,l2);
    }
    private ListNode addTwoNumbers(boolean flag,ListNode l1,ListNode l2){
        if (flag == true){
            if (l1 == null&&l2!=null){
                l2.val = l2.val+1;
                if (l2.val>=10){
                    l2.val = l2.val%10;
                    if (l2.next == null){
                        l2.next = new ListNode(0);
                    }
                    return l1.next=addTwoNumbers(true,null,l2.next);
                }else {
                    return l2;
                }
            }
            if (l2 == null && l1!= null){
                l1.val = l1.val +1;
                if (l1.val>=10){
                    l1.val = l1.val%10;
                    if (l1.next == null){
                        l1.next = new ListNode(0);
                    }
                    return l1.next=addTwoNumbers(true,l1.next,null);
                }else {
                    return l1;
                }
            }

            l1.val = l1.val+l2.val+1;
            if (l1.val>=10){
                l1.val = l1.val%10;
                if (l1.next==null){
                    l1.next = new ListNode(0);
                }
                return l1.next=addTwoNumbers(true,l1.next,l2.next);
            }else {
                if (l1.next==null&&l2.next==null)
                    return l1;
                return l1.next=addTwoNumbers(false,l1.next,l2.next);
            }
        }else {

            if (l1 == null&&l2!=null){
                return l2;
            }
            if (l2 == null && l1!= null){
                return l1;
            }
            l1.val = l1.val+l2.val;
            if (l1.val>=10){
                l1.val = l1.val%10;
                if (l1.next==null){
                    l1.next = new ListNode(0);
                }
                return l1.next=addTwoNumbers(true,l1.next,l2.next);
            }else {
                if (l1.next==null&&l2.next==null)
                    return l1;
                return l1.next=addTwoNumbers(false,l1.next,l2.next);
            }

        }

    }




}

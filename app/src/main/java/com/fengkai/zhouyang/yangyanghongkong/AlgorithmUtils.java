package com.fengkai.zhouyang.yangyanghongkong;

import android.util.Log;

public class AlgorithmUtils {

    public static void testAlgorithm() {
        twoNodeAdd();
    }

    private static void twoNodeAdd() {
        Algorithm.ListNode p = new Algorithm.ListNode(2);
        p.next = new Algorithm.ListNode(3);
        p.next.next = new Algorithm.ListNode(5);
        Algorithm.ListNode q = new Algorithm.ListNode(5);
        q.next = new Algorithm.ListNode(8);
        q.next.next = new Algorithm.ListNode(9);
        q.next.next.next = new Algorithm.ListNode(3);
        Algorithm.ListNode listNode = Algorithm.addTwoNumbers(p, q);
        StringBuffer str = new StringBuffer();
        while (listNode != null){
            str.append(listNode.val+"> ");
            listNode = listNode.next;
        }
        Log.d("fengkai001", "addTwoNode:" + str.toString());
    }

}

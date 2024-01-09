package com.example.rank.likou;

/**
 * @ClassName ListNode
 * @Description  单链表节点类
 * @Author ts
 * @Date 2023/1/6 18:20
 * @Version 1.0
 */
public class ListNode {

      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

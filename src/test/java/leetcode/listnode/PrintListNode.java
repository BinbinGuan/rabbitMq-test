package leetcode.listnode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintListNode {



    public int[] reversePrint(ListNode head) {

        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        int size=stack.size();
        int[] print=new int[size];
        for (int i=0;i<size;i++){
            print[i]=stack.pop().val;
        }
        return print;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(3);
        ListNode head1=new ListNode(2);
        ListNode head2=new ListNode(1);
        head.next=head1;
        head1.next=head2;
        System.out.println(reversePrint1(head));
    }

    public static int[] reversePrint1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head!=null){
            list.add(head.val);
            head=head.next;
        }
        int size= list.size();
        int[] print = new int[size];
        for (int i=0;i<list.size();i++){
            print[size-1]=list.get(i);
            size--;
        }
        return print;
     }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}

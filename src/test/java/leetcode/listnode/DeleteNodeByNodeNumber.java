package leetcode.listnode;

import org.junit.Test;

/**
 * @author: GuanBin
 * @date: Created in 下午3:31 2019/8/19
 * 删除链表的倒数第N个节点
 */
public class DeleteNodeByNodeNumber {
    /**
     * 示例：
     * <p>
     * 给定一个链表: ya->1->2->3->4->5, 和 n = 2.   6-2=4
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * <p>
     * 思路
     * <p>
     * 我们注意到这个问题可以容易地简化成另一个问题：删除从列表开头数起的第 (L - n + 1)(L−n+1) 个结点，其中 L 是列表的长度。只要我们找到列表的长度 L，这个问题就很容易解决。
     * <p>
     * 算法
     * <p>
     * 首先我们将添加一个哑结点作为辅助，该结点位于列表头部。哑结点用来简化某些极端情况，例如列表中只含有一个结点，或需要删除列表的头部。在第一次遍历中，我们找出列表的长度 LL。然后设置一个指向哑结点的指针，并移动它遍历列表，直至它到达第 (L - n)(L−n) 个结点那里。我们把第 (L - n)(L−n) 个结点的 next 指针重新链接至第 (L - n + 2)(L−n+2) 个结点，完成这个算法。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-by-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     */
    private ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);//哑节点
        dummy.nextNode = head;
        int length = 0;
        ListNode first = head;

        while (first != null) {
            length++;
            first = first.nextNode;
        }
        length -= n;  //5-2=3
        first = dummy;//ya->1->2->3->4->5
        //循环后的node节点first是n之前的所有节点，哑节点也已去除
        while (length > 0) {
            length--;
            first = first.nextNode;
        }
        //n之后的节点赋值给n之前的前一个节点，正好跳过n节点自己
        first.nextNode = first.nextNode.nextNode;
        return dummy.nextNode;

    }

    /**
     * 方法二：一次遍历算法
     * 算法
     * <p>
     * 上述算法可以优化为只使用一次遍历。我们可以使用两个指针而不是一个指针。第一个指针从列表的开头向前移动 n+1n+1 步，而第二个指针将从列表的开头出发。现在，这两个指针被 nn 个结点分开。我们通过同时移动两个指针向前来保持这个恒定的间隔，直到第一个指针到达最后一个结点。此时第二个指针将指向从最后一个结点数起的第 nn 个结点。我们重新链接第二个指针所引用的结点的 next 指针指向该结点的下下个结点。
     * <p>
     * <p>
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-by-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndTest(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.nextNode = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.nextNode;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.nextNode;
            second = second.nextNode;
        }
        second.nextNode = second.nextNode.nextNode;
        return dummy.nextNode;
    }


    @Test
    public void test() {
        ListNode dummy1 = new ListNode(1);
        ListNode dummy2 = new ListNode(2);
        ListNode dummy3 = new ListNode(3);
        ListNode dummy4 = new ListNode(4);
        ListNode dummy5 = new ListNode(5);
        ListNode dummy6 = new ListNode(6);
        dummy1.nextNode = dummy2;
        dummy2.nextNode = dummy3;
        dummy3.nextNode = dummy4;
        dummy4.nextNode = dummy5;
        dummy5.nextNode = dummy6;

        removeNthFromEnd(dummy1, 2);

    }

}

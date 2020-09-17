import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {
    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<Integer> lists = new ArrayList<>();
        // 两数之和
        int sum = 0;
        // 是否进位
        int level = 0;
        while(l1 != null || l2 != null){
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            sum += level;
            level = 0;
            lists.add(sum%10);
            if(sum >= 10)
                level = sum/10;
            sum = 0;
        }
        if(level > 0){
            lists.add(level);
        }
        ListNode node = new ListNode(lists.get(0));
        lists.remove(0);
        l1 = node;
        for(Integer a: lists){
            ListNode nodeTemp = new ListNode(a);
            l1.next = nodeTemp;
            l1 = l1.next;
        }
        l1.next = null;
        return node;
    }
	/* 加强版
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode temp = root;
        // 两数之和
        int sum = 0;
        // 是否进位
        int level = 0;
        while(l1 != null || l2 != null || level != 0){
            sum = l1 == null ? 0 : l1.val;
            sum += l2 == null ? 0 : l2.val;
            sum += level;
            level = sum/10;
            ListNode sumNode = new ListNode(sum % 10);
            temp.next = sumNode;
            temp = temp.next;
            l1 = l1 == null ? null: l1.next;
            l2 = l2 == null ? null: l2.next;
        }
        return root.next;
    }
	*/
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1);
        ListNode a = new ListNode(9);
        ListNode b = new ListNode(9);
        ListNode c = new ListNode(9);
        ListNode d = new ListNode(9);
        ListNode e = new ListNode(9);
        ListNode f = new ListNode(9);
        ListNode g = new ListNode(9);
        ListNode h = new ListNode(9);
        ListNode i = new ListNode(9);
        i.next = null;
        h.next = i;
        g.next = h;
        f.next = g;
        e.next = f;
        d.next = e;
        c.next = d;
        b.next = c;
        a.next = b;
        l2.next = a;
        System.out.println(solution.addTwoNumbers(l1,l2).val);;
    }
}

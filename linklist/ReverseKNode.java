public class ReverseKNode{
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k <= 0) return head;
        ListNode dummy = new ListNode(1), cursor = dummy;
        dummy.next = head;
        
        while(cursor.next != null){
            ListNode from = cursor.next, to = cursor.next;
            for(int i = k - 1; i > 0; i--){
                if(to.next == null){
                    return dummy.next;
                }
                to = to.next;
            }
            reverse(cursor, to.next, from, to);
            cursor = from;
        }
        
        return dummy.next;
    }
    
    private ListNode reverse(ListNode left, ListNode right, ListNode from, ListNode to){
        if(from == to) return from;
        ListNode pre = reverse(left, right, from.next, to);
        pre.next = from;
        if(from == left.next){
            left.next = to;
            from.next = right;
        }
        
        return from;
    }
}
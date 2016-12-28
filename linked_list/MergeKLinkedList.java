public class MergeKLinkedList {
    public ListNode mergeKLists(ListNode[] lists) {
         if(lists == null || lists.length <= 0){
             return null;
         }
         
         Queue<ListNode> heap = new PriorityQueue<>(lists.length, new ListNodeComparator());
         for(ListNode list : lists){
             if(list != null)
                heap.add(list);
         }
         
         ListNode dummy = new ListNode(0);
         ListNode cur = dummy;
         while(!heap.isEmpty()){
             ListNode head = heap.poll();
             cur.next = head;
             cur = cur.next;
             if(head.next != null){
                 heap.add(head.next);
             }
         }
         
         return dummy.next;
    }
}

class ListNodeComparator implements Comparator<ListNode>{
    
    @Override
    public int compare(ListNode left, ListNode right){
        if(left == null){
            return 1;
        }else if(right == null){
            return -1;
        }
        
        return left.val - right.val;
    }
}
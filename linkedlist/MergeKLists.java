public class MergeKLists{
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new ListNodeComparator());

        for(ListNode list : lists){
            if(list != null) queue.add(list);
        }

        ListNode dummy = new ListNode(0);
        ListNode cursor = dummy;
        while(!queue.isEmpty()){
        	ListNode next = queue.poll();
        	cursor.next = next;
        	cursor = cursor.next;
        	if(next.next != null) queue.add(next.next);
        }

        return dummy.next;
    }

    public static class ListNodeComparator implements Comparator<ListNode>{
    	@Override
    	public int compare(ListNode a, ListNode b){
    		return a.val - b.val;
    	}
    }
}

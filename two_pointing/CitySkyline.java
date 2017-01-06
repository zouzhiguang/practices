public class CitySkyline{
	public List<int[]> getSkyline(int[][] buildings) {
	    List<int[]> result = new ArrayList<>();
	    List<int[]> height = new ArrayList<>();
	    for(int[] b:buildings) {
	        height.add(new int[]{b[0], -b[2]});
	        height.add(new int[]{b[1], b[2]});
	    }
	    Collections.sort(height, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

	    // Max Heap
	    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
	    pq.offer(0);
	    int prev = 0;
	    for(int[] h:height) {
	        if(h[1] < 0) {
	            pq.offer(-h[1]);
	        } else {
	            pq.remove(h[1]);
	        }
	        int cur = pq.peek();
	        if(prev != cur) {
	            result.add(new int[]{h[0], cur});
	            prev = cur;
	        }
	    }
	    return result;
	}
}
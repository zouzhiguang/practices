public class MaxRectangleHistogram {
	private int maximalRectangleHisto(int[] height){
		if(height == null || height.length <= 0) return 0;
		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for(int i = 0; i <= height.length; i++){
			while(!stack.isEmpty() && (i == height.length || height[i] < height[stack.peek()])){
				Integer last = stack.pop();
				// calculate previous maximal area for 0~last or stack.peek() ~ last
				int width = stack.isEmpty() ? i : i - (stack.peek() + 1); 
				max = Math.max(max, width * height[last]); 
			}
			stack.push(i);
		}

		return max;
	}
}
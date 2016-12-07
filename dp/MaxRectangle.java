// MaxRectangle.java
public class MaxRectangle {
	// 从上往下扫，一层一层计算最大面积。 
	// 1. 定义： 面积是由左边界left和右边界right来维护，高度就是top来维护，面积就是（右-左）*高 就是包含当前点的最大矩形面积
	// 2. 初始化： 右边界全是最右，即n-1，左边界全0，高度全0
	// 3. 状态转移： 
	// 高度： 如果当前是1就高度+1，否则高度置0
	// 左边界和右边界用来维护矩形的形状。如果当前行左边界更靠左，或右边界更靠右，仍可以维持高度+1的情况下的矩形。
	// 所以矩形的左边界和右边界只能尽可能的向中间方向收敛。
	// 左边界： 当前节点所在矩形的左边界， 从左往右数， 因为只能向中间收敛， 取max(current_left, left[i])
	// 右边界： 当前节点所在矩形的右边界， 从右往左数， 因为只能向中间收敛， 取min(current_right, right[i])
	// 面积： 不断算当前节点的最大面积， 即max(area, (right[i] - left[i]) * top[i])
	// 4. 返回值： 最大的area数值
	public int maximalRectangleDP(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0; 

		int rows = matrix.length, cols = matrix[0].length;
		int[] left = new int[cols], right = new int[cols], top = new int[cols];
		// init with big value so as can min to cap to smaller values.
		Arrays.fill(right, cols); // max distance (inclusive) to right-most 1 at (y,x)
		int max = 0;

		for (int y = 0; y < rows; y++) {

			for (int x = 0; x < cols; x++) {
				top[x] = matrix[y][x] == '1' ? top[x] + 1 : 0;
			}

			// max left distance so far
			for (int x = 0, current_left = 0; x < cols; x++) {
    			if(matrix[y][x] == '1') {
    			    left[x] = Math.max(left[x], current_left);
                } else {
                    left[x] = 0; 
                    current_left = x + 1;
                }
			}

			// max right distance so far
			for (int x = cols-1, current_right = cols; x >= 0; x--) {
    			if(matrix[y][x] == '1') {
    			    right[x] = Math.min(right[x], current_right);
    			} else {
    			    right[x] = cols; 
    			    current_right = x;
    			}    
			}

			for (int x = 0; x < cols; x++) {
		  		if (matrix[y][x] == '1') {
		    		// width should exclude double count of current element
		    		max = Math.max(max, (right[x] - left[x])*top[x]);
		  		}
			}
		}

		return max;
	}

	public int maximalRectangleHistogram(char[][] matrix) {
		if(matrix == null || matrix[0] == null) return 0;
		int m = matrix.length, n = matrix[0].length, max = 0; 
		int[] height = new int[n];
		for(int y = 0; y < m; y++){
			for(int x = 0; x < n; x ++){
				height[x] = matrix[y][x] == '1' ? height[x] + 1 : 0;
			}

			max = Math.max(maximalRectangleHisto(height), max);
		}

		return max;
	}

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
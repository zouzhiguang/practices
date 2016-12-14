public class OptimalBinarySearchTree {
	public int optimalSearchTree(int[] keys, int[] freq) {
		if(keys == null || freq == null || keys.length <= 0 || freq.length <= 0 || keys.length != freq.length) return 0;

		int n = keys.length;
		int[][] state = new int[n][n];

		for(int i = 0 ; i < n; i++) state[i][i] = freq[i];

		for(int k = 2; k <= n; k++){
			for(int left = 0; left < n - k; left++){
				int right = left + k - 1;
				state[left][right] = Integer.MAX_VALUE;
				for(int i = left; i <= right; i++){
					state[left][right] = Math.min(state[left][right], (i == left ? 0 : state[left][i - 1]) + 
				}
			}
		}

	} 


		public int maxCoinsDP(int[] iNums) {
	    int[] nums = new int[iNums.length + 2];
	    int n = 1;
	    for (int x : iNums) if (x > 0) nums[n++] = x;
	    nums[0] = nums[n++] = 1;


	    int[][] dp = new int[n][n];
	    for (int k = 2; k < n; ++k)
	        for (int left = 0; left < n - k; ++left) {
	            int right = left + k;
	            for (int i = left + 1; i < right; ++i)
	                dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
	        }

	    return dp[0][n - 1];
}
// JumpGame.java

public class JumpGame {
	public boolean canJump(int[] A) {
		if(A == null || A.length <= 0) return false;
		int index = A.length - 1, end = A.length - 1;
		while(index >= 0){
			if(num[index] >= end - index) end = index;
			index--;
		}

		return A[0] >= end;
	}


	public int minJump(int[] nums) {
		if(nums == null || nums.length <= 1) return 0;
		// define:
		int[] state = new int[nums.length];
		
		// init:
		for(int i = 1; i < nums.length; i++){
			state[i] = Integer.MAX_VALUE;
		}
		int reachable = nums[0];

		// transfer:
		for(int i = 1; i < nums.length; i++){
			for(int j = Math.max(0, i - reachable); j < i; j++){
				if(state[j] != Integer.MAX_VALUE && j + nums[i] >= i){
					state[i] = Math.min(state[i], state[j] + 1);
				}
			}

			reachable = Math.max(nums[i], reachable);
		}

		return state[nums.length - 1];
	}

	public int minJumpSimplified(int[] nums) {
		if(nums == null || nums.length <= 1) return 0;
		int step = 0, reachable = 0, lastMin = 0;
		while(reachable < nums.length - 1){
			for(int i = reachable; i >= lastMin; i--){
				reachable = Math.max(reachable, i + nums[i]);
			}

			step ++;
			lastMin++;
		}
	}
}
public class MinArray {

	// check if given nums, the smallest number's square  is smaller than the max number.
	// if not, then we can delete either one element from head or tail. 
	// return how many element we need to delete so that can match the rule
	// if delete all still can not fullfill ([2,2,2]), then return -1

	private static final int MAX_SQR = (int) Math.sqrt(Integer.MAX_VALUE);
	public int deleteCount(int[] nums){
		if(nums.length < 2) return -1;
		Stack<Integer> min = new Stack<>(), max = new Stack<>();
		min.push(0);
		max.push(0)
		for(int i = 1; i < nums.length; i++){
			if(nums[i] <= nums[min.peek()]) min.push(i);
			if(nums[i] >= nums[max.peek()]) max.push(i);
		}

		int count = Integer.MAX_VALUE, left = 0, right = length - 1;
		while(!min.isEmpty() && max.isEmpty()){
			if(square(nums[min.peek()])  < nums[max.peek()]){
				int current = getCount(min.peek(), max.peek(), nums.length - 1);
				if(current < count){
					count = current;
					left = Math.min(min.peek(), max.peek());
					right = Math.max(min.peek(), max.peek());
				}

				if(max.peek() != 0) max.pop();
				else min.pop();
			}else{
				if(min.peek() != 0) min.pop();
				else max.pop();
			}
		}
	}

	private int square(int min){
		if(min >= MAX_SQR) return Integer.MAX_VALUE;
		return min * min;
	}

	private int getCount(int min, int max, int last){
		return Math.min(min, max) + last - Math.max(min, max);
	}
}
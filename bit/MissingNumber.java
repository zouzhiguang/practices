public class MissingNumber {
	// nums from 0 ~ n, with n numbers, so there is one missing. find the missing one.
	// Solution: for any number, i ^ i = 0
	public int missingNumber(int[] nums) {
	    int xor = 0, i = 0;
		for (i = 0; i < nums.length; i++) {
			xor = xor ^ i ^ nums[i];
		}

		return xor ^ i;
	}
}
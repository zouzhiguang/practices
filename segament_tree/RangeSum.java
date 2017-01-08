// sum is called many time without update	
public class RangeSumImmutable {
	private final int[] sums;


	public RangeSumImmutable(int[] nums){
		sums = new int[nums.length];
		int sum = 0;
        for(int i = 0; i < nums.length;i++){
            sum += nums[i];
            sums[i] = sum;
        }
	}

    public int sumRange(int i, int j) {
        return i == 0 ? sums[j] : sums[j] - sums[i - 1];
    }
}
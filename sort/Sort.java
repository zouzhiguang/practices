public class Sort {
	public void quickSort(int[] nums){
		quickSort(nums, 0, nums.length - 1);
	}

	private void quickSort(int[] nums, int start, int end){
		int pivot = partition(nums, start, end);

		quickSort(nums, start, pivot - 1);
		quickSort(nums, pivot + 1, end);
	}

	private int partition(int[] nums, int low, int high){
		if(low >= high) return low;
		int begin = low - 1, value = nums[high];
		for(int i = low; i <= high; i++){
			if(nums[i] <= value) swap(nums, ++begin, i);
		}
		return begin;
	}

	private void swap(int[] nums, int a, int b){
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
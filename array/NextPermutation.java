public class NextPermutation {
	public String getPermutation(int n, int k){
		List<Integer> remains = new ArrayList<>();
		int[] fact = new int[n+1];
		fact[0] = 1;
		for(int i = 1; i <= n; i++){
			fact[i] = fact[i-1] * i;
			remains.add(i);
		}

		k--;

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++){
			int index = k / fact[n - i];
			sb.append(remains.remove(index));
			k -= index * fact[n-i];
		}

		if(sb.isEmpty()){
			return "0";
		}
		return sb.toString();
	}

	public void nextPermutation(int[] nums){
        if(nums == null || nums.length <= 1) return;				
		int length = nums.length, last = nums[length-1], index = -1;
		for(int i = length - 2; i >= 0; i--){
			if(nums[i] < last){
				index = i; 
				break;
			}
			last = nums[i];
		}

		if(index < 0){
			reverse(nums, 0, length - 1);
			return;
		}

		for(int i = length - 1; i > index; i--){
			if(nums[index] < nums[i]){
				swap(nums, index, i);
				break;
			}
		}

		reverse(nums, ++index, length - 1);
	}

	private void reverse(int[] nums, int low, int high){
		while(low < high){
			swap(nums, low++, high--);
		}
	}

	private void swap(int[] nums, int a, int b){
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
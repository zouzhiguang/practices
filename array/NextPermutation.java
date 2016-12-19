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

	// can given s permute and become a palindrome?
	public boolean palindromePermutation(String s){
		int[] counts = new int[256];
		int odd = 0;
		for(char c : s.toCharArray()){
			odd += ++counts[c] % 2 == 1 ? 1 : -1;
		}

		return odd <= 1;
	}

	// return all s permutation (without dup) that is palindrome
	public List<String> allPalindromePermutation(String s){
		List<String> result = new ArrayList<>();
		if(s == null || s.isEmpty() || !palindromePermutation(s)) return result;

		// frequency map all char
		int[] count = new int[256];
		int odd = 0
		// count all char and check if have middle char (only single time)
		for(char c : s.toCharArray()){
			count[c]++;
			odd += count[c] % 2 == 1? 1 : -1;
		}

		if(odd > 1) return result;
		char middle = null;
		int length;
		for(int i = 0; i < 256; i++){
			if(count[i] == 0) continue;
			if(count[i] % 2 == 1){ 
				middle = (char) i;
			}

			count[i] /= 2;
		}

		// generate all permutations:
		dfs(count, new char[s.length() / 2], 0, middle, result);
	}

	private void dfs(int[] count, char[] cs, int offset, char middle, List<String> result){
		if(offset == cs.length){
			result.add(toString(cs, middle));
			return;
		}

		for(int i = 0; i < 256; i++){
			if(count[i] > 0){
				count[i]--;
				cs[offset] = (char) i;
				dfs(count, cs, offset + 1, middle, result);
				cs[offset] = null;
				count[i]++;
			}
		}
	}

	private String toString(char[] cs, char middle){
		StringBuilder sb = new StringBuilder(cs.length * 2 + 1);
		for(char c : cs){
			sb.append(c);
		}

		if(middle != null){
			sb.append(middle);
		}

		for(int i = cs.length - 1; i >= 0; i--){
			sb.append(cs[i]);
		}

		return sb.toString();
	}
}
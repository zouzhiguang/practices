public class Permutations {
    public List<List<Integer>> permuteNoDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null || nums.length <= 0) return results;

        dfs(nums, new boolean[nums.length], new ArrayList<>(), results);
        return results;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> result, List<List<Integer>> results){
    	if(result.size() >= nums.length){
    		results.add(new ArrayList(result));
    		return;
    	}

    	for(int i = 0; i < nums.length; i++){
    		if(visited[i]) continue;
    		result.add(nums[i]);
    		visited[i] = true;
    		dfs(nums, visited, result, results);
    		result.remove(result.size() - 1);
    		visited[i] = false;
    	}
    }

    public List<List<Integer>> permuteWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null || nums.length <= 0) return results;
        Arrays.sort(nums);
        dfsDup(nums, new boolean[nums.length], new ArrayList<>(), results);
        return results;
    }


    private void dfsDup(int[] nums, boolean[] visited, List<Integer> result, List<List<Integer>> results){
    	if(result.size() >= nums.length){
    		results.add(new ArrayList(result));
    		return;
    	}

    	for(int i = 0; i < nums.length; i++){
    		if(visited[i] || (i != 0 && nums[i] == nums[i-1] && !visited[i-1])) continue;

    		result.add(nums[i]);
    		visited[i] = true;
    		dfs(nums, visited, result, results);
    		result.remove(result.size() - 1);
    		visited[i] = false;
    	}
    }
}
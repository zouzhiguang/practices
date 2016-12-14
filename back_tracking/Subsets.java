public class Subsets{
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null || nums.length <= 0) return results;
        //results.add(new ArrayList<>());
        subsets(nums, 0, new ArrayList<>(), results);
        return results;
    }
    
    private void subsets(int[] nums, int offset, List<Integer> result, List<List<Integer>> results){
        results.add(new ArrayList<>(result));
        if(offset >= nums.length) return;
        
        for(int i = offset; i < nums.length; i ++){
            result.add(nums[i]);
            subsets(nums, i + 1, result, results);
            result.remove(result.size() - 1);
        }
    }	
}
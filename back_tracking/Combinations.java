public class Combinations {
    public List<List<Integer>> combinationNoDup(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums == null || nums.length <= 0) return result;

        int[] current = new int[nums.length];
        combinationNoDup(nums, 0, current, 0, result);

        return result;
    }

    public void combinationNoDup(int[] nums, int cursor, int[] current, int offset, List<List<Integer>> result){
        if(cursor == nums.length || offset == nums.length) return;
        current[offset] = nums[cursor];

        result.add(toList(current, 0, offset));

        combinationNoDup(nums, cursor + 1, current, offset + 1, result);
        combinationNoDup(nums, cursor + 1, current, offset, result);
    }

    public List<List<Integer>> combinationsDup(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums == null || nums.length <= 0) return result;

        Arrays.sort(nums);
        int[] current = new int[nums.length];
        combinationsDup(nums, 0, current, 0, result);

        return result;
    }

    public void combinationsDup(int[] nums, int cursor, int[] current, int offset, List<List<Integer>> result){
        if(cursor == nums.length || offset == nums.length) return;
        current[offset] = nums[cursor];

        result.add(toList(current, 0, offset));

        combinationNoDup(nums, cursor + 1, current, offset + 1, result);
        while (cursor < nums.length - 1 && nums[cursor] == current[offset]) cursor++;
        combinationNoDup(nums, cursor + 1, current, offset, result);
    }

    public List<List<Integer>> combinationSizeK(int[] nums, int k){
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums == null || nums.length <= 0) return result;

        int[] current = new int[k];
        combinationSizeK(nums, 0, current, 0, result);

        return result;
    }

    public void combinationSizeK(int[] nums, int cursor, int[] current, int offset, List<List<Integer>> result){
        if(cursor == nums.length || offset == nums.length) return;
        current[offset] = nums[cursor];

        result.add(toList(current, 0, offset));

        combinationNoDup(nums, cursor + 1, current, offset + 1, result);
        while (cursor < nums.length - 1 && nums[cursor] == current[offset]) cursor++;
        combinationNoDup(nums, cursor + 1, current, offset, result);
    }

    private List<Integer> toList(int[] nums, int from, int to){
        List<Integer> list = new ArrayList<>();
        for(int i = from; i <= to; i++){
            list.add(nums[i]);
        }
        return list;
    }

    public List<List<Integer>> combinationSum(int[] nums, int k) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null || nums.length <= 0 || k <= 0) return results;
        Arrays.sort(nums);
        combinationSum(nums, 0, k, new ArrayList<Integer>(), results);
        return results;
    }
    
    private void combinationSum(int[] nums, int offset, int sum, List<Integer> result, List<List<Integer>> results){
        if(sum == 0){
            results.add(new ArrayList<>(result));
            return;
        }
        
        if(sum < 0 || offset >= nums.length || nums[offset] == 0 || nums[offset] > sum){
            return;
        }
        
        for(int i = offset; i < nums.length; i++){
            result.add(nums[i]);
            combinationSum(nums, i, sum - nums[i], result, results);
            result.remove(result.size() - 1);
        }
    }
}

// LongestConsecutiveSequence.java

// use a hashmap to mimic the union find to store the boundary of the connected components 
// hash -> (num, sum) 

// for each num <- nums
// if hash have num, it's dup, continue
// if hash have num + 1, sum = hash.get(num+1) + 1; put(num, sum); put(num + hash.get(num) - 1, sum)
// if hash also have num - 1, sum = hash.get(num - 1) + hash.get(num)? 0 : value; put(num)
// if not have, put(num, 1);

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer,Integer> ranges = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (ranges.containsKey(num)) continue;
            
            // 1.Find left and right num
            int left = ranges.getOrDefault(num - 1, 0);
            int right = ranges.getOrDefault(num + 1, 0);
            int sum = left + right + 1;
            max = Math.max(max, sum);
            
            // 2.Union by only updating boundary
            // Leave middle k-v dirty to avoid cascading update
            if (left > 0) ranges.put(num - left, sum);
            if (right > 0) ranges.put(num + right, sum);
            ranges.put(num, sum); // Keep each number in Map to de-duplicate
        }
        return max;
    }
}
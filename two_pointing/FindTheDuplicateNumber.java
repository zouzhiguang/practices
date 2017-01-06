
// as nums consists of n + 1 elements which is from 1 to n with at least one dup number. 
// we can use binary search: left half should contains at most n/2 elements which is bigger than mid, vice versa. 
// the one contains more means there is duplicates. eg:
// 1,2,2,4,5,6,6, is 1....6, mid is 3, total have 3 elements are <= 3. then dup must be in right half.

// 5,2,3,4,1,7,5,3, 
// 1) mid = (1 + 7) / 2 = 4 -> 5 , 5 > 4, right = mid - 1, dup -> 1, 3 (4 - 1)



// 1,1,1,1,1,1,1,1
// (1 + 7) /2 -> mid = 4, count = 8, dup -> [1, 3] 3 -> 4 - 1

//1,2,2,4,5,7,6,6
// mid = 4, count = 3, dup -> [5, 7]

// 3,3,3,3,5,5,5,5



// 1 + 7 /2 -> mid = 4, count = 5, dup -> [1,3]
// mid = 2, count = 3, dup -> [1, 1]
// mid = 1, count = 1, low -> [2, 1], return low -> 2 


// 8,8,8,8,8,8,8,8
//   
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int count = 0;
            for(int i : nums){
                if(i <= mid) count++;
            }
            
            if(count > mid){
                // low...mid count should have dup
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        
        return low;
    }
}
// MedianOfTwoSortedArray.java

// 在两个已排序数组中找第K大元素：
// 思路： 已排序的题目肯定要考虑用二分， 我们假设A，B中排前K的元素分别有K‘和k“，则有A[K'+1] > B[K"]，K“ + 1 = K - （K’ + 1）， 即K”= K - 1 -（K‘ + 1） 
// 所以问题转化成在A中找mid的位置,使得 mid = K' + 1, 所以 midB = K - 1 - mid， 令到A[mid] > B[midB]，A的 low = 0， high = m， 则有：
// mid = low + (high - low) / 2; 注意到midB可能越界（K > m / 2 + n 就可以在第一次while的时候越界，比如m = 2, n = 3, k = 5, midB = 3 >= n越界）
// if (midB >= n || A[mid] < B[midB]) low = mid + 1;
// else high = mid;
// 结束条件是 low = high, low指向A中第K‘ + 1个元素。 
// 所以K‘ = low - 1；注意到 low - 1可能 < 0越界，(A里面元素全部都大于B), 则不选K‘而返回K“

// 复杂度： O(LogM), M是两个数组较小的size
public class MedianOfTwoSortedArray {
	public int getMedian(int[] A, int[] B){
		if(A == null || B == null) return 0;
		int m = A.length;
		int n = B.length;
		if(m + n % 2 == 0) {
			return (getKthElement(A, B, (m + n) / 2) + getKthElement(A, B, (m + n) / 2 + 1)) / 2;
		}else{
			return getKthElement(A, B, (m + n) / 2);
		}
	}

	public int getKthElement(int[] A, int[] B, int K){
		if(A.length > B.length) return getKthElement(B, A, K);
		int m = A.length, n = B.length, low = 0, high = m;
		while(low < high){
			int mid = (low + high) / 2;
			int indexB = K - mid - 1;
			if(indexB >= n || A[mid] < B[indexB]) low = mid + 1;
			else high = mid;
		}

		int keyA = low - 1 >= 0 ? A[low - 1] : Integer.MIN_VALUE;
		int keyB = K - 1 - low >= 0 ? B[K - 1 - low] : Integer.MIN_VALUE;
		return Math.max(keyA, keyB);
	}
}
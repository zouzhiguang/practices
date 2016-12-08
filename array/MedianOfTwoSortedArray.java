// MedianOfTwoSortedArray.java

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
// MaxSubarrayProduct.java

public class MaxSubarrayProduct{
	public int maxProduct(int[] A) {
        if(A == null || A.length == 0) return 0;
        int maxProduct = A[0];
        int max = A[0], min = A[0];
        for(int i = 1; i < A.length; i ++) {
            int tmpMax = max, tmpMin = min;
            max = Math.max(Math.max(A[i], tmpMax * A[i]), tmpMin * A[i]); // max of A[i], max * A[i], min * A[i]
            min = Math.min(Math.min(A[i], tmpMax * A[i]), tmpMin * A[i]); // min of A[i], max * A[i], min * A[i]
            maxProduct = Math.max(max, maxProduct);
        }
        return maxProduct;
    }
}
# DP Bottom Up 
this kind of questions have things in common, state is difficult to make and calculate because one operation would have influence with others and so as not easy to define state. 
but when we say we reversely re-think about the problem, if given fixed length of structure (node, string, etc), we can find a optimal solution of the substructure. 
then we iteratively calculate from 1, 2, 3, ... , n 'layers' to find the final solution.

[Scramble String](https://leetcode.com/problems/scramble-string/)
Problem: Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively. To scramble the string, we may choose any non-leaf node and swap its two children. Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Thinking: when we find from i to j, it's a scramble, then we know res = state[1][i - 1] && isScramble[i, j] && state[j + 1][n] 
so if we cut i~j into three part and each part, k = j - i, have: i - 2 < k, j - i < k, and n - (j + 1) < k, then we can enumerate k from 2 to n, to find the final result.

state[len][i][j] is from 

```
1. state[len][i][j] is whether given the scramble length to len, whether s1[i..n] is scramble of length = len with s2[j...n] 
2. state[1][i][j] = s1.charAt(i-1) == s2.charAt(j-1)
3. function: 
    state[len][i][j] = state[k][i][j] && state[len-k][i+k][j+k] || state[k][i][j+len-k] && state[len-k][i+k][j] for i <- 0 ~ n - len, j <- 0 ~ n - len, k <- 1 ~ len - 1, len <- 2 ~ n
4. state[n][0][0]
```

Also can be done using divide and conquer:
if we use ^ to mark isScramble 
when we cut at i <- 1 ~ n - 1; we know if s1[0..i-1] ^ s2[0...i-1] && s1[i...n-1] ^ s2[i...n-1] or s1[0...i-1] ^ s2[i...n-1] && s1[i...n-1] ^ s2[0...n-i-1], we return true, or else false;
```
for(int i = 1; i < n; i++){
    String s11 = s1.substring(0, i);
    String s12 = s1.substring(i, s1.length());
    String s21 = s2.substring(0, i);
    String s22 = s2.substring(i, s2.length());
    String s23 = s2.substring(0, s2.length()-i);
    String s24 = s2.substring(s2.length() - i, s2.length());

    if(isScramble(s11, s21) && isScramble(s12, s22))
        return true;
    if(isScramble(s11, s24) && isScramble(s12, s23))
        return true;    
}
return false; 
```

[Burst Balloon](https://leetcode.com/problems/burst-balloons/)
Problem: burst a balloon once a time and get nums[i-1] * nums[i] * nums[i+1] points, and i-1 and i + 1 become adjacent. take nums[-1] == nums[n] = 1, return maximum points can get to burst all balloons. Assert all non-negative integer in nums.  

Solution: 
1. Divide and Conquer. when burst a balloon index i, it separates the current problem into two: 0...i, and i...right. So you can have: 
```
public int burst(int[] nums, int left, int right) {
  for (int i = left + 1; i < right; ++i)
      ans = Math.max(ans, nums[left] * nums[i] * nums[right] + burst(nums, left, i) + burst(nums, i, right));
  return ans;
}
```
But this is exponential as burst call iteratively. Using memorization could solve this and degrade total to O(n^3):
```
public int burst(int[][] memo, int[] nums, int left, int right) {
    if (left + 1 == right) return 0;
    if (memo[left][right] > 0) return memo[left][right];
    int ans = 0;
    for (int i = left + 1; i < right; ++i)
        ans = Math.max(ans, nums[left] * nums[i] * nums[right] 
        + burst(memo, nums, left, i) + burst(memo, nums, i, right));
    memo[left][right] = ans;
    return ans;
}
```

2. DP solution: try to solve it using a bottom-up approach. 
As when we burst a balloon, it change the structure and can not use dp directly. 
An idea would think reversely. When we have i balloons left to burst, the optimal solution can be calculated as they are controlled.
As we can use state[i][left][right] to get when we have i balloons left and we can calculated best from left to right. (right - left = i);
then we can struct i from 2, 3, 4, .... , n balloons as we have:
```
state[i+1][left][right] = burst from left to right + state[i][left][right] to find the best for each i+1 layer.
```
and as we only use previous layer's data, we can reduce the space usage to o(n^2) for state[left][right].
```
1. state[left][right] is the max points we get when we burst from left to right, and we total have right - left balloons.
2. state[left][right] = 0
3. state transfer: state[left][right] = Max(state[left][right], nums[left] * nums[i] * nums[right] + state[left][i] + state[i][right]);
4. return state[0][n-1];
```
code: 
```
int[][] dp = new int[n][n];
    for (int k = 2; k < n; ++k)
        for (int left = 0; left < n - k; ++left) {
            int right = left + k;
            for (int i = left + 1; i < right; ++i)
                dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
        }

    return dp[0][n - 1];
```

[Optimal Binary Search Tree](http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/)
Problem: given keys[] and freq[] which is keys and times of calling to find the key. return the minimal operations count for optimally construct the bst.
Example: 
```
Input:  keys[] = {10, 12}, freq[] = {34, 50}
There can be following two possible BSTs 
        10                       12
          \                     / 
           12                 10
          I                     II
Frequency of searches of 10 and 12 are 34 and 50 respectively.
The cost of tree I is 34*1 + 50*2 = 134
The cost of tree II is 50*1 + 34*2 = 118 
```
Thinking: same idea with burst balloons, if we put index i as root, then we cut the keys to 0 ~ i - 1 and i + 1 ~ n. 
So the cost[i] = cost(keys, left, i - 1, level + 1) + level * freq[i] + cost(keys, i + 1, right, level + 1); 
As it iteratively calculate the value, we need memorization same as it's done in burst balloons:
```
public int cost(int[][] memo, int[] keys, int[] freq, int left, int right, int level) {
    if (left > right) return 0;
    if (left == right) return freq[left] * level;
    if (memo[left][right] > 0) return memo[left][right];
    int ans = Integer.MAX_VALUE;
    for (int i = left; i <= right; ++i)
        ans = Math.max(ans,  cost(memo, keys, freq, left, i - 1, level + 1) + level * freq[i] + cost(memo, keys, freq, i + 1, right, level + 1));
    memo[left][right] = ans;
    return ans;
}

public int optimalSearchTree(int keys[], int freq[], int n)
{
     return cost(new int[n][n], keys, freq, 0, n-1, 1);
}

```

Or same Burst Balloons, we can use DP bottom up solution: 
then we can struct i from 2, 3, 4, .... , n balloons as we have:
```
state[i+1][left][right] = 
```

```
1. state[i][j] is optimal subtree cost from keys[i] to keys[j]
2. init: state[i][i] = freq[i], others = Integer.MAX_VALUE
3. state[i][j] = min(state[i][j], state[i][k - 1] + freq[k] * level +  state[k + 1][j]);
4. return state[0][n-1]
```


[Matrix Chain Multiplication](http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/)


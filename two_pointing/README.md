# Two Pointer

Usually on array problem or String problem, to find all the solution of a pattern (or count of the pattern, all index satisfy the pattern, etc...)
This kind of question usually need to keep a window (which the window satisfy the required condition), by shrink left and extend right (or shrink on both sides) to find next position that match the condition. 

## Typical Problem:   

[3Sum](https://leetcode.com/problems/3sum/): Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

```
Sort the array first, then enumerate i from 0 ~ n-3, let left = i and right = n - 1, 
	while(left < right), 
		if i + left + right = 0, add result. 
		if sum > 0, right--, 
		if sum < 0, left ++.
```

[4Sum](https://leetcode.com/problems/4sum/): same with three sum, enumerate i from 0 ~ n - 4, j from i + 1 ~ n - 3, left = j + 1, right = n - 1;


[Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/): 
Problem: Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
```
For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".
```
```
define left = right = 0; counter is number of char that the window currently required to match the pattern.
1. move right to right, until match all char in the T: if table[T.charAt(right++)]-- > 0, counter--. until counter == 0
2. if counter == 0, shrink left to un-match any char in the T to continue. record min length required to match.
3. use int[128] to replace hashmap to optimize the algorithms. 

```

[Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/): 
Problem: You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

```
For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
```
```

	

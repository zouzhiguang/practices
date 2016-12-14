# Dynamic Programming
Dynamic programming usually divide the problem into 4 parts: 
```
1. define state: whether it's one sequence, two sequence, what is the meaning of the state
2. state initialization: what is state[0] or state[0][0] or state[i][0] or state[0][j] which are the boundary condition
3. state transfer: according to the problem, how to compose the state transfer function
4. result return
```

One sequence: 
Single String iteration problem: LCS/ LIS, Word Break, Decode Ways, Longest Valid Parentheses, Palindrome min cut 
Single Array subset filtering problem: maximum product sub array, Jump game, 

Two sequence: 
Two String manipulation problem: Interleaving string, edit distance, distinct subsequences, scramble string, regex matching, wildcard matching,  
matrix iteration problem: unique path, minimum path sum, dungeon game, maximum rectangle

## One Sequence

LCS/ LIS: 
```
1. state[i] is lcs of s[0...i-1]
2. state[0] = 0; state[1] = 1;
3. for j = 0 ... i - 1, if(s[i] >= s[j]) state[i] = max(state[i], state[j] + 1);
4. return state[s.length()]
```

word break: 
```
1. boolean[] state[i] is string[0...i-1] can word break or not.
2. state[0] = true
3. for j <- 0...i-1, if(state[j] && substring(j-1, i) is in dict) state[i] = true
4. return state[s.length()]
```

Decode ways: 
```
1. state[i] is number of decode ways for substring s[0...i-1]
2. state[0] = 1; state[1] = 1;
3. if s[i-1] == '0' 
		// invalid cases: 
		if(s[i-2] > '2' || s[i-2] == '0') return 0;	 
		// can only be interpreted as 10 or 20, so it's same as state[i-2], that's why state[0] = 1; 
		state[i] = state[i-2];
	else
		int val = (s[i-2] - '0') * 10 + (s[i-1] - '0');
		if(val > 10 && val <= 26) state[i] = state[i-1] + state[i-2]; 
		else state[i] = state[i-1];
4. return state[s.length()];
```

Longest Valid Parentheses:
```
1. state[i] is LVP of s[0...i-1]
2. state[0] = 0
3. if(s[i-1] == '(') state[i] = 0;
   else 
       if(i - state[i-1] >= 1 && s[i-state[i-1] - 1] == '(') 
       		if(i - state[i-1] - 2 < 0)
       			state[i] = state[i-1] + 2;
       	    else 
       	    	state[i] = state[i-1] + 2 + state[i-state[i-1] - 2];
4. max(state[i], i <- 1....s.length())
```

Palindrome Min Cut
```
1. state[i] is the number of min cut of S[0...i-1]
2. init: state[0] = 0; state[1] = 0; state[i] = i;
3. for j <- 0...i, if(isPalindrom(S[j-1...i])) state[i] = Math.min(state[i], state[j-1] + 1);
4. return  state[s.length()]
```

maximum product sub array:
```
1. max[i] is current maximum product, min[i] is current minimal product
   we record maximal and minimal because there may be negative numbers which either current should fall in either max or min
   As we only need the last index value of max and min, so no need to keep all the value there.
2. max = min = A[0]
3. max = Math.max(Math.max(A[i], lastMax * A[i]), lastMin * A[i])
   min = Math.min(Math.min(A[i], lastMax * A[i]), lastMin * A[i]) 
4. max during iteration.
```

Jump game I: 
Question: given an array of integer, which refers to the length it can reach. determine if given array can jump from first index to the last
Solution: use greedy thinking, if index can reach end, then just determine whether can reach index which index <- 0...index.
```
2. init: index = end = nums.length - 1;
3. iteration: while(index >= 0) if(nums[index] >= end - index) end = index; index--;
4. result: end == 0;
```

Jump game II: 
jump from first to the last, for the min number of jump
```
1. state[i] is the min step needed for jump from 0 to i
2. state[i] = Integer.MAX_VALUE, state[0] = 0;
3. reachable = Math.max(reachable, A[i]), for j <- i - reachable (min=0) to i; 
   if(state[j] != Integer.MAX_VALUE && state[j] + 1 < state[i]) state[i] = state[j] + 1; 

4. state[nums.length - 1]
```

## Two Sequences: 
### 2-String problem
Basically 2-string have dependency on each other with the result / state

Interleaving string
```
1. state[i][j] is whether string s1[0...i-1] and s2[0...j-1] is interleaving of s3[0...i+j-1] 
2. init: 
         state[0][0] = true
         state[i][0] = state[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1); 
         state[0][j] = state[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1); 
3. transfer: 
         if(s1[i-1] == s3[i+j-1]) state[i][j] = state[i][j] || state[i-1][j]
         if(s2[i-1] == s3[i+j-1]) state[i][j] = state[i][j] || state[i][j-1]
         else state[i][j] = false
4. result: state[s1.length()][s2.length()]
```

Edit Distance
```
1. state[i][j] is the edit distance of s1[0...i-1] and s2[0...j-1]
2. state[0][j] = j; state[i][0] = i;
3.  if s[i-1] == s[j-1] state[i][j] = state[i-1][j-1];
    else state[i][j] = Math.min(state[i][j-1], Math.min(state[i-1][j], state[i-1][j-1])) + 1
4. result: state[s1.length()][s2.length()]
```

Distinct Subsequences
problem: count of distinct s2 which is a subsequence of s1
```
1. state[i][j] is count of disctinct count of s2[0...j-1] in s1[0...i-1]
2. state[i][0] = 1
3. if s1[i-1] == s2[j-1], state[i][j] = state[i-1][j] + state[i][j-1]
   else state[i][j] = state[i-1][j]
4. state[s1.length()][s2.length()] 
```

Scramble String
```
1. state[len][i][j] is whether s1[i...i+len-1] and s2[j...j+len-1] is scramble 
2. state[1][i][j] = s1.charAt(i-1) == s2.charAt(j-1)
3. function: 
    state[len][i][j] = state[k][i][j] && state[len-k][i+k][j+k] || state[k][i][j+len-k] && state[len-k][i+k][j]
4. state[n][0][0]
```

Regex Matching:
```
1. state[i][j] is if s[0...i] matches p[0...j]
2. init: state[0][j] = state[0][j-2] && p.charAt(j-1) == '*', for j <- 2....p.length()
3. function: 
   if p.charAt(j-1) == '*' state[i][j] = state[i][j-2] //(0 time) 
                                         state[i][j-1] //(1 time)
                                         state[i-1][j] && singleMatch(s, p, i, j-1) //(multiple times)
   else state[i][j] == state[i-1][j-1] && singleMatch(s, p, i, j); // previous matched and current single char match
4. return state[s.length()][p.length()];
```

Wildcard Matching
```
1. state[i][j] is if s[0...i] matches p[0...j]
2. init: state[0][0] = true; state[0][j] = state[0][j-1] && p[j-1] == '*' 
3. if p[j-1] == '*' state[i][j] = state[i][j-1] //(* match empty string)
                               || state[i-1][j] //(* match any single char) 
   else state[i][j] == state[i-1][j-1] && singleMatch(s, p, i, j);

4. return state[s.length()][p.length()]
```

### Matrix related 2D DP problems
Unique Path Basic
Problem: from top left move to bottom right, how many disctint path in total?
```
1. state[i][j] is count of unique path move from [0,0] to [i,j]
2. state[0][0] = 0; state[0][j] = 1; state[i][0] = 1;
3. state[i][j] = state[i-1][j] + state[i][j-1];
4. return state[m][n]
```

Unique Path with Obstacles
Problem: from top left move to bottom right, Obstacles can not be reached, how many disctint path in total?
```
1. state[i][j] is count of unique path move from [0,0] to [i,j]
2. state[0][0] = 0; state[0][j] = 1; state[i][0] = 1; i <- 0...m, j <- 0...n, when grid[i] / grid[j] == '1' break;   
3. if(grid[i][j] == 1) state[i][j] = 0;
   else state[i][j] = state[i-1][j] + state[i][j-1];
4. return state[m][n]
```

Minimum Path Sum
Problem: from Top lef to bottom right with sum all non-negative integer together, return minimal sum
```
1. state[i][j] is min sum from [0,0] to [i,j]
2. state[0][0] = 0;
3. state[i][j] = Math.min(state[i][j-1], state[i-1][j]) + grid[i][j];
4. return state[m][n]
```

Dungeon Game
Problem: from top left to bottom right, each cell can add-up / lose health point, what is the minimum hp when knight enter [0,0]?
```
1. state[i][j] is minimal life required start at [i,j] to [m,n]
2. state[m][n] = Math.max(1, 1 - dungeon[m][n]);
3. state[i][j] = Math.max(1, Math.min(state[i+1][j], state[i][j+1]) - dungeon[i][j])
4. return state[0][0]
```

[Maximum Rectangle](https://leetcode.com/problems/maximal-rectangle/)
```
state[i][j] is the maximum area that included the point [i,j].

then state[i][j] is separated into three component: 
state[i][j] = (left[i][j] + right[i][j] -1) * height[i][j];

left[i][j] is i <- 0...m, j <- 0....n-1, the left boundary from position j (j is inclusive)
right[i][j] is i <- 0...m, j <- n-1....0, the right boundary from position j (j is inclusive)
height[i][j] is height of the rectangle for rows 0...i that include point[i,j]

transfer function: 
left[j] = if(matrix[i][j] == 1) min(left[j - 1], currentLineLeft); else 0
right[j] = if(matrix[i][j] == 1) min(right[j + 1], currentLineRight); else 0
height[i] = if(matrix[i][j] == 1) height[i] = 1 else 0
```
..1.left distance should be calculated from left to right, and right distance from right to left
..2.as we use min to calculate left & right distances, their default values should be some big enough number
..3.since left & right distances are inclusive of current element, we should -1 when calculating width to avoid double counting







































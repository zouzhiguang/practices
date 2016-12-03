# Dynamic Programming
Dynamic programming usually divide the problem into 4 parts: 
1. define state: whether it's one sequence, two sequence, what is the meaning of the state
2. state initialization: what is state[0] or state[0][0] or state[i][0] or state[0][j] which are the boundary condition
3. state transfer: according to the problem, how to compose the state transfer function
4. result return

One sequence: 
Single String iteration problem: LCS/ LIS, Word Break, Decode Ways, Longest Valid Parentheses, Palindrome min cut 
Single Array subset filtering problem: maximum product sub array, Jump game, 

Two sequence: 
Two String manipulation problem: Interleaving string, edit distance, distinct subsequences, scramble string, regex matching, wildcard matching,  
matrix iteration problem: unique path, minimum path sum, dungeon game, maximum rectangle

## One Sequence

LCS/ LIS: 
1. state[i] is lcs of s[0...i-1]
2. state[0] = 0; state[1] = 1;
3. for j = 0 ... i - 1, if(s[i] >= s[j]) state[i] = max(state[i], state[j] + 1);
4. return state[s.length()]

word break: 
1. boolean[] state[i] is string[0...i-1] can word break or not.
2. state[0] = true
3. for j <- 0...i-1, if(state[j] && substring(j-1, i) is in dict) state[i] = true
4. return state[s.length()]

Decode ways: 
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

Longest Valid Parentheses:
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

Palindrome Min Cut
1. state[i] is the number of min cut of S[0...i-1]
2. init: state[0] = 0; state[1] = 0; state[i] = i;
3. for j <- 0...i, if(isPalindrom(S[j-1...i])) state[i] = Math.min(state[i], state[j-1] + 1);
4. return  state[s.length()]


maximum product sub array:


Jump game: 

## Two Sequences: 



































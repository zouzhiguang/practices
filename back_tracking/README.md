# Back Tracking
Back tracking is a collection of problem which is like performing bfs/dfs on particular data stucture like array, string, matrix (2d, 3d array). Mostly of the time, it requires of memorization for the iterating from making the algorithms going to exponential complexity. Before we set current node/position visited, and then after it comes back from recursion, we reset it to un-visited or default value. 
Template would be: 

```
Matrix: 
void dfs(char[][] board, int i, int j, TrieNode p, List<String> results){
	// checking condition
	if(board[i][j] = '#' || p.next[board[i][j] - 'a'] == null) return;
	// fullfil condition
	if(p.word != null){
		results.add(p.word);
		p.word = null;
		// can return or continue finding.
	}

	// mark current node as visited before going next visit
	board[i][j] = '#';
	// boundary checking and go dfs
	if (i > 0) dfs(board, i - 1, j ,p, res); 
	if (j > 0) dfs(board, i, j - 1, p, res);
	if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
	if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
	// after recursion, reset char back to original value.
	board[i][j] = c;
}	
```
```
String: 
void dfs(String s, int pos, List<String> result, List<List<String>> results) {
		// check fullfill/quit condition
        if (pos == s.length()) {
            results.add(new ArrayList<>(result));
            return;
        }

        // iterate from each position.
        for (int i = pos + 1; i <= s.length(); i++) {
            String prefix = s.substring(pos, i);
            if (!isPalindrome(prefix)) continue;

            // set/add result
            result.add(prefix);
            partition(s, i, result, results);
            // remove result
            result.remove(result.size() - 1);
        }
    }

```

__The problem is different from dp only that dp is asking for the optimal result while back tracking is to find out all the possible answers/subsets/combinations.__**

For examples: 

[Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) is Back Tracking problem as it's asking for all the possible palindrome combinations within given string.

[Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/) is DP problem as it's asking for minimum cut that can make all substring a palindrome. 


More:
[Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)
Problem: Given a string containing only digits, restore it by returning all possible valid IP address combinations.
For example:
```
Given "25525511135",
return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
```
ip could be from 0 ~ 255 for each setction, should be exactly 4 section.


More for advices on [Word Search II](https://leetcode.com/problems/word-ladder-ii/) 

Intuitively, start from every cell and try to build a word in the dictionary. Backtracking (dfs) is the powerful way to exhaust every possible ways. Apparently, we need to do pruning when current character is not in any word.

..* How do we instantly know the current character is invalid? HashMap?
..* How do we instantly know what's the next valid character? LinkedList?
..* But the next character can be chosen from a list of characters. "Mutil-LinkedList"?

Combing them, Trie is the natural choice. Notice that:

..* TrieNode is all we need. search and startsWith are useless.
..* No need to store character at TrieNode. c.next[i] != null is enough.
..* Never use c1 + c2 + c3. Use StringBuilder.
..* No need to use O(n^2) extra space visited[m][n].
..* No need to use StringBuilder. Storing word itself at leaf node is enough.
..* No need to use HashSet to de-duplicate. Use "one time search" trie.

59ms: Use search and startsWith in Trie class like this popular solution.
33ms: Remove Trie class which unnecessarily starts from root in every dfs call.
30ms: Use w.toCharArray() instead of w.charAt(i).
22ms: Use StringBuilder instead of c1 + c2 + c3.
20ms: Remove StringBuilder completely by storing word instead of boolean in TrieNode.
20ms: Remove visited[m][n] completely by modifying board[i][j] = '#' directly.
18ms: check validity, e.g., if(i > 0) dfs(...), before going to the next dfs.
17ms: De-duplicate c - a with one variable i.
15ms: Remove HashSet completely. dietpepsi's idea is awesome.

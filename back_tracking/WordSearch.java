public class WordSearch {
	
	// check if given 2D matrix contains given word by iterating from adjacent cell.
	public boolean exist(char[][] board, String word) {
	    if(board == null || board.length <= 0 || board[0].length <= 0 || word == null) return false;
	    if(word.isEmpty()) return true;
	    
	    boolean[][] visited = new boolean[board.length][board[0].length];
	    for(int i = 0; i < board.length; i ++){
	        for(int j = 0; j < board[0].length; j++){
	             if(word.charAt(0) == board[i][j] && dfs(board, i, j, word, 0, visited)) {
	                 return true;
	             }        
	        }
	    } 
	    
	    return false;
	}

	public boolean dfs(char[][] board, int i, int j, String word, int offset, boolean[][] visited){
	    if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) return false;
	    if(word.charAt(offset) != board[i][j]) return false;
	    if(offset >= word.length()) return true;
	    visited[i][j] = true;

	    if(dfs(board, i + 1, j, word, offset + 1, visited)
	    || dfs(board, i, j + 1, word, offset + 1, visited)
	    || dfs(board, i - 1, j, word, offset + 1, visited)
	    || dfs(board, i, j - 1, word, offset + 1, visited))
	     return true;
	    
	    visited[i][j] = false;
	    
	    return false;
	}

	// check if given 2D matrix, give all words in dictionary that are contained in the board.
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> res = new ArrayList<>();
	    TrieNode root = buildTrie(words);
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[0].length; j++) {
	            dfs (board, i, j, root, res);
	        }
	    }
	    return res;
	}

	public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
	    char c = board[i][j];
	    if (c == '#' || p.next[c - 'a'] == null) return;
	    p = p.next[c - 'a'];
	    if (p.word != null) {   // found one
	        res.add(p.word);
	        p.word = null;     // de-duplicate
	    }

	    board[i][j] = '#';
	    if (i > 0) dfs(board, i - 1, j ,p, res); 
	    if (j > 0) dfs(board, i, j - 1, p, res);
	    if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
	    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
	    board[i][j] = c;
	}

	public TrieNode buildTrie(String[] words) {
	    TrieNode root = new TrieNode();
	    for (String w : words) {
	        TrieNode p = root;
	        for (char c : w.toCharArray()) {
	            int i = c - 'a';
	            if (p.next[i] == null) p.next[i] = new TrieNode();
	            p = p.next[i];
	       }
	       p.word = w;
	    }
	    return root;
	}

	class TrieNode {
	    TrieNode[] next = new TrieNode[26];
	    String word;
	}

}
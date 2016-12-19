public class WordLadder {
	    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if(beginWord == null || endWord == null || !wordList.contains(endWord)) return results;
        Map<String, Set<String>> map = new HashMap<>();
        wordList.add(beginWord);
        for(String word : wordList){
            map.put(word, new HashSet<>());
            for(String other: wordList){
                if(word.equals(other) || !isNext(word, other)) continue;
                map.get(word).add(other);
            }
        }
        List<String> result = new ArrayList<>();
        Set<String> hash = new HashSet<>();
        result.add(beginWord);
        hash.add(beginWord);
        // dfs(beginWord, endWord, new int[]{wordList.size()}, map, hash, result, results);
        bfs(beginWord, endWord, map, hash, results);
        return results;
    }

    private void bfs(String current, String end, int[] minSize, Map<String, Set<String>> map, Set<String> visited, List<List<String>> results){
    	Queue<String> queue = new LinkedList<>();
    	queue.add(current); 
    	while(!queue.isEmpty()){
    		int size = queue.size();
    		for(int i = 0; i < size(); i++){
    			
    		}
    	}
    }

    private void dfs(String current, String end, int[] minSize, Map<String, Set<String>> map, Set<String> visited, List<String> result, List<List<String>> results){
        if(current.equals(end)){
            results.add(new ArrayList<>(result));
            minSize[0] = Math.min(minSize[0], result.size());
            return;
        }
        
        if(result.size() >= minSize[0]) return;
        
        for(String next : map.get(current)){
            if(visited.contains(next)) continue;
            visited.add(next);
            result.add(next);
            dfs(next, end, minSize, map, visited, result, results);
            result.remove(result.size() - 1);
            visited.remove(next);
        }
    }

    private boolean isNext(String a, String b){
        if(a == null || b == null || a.length() != b.length()) return false;
        int diff = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) diff ++;
        }
        return diff == 1;
    }
}
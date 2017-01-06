
// 给定start和end，还有字典dict， 找到所有最少的从start到end的变换：
// 1. 从A到B每次只能变换一个字母
// 2. 所有的变换的中间词都必须是字典中给的

// Solution: 
// 1. 因为要找最少的变换，所以一定要用bfs来从start开始搜索end，并建立到该层的完整的树（用map来表示connectivity）。当遇到end的时候，就表示所有结果的长度应该等于层数，做完该层以后就返回，从而把不必要的都剪枝掉。
// 2. 用dfs来查找所有的解，（从start到end的路径，方法是找到distance中所有距离等于下标的结果集，而且互相还要是相邻的， 具体看代码）
// 总体复杂度：O(MN), M是字典长度，N是单词长度
public class WordLadder {
	 public List<List<String>> findLadders(String start, String end, Set<String> dict) {    
        List<List<String>> res = new ArrayList<>();         
        Map<String, List<String>> neighbors = new HashMap<>();// Neighbors for every node
        Map<String, Integer> distance = new HashMap<>();// Distance of every node from the start node
        dict.add(end); 
        dict.remove(start);// avoid loop cycle when bfs/dfs
        
        bfs(start, end, dict, neighbors, distance);                 
        dfs(start, end, neighbors, distance, new ArrayList<String>(), res);   
        return res;
    }

    // find distance to start for all words in dict.
    // 同时构建neighbors和distance，邻居用于查找每个单词的邻居， distance用于dfs遍历过滤下一层
    // O(MN): 最差每个单词扫一遍，每个单词的字母遍历一次 -> M * 26 * N = 26MN -> O(MN)
    private void bfs(String start, String end, Set<String> dict, Map<String, List<String>> neighborsMap, Map<String, Integer> distance) {
        for (String word : dict) neighborsMap.put(word, new ArrayList<>());
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        neighborsMap.put(start, new ArrayList<>());
        int curDistance = 0;
        distance.put(start, curDistance);
        boolean endReached = false; // to collect all result with same distance
        while (!queue.isEmpty() && !endReached) {
            int count = queue.size();
            curDistance++;   
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                List<String> neighbors = getNeighbors(cur, dict);
                for (String neighbor : neighbors) {
                    neighborsMap.get(cur).add(neighbor);
                    if (distance.containsKey(neighbor)) continue;
                    distance.put(neighbor, curDistance);
                    if (end.equals(neighbor)) endReached = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Find all next level nodes.   
    // 每一个字符都扫从a~z，那么总体时间复杂度是26 * wordLen -> O(wordLen) 
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        StringBuilder builder = new StringBuilder(node);
    
        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < builder.length(); i++) {
                if (builder.charAt(i) == ch) continue;
                char old_ch = builder.charAt(i);
                builder.setCharAt(i, ch);
                String current = builder.toString();
                if (dict.contains(current)) res.add(current);
                builder.setCharAt(i, old_ch);
            }
        }
        return res;
    }

    // 用dfs来遍历所有的解
    // 最差start和end都不相同，则最差情况是一共M个单词被N层均匀吸收，即每层有Logn M 个元素。 所以复杂度是O（NLogM）的
    private void dfs(String cur, String end, Map<String, List<String>> neighbors, Map<String, Integer> distance, List<String> solution, List<List<String>> res) {
       solution.add(cur);
       // 退出条件：
       if (end.equals(cur)) res.add(new ArrayList<String>(solution));
       else {
            // 找到所有当前节点的可能的下一层，而且距离起点的距离比当前节点大1， 即可以从start -> ... -> current -> next -> ... -> end 的所有解
           for (String next : neighbors.get(cur)) {            
               if (distance.get(next) == distance.get(cur) + 1) {
                   dfs(next, end, neighbors, distance, solution, res);
               }
            }
       }           
       solution.remove(solution.size() - 1);
   }
}
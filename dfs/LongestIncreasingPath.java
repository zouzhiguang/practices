//LongestIncreasingPath.java
// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
//Given an integer matrix, find the length of the longest increasing path.

//From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).


// dfs solution, 16 ms
// for each point (i,j), serve as a starting point to go up, down, left and right to search for valid path. 
public class LongestIncreasingPath {
	public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int len = dfs(matrix, i, j, matrix.length, matrix[0].length, Integer.MIN_VALUE, cache);
                max = Math.max(max, len);
            }
        }
        
        return max;
    }
    
    private int dfs(int[][] matrix, int i, int j, int m, int n, int previous, int[][] cache){
        if(i < 0 || i >= m || j < 0 || j >= n || previous >= matrix[i][j]) return 0;
        if(cache[i][j] != 0) return cache[i][j];

        int left = dfs(matrix, i - 1, j, m, n, matrix[i][j], cache) + 1;
        int down = dfs(matrix, i, j - 1, m, n, matrix[i][j], cache) + 1;
        int right = dfs(matrix, i + 1, j, m, n, matrix[i][j], cache) + 1;
        int up = dfs(matrix, i, j + 1, m, n, matrix[i][j], cache) + 1;
        int max = 1;
        max = Math.max(max, Math.max(left, Math.max(right, Math.max(up, down))));
        cache[i][j] = max;
        return max;
    }

	// Topological Sort solution: 
	// every level, scan the 
    public int longestIncreasingPath2(int[][] matrix) {
    	if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return 0;
        int m = matrix.length, n = matrix[0].length, count = m * n;
        int ans = 0;
        while(count > 0){
        	Set<Point> remove = new HashSet<>();

        	for(int i = 0; i < m; i++){
        		for(int j = 0; j < n; j++){
        			if(matrix[i][j] == Integer.MIN_VALUE) continue;

					boolean up = j == 0 || matrix[i][j - 1] <= matrix[i][j];  
        			boolean down = j == n - 1 || matrix[i][j + 1] <= matrix[i][j];
        			boolean left = i == 0 || matrix[i - 1][j] <= matrix[i][j];
        			boolean right = i == m - 1 || matrix[i + 1][j] <= matrix[i][j];


        			if(up && down && left && right) {
        				remove.add(new Point(i, j));
        			}
        		}
        	}

        	for(Point p : remove){
        		matrix[p.x][p.y] = Integer.MIN_VALUE;
        		count--;
        	}

        	ans++;
        }

        return ans;
    }

    public static class Point {
    	int x, y;
    	public Point(int x, int y){
    		this.x = x;
    		this.y = y;
    	}
    }
}




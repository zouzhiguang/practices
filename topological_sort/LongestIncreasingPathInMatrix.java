// LongestIncreasingPathInMatrix.java

public class LongestIncreasingPathInMatrix {
	public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return 0;
        int m = matrix.length, n = matrix[0].length, count = m * n;
        int ans = 0;
        while(count > 0){
        	Set<Point> remove = new HashSet<>();

        	for(int i = 0; i < m; i++){
        		for(int j = 0; j < n; j++){
        			if(matrix[i][j] == Integer.MIN_VALUE) continue;

        			boolean up = i == 0 || matrix[i - 1][j] <= matrix[i][j];  
        			boolean down = i == m - 1 || matrix[i + 1][j] <= matrix[i][j];
        			boolean left = j == 0 || matrix[i][j - 1] <= matrix[i][j];
        			boolean right = j == n - 1 || matrix[i][j + 1] <= matrix[i][j];

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

}

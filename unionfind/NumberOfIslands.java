// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
// You may assume all four edges of the grid are all surrounded by water.
// 找出小島數目，0是水，1是陸地，連續陸地視為一個島。求水中島的數目。
// dfs：遍歷所有，如果當前為1，則用dfs將相鄰的都置0，數目+1. 
// UF： 假設所有1都是獨立的一個小島，如果union一次就將count--，並將兩者union，如果相同root就說明之前已經union過了，就不用再count--。最後返回count數
public class NumberOfIslands {
    public int numIslandsDFS(char[][] grid) {
		int count = 0;
	    n = grid.length;
	    if (n == 0) return 0;
	    m = grid[0].length;
	    for (int i = 0; i < n; i++){
	        for (int j = 0; j < m; j++)
	            if (grid[i][j] == '1') {
	                DFSMarking(grid, i, j);
	                ++count;
	            }
	    }    
	    return count;
	}

	// set all nearby target which is non-zero to 0
	private void DFSMarking(char[][] grid, int i, int j) {
	    if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
	    grid[i][j] = '0';
	    DFSMarking(grid, i + 1, j);
	    DFSMarking(grid, i - 1, j);
	    DFSMarking(grid, i, j + 1);
	    DFSMarking(grid, i, j - 1);
	}

   	public int numIslandsUF(char[][] grid) {
		if(grid.length == 0 || grid[0].length == 0) return 0;

	    int m = grid.length, n = grid[0].length;
	    UF uf = new UF(m , n, grid);

	    for(int i = 0; i < m; i++) {
	        for(int j = 0; j < n; j++) {
	            if(grid[i][j] == '0') continue;
	            int p = i * n + j;
	            int q;
	            // check up, down, left, right if any adjacent node is '1'
	            if(i > 0 && grid[i - 1][j] == '1') {
	                q = p - n;
	                uf.union(p, q);
	            }
	            if(i < m - 1 && grid[i + 1][j] == '1') {
	                q = p + n;
	                uf.union(p, q);
	            }
	            if(j > 0 && grid[i][j - 1] == '1') {
	                q = p - 1;
	                uf.union(p, q);
	            }
	            if(j < n - 1 && grid[i][j + 1] == '1') {
	                q = p + 1;
	                uf.union(p, q);
	            }
        	}
    	}
    	
    	return uf.count;
    }

    public static class UF {
    	private final int count;
		private final int[] id;

    	public UF(int m, int n, char[][] grid) {
    		// set all 1 to a count when there is no connection.
		    for(int i = 0; i < m; i++) {
		        for(int j = 0; j < n; j++) {
		            if(grid[i][j] == '1') count++;
		        }
		    }
 			
 			// m and n is used for init underlying array.
		    id = new int[m * n];
		    for(int i = 0; i < m * n; i++) {
		        id[i] = i;
		    }
		}

		// quick union UF with path compression.
		public int find(int p) {
		    while(p != id[p]) {
		        id[p] = id[id[p]];
		        p = id[p];
		    }
		    return p;
		}

		public boolean isConnected(int p, int q) {
		    int pRoot = find(p);
		    int qRoot = find(q);
		    if(pRoot != qRoot) return false;
		    else return true;
		}

		public void union(int p, int q) {
		    int pRoot = find(p);
		    int qRoot = find(q);
		    if(pRoot == qRoot) return;
		    id[pRoot] = qRoot;
		    count--;
		}
    }
}
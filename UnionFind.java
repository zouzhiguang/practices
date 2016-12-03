public class UnionFind {
	int[] idx;

	public UnionFind(int capacity) {
		idx = new int[capacity];
	}

	public void union(int i, int j){
		idx[j] = parent(i);
	}

	public boolean isConnected(int i, int j){
		return parent(i) == parent(j);
	}

	private int parent(int i){
		while(idx[i] != i) i = idx[i];
		return i;
	}

}
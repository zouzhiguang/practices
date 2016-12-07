public class UnionFindWeightedWithPathCompression {
	private static final int ROOT = 1;
	private int[] id;
	private int[] size;
	public UnionFindWeightedWithPathCompression(int capacity){
		id = new int[capacity + 1];
		size = new int[capacity + 1];
		for(int i = 0; i <= capacity; i++){
			id[i] = i;
			size[i] = 1;
		}
	}

	public void union(int a, int b){
		int i = root(a);
		int j = root(b);
		if(i == j) return ;
		if(size[i] < size[j]){
			size[i] += size[j];
			id[j] = i;
		}else{
			size[j] += size[i];
			id[i] = j;
		}
	}

	public boolean isConnected(int i, int j){
		return root(i) == root(j);
	} 

	private int root(int i){
		while(id[a] != a){
			id[a] = id[id[a]];
			a = id[a];
		}
	}
}
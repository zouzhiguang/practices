public class MinHeap {
	private static int ROOT = 1;

	private int[] values;
	private int counter;

	public MinHeap(int capacity){
		values = new int[capacity + 1];
	}

	public void insert(int i){
		values[++counter] = i;
		swim(counter); 
	}

	public int min(){
		if(isEmpty()) return -1;
		return values[ROOT];
	}


	public int deleteMin(){
		swap(ROOT, counter);
		int value = values[counter--];
		sink(ROOT);
		return value;
	}

	public boolean isEmpty(){
		return counter == 0;
	}

	private int parent(int i){
		return i / 2;
	}

	private void swim(int i){
		while(i != ROOT && values[i] <= values[parent(i)]){ 
			swap(i, parent(i));
			i = parent(i)
		};
	}

	private void sink(int i){
		while(2 * i < counter){
			if(values[2 * i] <= values[i]){
				swap(i, 2 * i);
				i = 2 * i;
			}else if(2 * i + 1 <= counter && values[2 * i + 1] <= values[i]){
				swap(i, 2 * i + 1);
				i = 2 * i + 1;
			}
		}
	}

	private void swap(int i, int j){
		int tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

}

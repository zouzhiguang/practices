// 设计LFU的cache， 要求最少频率使用的entry最先被remove掉。如果相同频率，则remove较早插入的entry。
// 首先肯定存储value和存储频率的各一个map
// 要使得LFU，则需要一个PriorityQueue，每次将value最小的（最早或频率最少）的拿出来扔掉
// 使用一个Cache类，存储了key，频率，和插入时间。
// 其他一切顺其自然就写出来了
public class LFUCache {
	public class Cache implements Comparable<Cache> {
		int key, freq;
		long recent;
		public Cache (int key, int freq){
			this.key = key;
			this.freq = freq;
			this.recent = System.nanoTime();
		}

		public boolean equals(Object o){
			return o instanceof Cache && key == ((Cache) o).key;
		} 

		public int hashcode(){return key;}

		public int compareTo(Cache c) {
			return freq == c.freq ? (int)(recent - c.recent) : freq - c.freq;
		}
	}

	int capacity;
	Map<Integer, Integer> value, frequency;
	PriorityQueue<Cache> queue;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        value = new HashMap<>();
        frequency = new HashMap<>();
        queue = new PriorityQueue<>();
    }
    
    public int get(int key) {
        if(!value.containsKey(key)) return -1;
        updateFreq(key);
        return value.get(key);
    }
    
    public void put(int key, int val) {
        if(capacity <= 0) return;
        if(value.containsKey(key)){
        	updateFreq(key);
        	value.put(key, val);
        	return;
        }

        if(value.size() == capacity){
        	Cache old = queue.poll();
        	value.remove(old.key);
        	frequency.remove(old.key);
        }

        value.put(key, val);
        frequency.put(key, 1);
        queue.add(new Cache(key, 1));
    }

    // 更新频率
    private void updateFreq(int key){
    	if(!frequency.containsKey(key)) return;
		int f = frequency.get(key) + 1;
		frequency.put(key, f);
		Cache cache = new Cache(key, f);
    	queue.remove(cache);
    	queue.add(new Cache(key, f));
    }
}
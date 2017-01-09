// 简单粗暴， 用一个双向链表， 头是新元素，尾部是旧元素， 如果用过就提出来摆head， 删掉就删掉
// 所有信息存放在Cache类里面，也可以叫Node啦。。。
public class LRUCache{
	public class Cache{
		int key, value;
		Cache prev, next;
		Cache(int key, int value){
			this.key = key;
			this.value = value;
		}
	}

	int capacity, count;
	Map<Integer, Cache> map;
	Cache head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Cache(0,-1);
        tail = new Cache(0, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Cache old = map.get(key);
    	remove(old);
    	add(old);
        return old.value;
    }
    
    public void set(int key, int value) {
    	if(capacity <= 0) return;
        if(map.containsKey(key)) {
        	Cache old = map.get(key);
        	old.value = value;
        	remove(old);
        	add(old);
        	return;
        }

        if(count >= capacity){
        	Cache remove = tail.prev;
            remove(remove);
        	map.remove(remove.key);
        }

        Cache add = new Cache(key, value);
        map.put(key, add);
        add(add);
        count++;
    }

    // add node to head
    private void add(Cache node){
        Cache after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }
    
    // remove node from list
    private void remove(Cache node){
        Cache before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }
}
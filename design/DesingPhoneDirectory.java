/*
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.

设计电话簿， 要求有:
    get     ->  提供一个可用的号码
    check   ->  查看是否某个号码可用
    release ->  回收／删除一个号码
*/
public class DesingPhoneDirectory {

	// keep track of available numbers, if require every time given must be smallest number available, then use a PriorityQueue (min-heap)instead.
	private final LinkedList<Integer> availableNums;
	// hash-map alike to quickly check if a given number is used
	private final boolean[] isUsed;
	// max count of numbers
	private final int maxNumbers;
	
	/* 
	 * Initialize your data structure here
	 *
	 * @param maxNumbers - The maximum numbers that can be stored in the phone directory. 
	 */
	public PhoneDirectory(int maxNumbers) {
		availableNums = new LinkedList<>();
		this.maxNumbers = maxNumbers;
		isUsed = new boolean[maxNumbers];
		
		for(int num = 0; num < maxNumbers; num++) availableNums.offer(num);
	}
	
	/* 
	 * Provide a number which is not assigned to anyone.

	 * @return - Return an available number. Return -1 if none is available. 
	 */
	public int get() {
		if(availableNums.isEmpty()) return -1;
		int num = availableNums.poll();
		isUsed[num] = true;
		return num;	
	}
	
	/* 
	 * Check if a number is available or not. 
	 */
	public boolean check(int number) {
		if(number < 0 || number >= maxNumbers) return false;
		return !isUsed[number];
	}
	
	/* 
	 * Recycle or release a number. 
	 */
	public void release(int number) {
		if(number < 0 || number >= maxNumbers || !isUsed[number]) return;
		
		isUsed[number] = false;
		availableNums.addFirst(number);
	}
}
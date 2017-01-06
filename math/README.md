[Reservoir Sampling: Linked List Random Node](https://leetcode.com/problems/linked-list-random-node/)
Problem: 
```
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
```
解答: 
若已知链表长度len，那么直接随机一下0~len-1，然后遍历到那个结点。
如果不知道长度呢？
我们实时的计算当前遍历了多少个元素cnt，然后以 1/cnt 的概率选择 当前的元素，直到遍历完链表。
这样遍历一遍即可。

验证：
一共n个数，选择第i个数的概率是：
从第1到第i-1，可以选中也可以不选中，当第i次选中的是i的时候，前面的返回值都会被覆盖， 所以：P' = 1;
第i次选中i的概率是1／i，然后从第i + 1开始，所有数字都不被选中：
```
p" = i / i + 1  *  i + 1 / i + 2  * .... * n - 1 / n

P = P‘ * (1 * / i) * P“ = 1 / i * i / i+1 * i+1/i+2 * .... * n-1/n = 1 / n
```
所以这样遍历一次就可以保证选取所有都是公平的。
Code: 
```
public class Solution {
    private ListNode head;
    private Random random;
    
    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int ans = 0;
		ListNode p = head;
        // 当小于n的时候不断试验random值
		for (int cnt = 1; p != null; cnt++, p = p.next) {
            if (random.nextInt(cnt) == 0) {
                ans = p.val;
            }
        }
		return ans;
    }
}
```

## Reservoir Sampling的详细解释：
```
PROBLEM:

Choose k entries from n numbers. Make sure each number is selected with the probability of k/n
BASIC IDEA:

Choose 1, 2, 3, ..., k first and put them into the reservoir.
For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
Repeat until k+i reaches n
PROOF:

For k+i, the probability that it is selected and will replace a number in the reservoir is k/(k+i)
For a number in the reservoir before (let's say X), the probability that it keeps staying in the reservoir is
P(X was in the reservoir last time) × P(X is not replaced by k+i)
= P(X was in the reservoir last time) × (1 - P(k+i is selected and replaces X))
= k/(k+i-1) × （1 - k/(k+i) × 1/k）
= k/(k+i)
When k+i reaches n, the probability of each number staying in the reservoir is k/n
EXAMPLE

Choose 3 numbers from [111, 222, 333, 444]. Make sure each number is selected with a probability of 3/4
First, choose [111, 222, 333] as the initial reservior
Then choose 444 with a probability of 3/4
For 111, it stays with a probability of
P(444 is not selected) + P(444 is selected but it replaces 222 or 333)
= 1/4 + 3/4*2/3
= 3/4
The same case with 222 and 333
Now all the numbers have the probability of 3/4 to be picked
THIS PROBLEM <LINKED LIST RANDOM NODE>

This problem is the sp case where k=1
```

[335. Self Crossing](https://leetcode.com/problems/self-crossing/): array x[] is distance when go North, West, South, East, iteratively. eg, [1,2,1,1] means go N = 1, then W = 2, then S = 1, last E = 1. can more than 4 values then means keep going. Provided such array, check if any path is crossing with any others.
```
Example 1:
Given x = 
[2, 1, 1, 2]
,
┌───┐
│   │
└───┼──>
    │

Return true (self crossing)
Example 2:
Given x = 
[1, 2, 3, 4]
,
┌──────┐
│      │
│
│
└────────────>

Return false (not self crossing)
Example 3:
Given x = 
[1, 1, 1, 1]
,
┌───┐
│   │
└───┼>

Return true (self crossing)
```
Solution:
```
It has two general solution: 
first is to check what condition it does not cross. then if satisfy, it means the it can not cross.
1. it spin in, eg, [9,9,8,8,7,7,6,6,5,5,4,4,3,3,2,2,1]
2. it spin out, eg, [1,2,3,4,5,6,7,8,9,10,....]
3. it first spin out, and then it spin in. [1,2,3,4,5,6,7,16,7,6,5,4,3,2,1]

Second is to define the condition when it crosses: 
Categorize the self-crossing scenarios, there are 3 of them: 
1. Fourth line crosses first line and works for fifth line crosses second line and so on...
2. Fifth line meets first line and works for the lines after
3. Sixth line crosses first line and works for the lines after
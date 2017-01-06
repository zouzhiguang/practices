# solution to [General Problem](https://github.com/zouzhile/interview)

1) write a random function randN based on randM: 

rand5: (int) 5* Math.random() + 1); 用一个randM() 生成一个randN():
```
if (M=N) return randM();
if(M > N) res = M; while(res > N) res = randM(); return res;
if(M < N) int res = Integer.MAX_VALUE, threshold = (M * M / N) * N; while(res > threshold) res = M * (randM() - 1) + randM(); return res % N + 1;
```
2) email regex: 
```
^[a-zA-Z0-9+._-]+@[a-zA-Z0-9+._-]+\.[a-zA-Z0-9+._-]+$
```
3) implement union find: 

4) convert Excel column count (A,B,C...Z,AA,AB,...) to Integer (1,2,3...26,27,28...):
```
int res = 0, base = 1;
for(int i = c.length() - 1; i >= 0; i--, base *= 26){
	res += (c.charAt(i) - 'A' + 1) * base;
}
```

6-N) What method would you use to look up a word in a dictionary?
Trie solution? -> extra spaces, look-up: O(word),
HashMap -> hash collide? O(1)
HashTable -> same

7-N) Find or determine non existence of a number in a sorted list of N numbers where the numbers range over M, M << N and N large enough to span multiple disks. Algorithm to beat O(log n), bonus points for constant time algorithm.
```
1. compare head and tail of each disk, locate which disk the number is on
2. use binary search to locate the number on single disk. space: O(1)

Another approach: 
As M << N, we can use binary search to fill the cache to enumerate M, when check, it's constant time. space: O(M), warm up: O(MlogN)
1. for each disk, use binary search to detect all numbers then to fill in cache.
2. check cache if the number exists.

for first one, need to rotate disk every time and every lookup is O(logN). it's better to use when look up time is rarely small but need to quickly find data in a big bunch of data.
the second one, though a little bit slower when warm up, but give constant time to checking. it's better to use when check a lot of times for different values. 
```

8-N) Given a file of 4 billion 32-bit integers, how to find one that appears at least twice?

use one bit to represent one integer. So a byte is 8 bit can represent 8 number occurrence. 
```
int radix = 8;
byte[] bitfield = new byte[0xffffffff/radix];
void F() throws FileNotFoundException{
    Scanner in = new Scanner(new FileReader("a.txt"));
    while(in.hasNextInt()){
        int n = in.nextInt();
        bitfield[n/radix] |= (1 << (n%radix));
    }

    for(int i = 0; i< bitfield.lenght; i++){
        for(int j =0; j<radix; j++){
            if( (bitfield[i] & (1<<j)) == 0) System.out.print(i*radix+j);
        }
    }
}
```
this requires 2^32 bit which is 8G bit which is 1G bytes memory. If provided only 10 MB memory: (10MB = 10 * 2^23 bit ~ 2 ^ 26)
use short[1<<20], 
scan from 0x000 ~ 0x111

9） Given a set of coin denominators, find the minimum number of coins to give a certain amount of change. Same Problem with C12_2： 
```
DP solution: 
dp[n] 表示要凑面值为n的需要最少硬币数目, dp[0] = 0;
for(int i = 1; i <= n; i++){
	dp[i] = Integer.MAX_VALUE;
	for(int j = 0; j < values.length && i >= values[j]; j++){
		dp[i] = Math.min(dp[i], dp[i - values[j]] + 1);
	}
}

return dp[n];
```
10) Given a set of ranges, find the two ranges with the greatest overlap.
```
1. sort by starting point, descending with end point
2. for each starting point, just compare first one of other array and second with same start point.
eg: [1,3], [2,5], [1,4], [3,6], [7,8]: after sort: [1, 4], [1, 3], [2, 5], [3, 6], [7, 8]
compute max one by one with cutting branch.
```



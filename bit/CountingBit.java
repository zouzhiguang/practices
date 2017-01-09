// 数从 0 ~ n 的所有数字组成的数组中所有数字的1的个数
// dp + bit: 1101 就等于f[0110] +　最后一位是否为１？１:０，　而０１１０　＜　１１０１，　所以在此之前已经计算过了。
public class CountingBit {
	public int[] countBits(int num) {
	    int[] f = new int[num + 1];
	    for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
	    return f;
	}
}
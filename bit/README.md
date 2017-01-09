# bit manipulations:

```
Set union : A | B
Set intersection : A & B
Set subtraction : A & ~B
Set negation : ALL_BITS ^ A

Set bit : A |= 1 << bit
Clear bit : A &= ~(1 << bit)
Test bit : (A & 1 << bit) != 0

```

Tricks: 
```
A & -A: extract lowest bit with '1' to 0: 
    i & (~i + 1) is a trick to extract the lowest set bit of i.
eg: 
for(int i=n;i > 0; i-=i &(-i)){
	System.out.println(i);	
}  

if n = 10 -> 1010:
output is: 
10 (1010, i & -i = 2, 10-2 = 8, so i' = 8)
8 (1000, 8 & -8 = 8, 8 - 8 = 0, so i'' = 0, break)

```
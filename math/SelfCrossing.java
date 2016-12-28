public class SelfCrossing {

	// For this question, to keep the line not crossed, it can be in following conditions:
	// Keep spiraling outside.
	// Keep spiraling inside.
	// Not crossing during transition from outside spiral into inside spiral.
    public boolean isSelfCrossingDefineWhenIts_Not_Crossing(int[] x) {
        if(x == null || x.length <= 3) return false;
        return !isSpinIn(x, 0, x.length - 1) && !isSpinOut(x, 0, x.length - 1);
    }
    // spin-in, if cross, return false.
    private boolean isSpinIn(int[] x, int from, int end) {
        for(int i = from, j = from + 2; j <= end; i++, j++)
            if(i != from && x[i] <= x[j]) 
                return false;
        return true;
    }
    // spin-out / spin-out to spin in.
    private boolean isSpinOut(int[] x, int from, int end) {
        for(int i = from, j = from + 2; j < end; i++, j++)
            if(x[i] >= x[j]) 
                // not crossing when spin-out change to spin-in and the violated part is a valid spin-in
                return (i - 2 >= 0 && x[j] < (x[i] - x[i-2]) || i - 1 >= 0 && x[j+1] < (x[i + 1] - x[i-1])) && isSpinIn(x, i, end);
        return true;
    }



    // Categorize the self-crossing scenarios, there are 3 of them: 
	// 1. Fourth line crosses first line and works for fifth line crosses second line and so on...
	// 2. Fifth line meets first line and works for the lines after
	// 3. Sixth line crosses first line and works for the lines after
	public boolean isSelfCrossingDefineWhenItsCrossing(int[] x) {
        int l = x.length;
        if(l <= 3) return false;
        
        for(int i = 3; i < l; i++){
            if(x[i] >= x[i-2] && x[i-1] <= x[i-3]) return true;  //Fourth line crosses first line and onward
            if(i >=4)
            {
                if(x[i-1] == x[i-3] && x[i] + x[i-4] >= x[i-2]) return true; // Fifth line meets first line and onward
            }
            if(i >=5)
            {
                if(x[i-2] - x[i-4] >= 0 && x[i] >= x[i-2] - x[i-4] && x[i-1] >= x[i-3] - x[i-5] && x[i-1] <= x[i-3]) return true;  // Sixth line crosses first line and onward
            }
        }
        return false;
    }
}
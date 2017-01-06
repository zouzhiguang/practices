
// one thing to note: for java, it's following IEEE Standard for Floating-Point Arithmetic (IEEE 754),
// which it implements 0.0 and -0.0 when it should be used. 
// so when the Double (0.0) and Double(-0.0) is different key when use double for the count map key.
// easy way to solve this is to add 0.0 to the slope.
public class MaxPointsOnALine {
    public int maxPoints(Point[] points) {
		if(points == null || points.length <= 0) return 0;
		int max = 1;
		for(int i = 0; i < points.length - 1; i++){
			int samePoints = 1, yCount = 0, localMax = 0;
			Map<Double, Integer> map = new HashMap<>();
			for(int j = i + 1; j < points.length; j++){
				if(points[i].x == points[j].x && points[i].y == points[j].y) samePoints++;
				else if(points[i].x == points[j].x) yCount++;
				else {
					// add 0.0 to make -0.0 become 0.0, or else it's different key.
					double slope = (points[i].y - points[j].y) / (double) (points[i].x -  points[j].x) + 0.0;
					map.put(slope, map.containsKey(slope) ? map.get(slope) + 1 : 1);
				}
			}
			for(int count : map.values()){
				localMax = Math.max(localMax, count);		
			}

			localMax = Math.max(localMax, yCount) + samePoints;
			max = Math.max(localMax, max);
		}

		return max;
    }
}

public static class Point{
	private int x;
	private int y;
	public Point(){}
	public point(int x, int y){
		this.x = x;
		this.y = y;
	}
}
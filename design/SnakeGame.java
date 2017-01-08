public class SnakeGame{
	public static class Position {
		int x, y;
		Position(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Position p){
			return p != null && x == p.x && y == p.y;
		}
	}
	// length of snake, game boarder for rows and cols.
	int len, rows, cols;

	int[][] food;

	LinkedList<Position> snake;

    /** Initialize your data structure here.
    *  @param width - screen width
    *  @param height - screen height 
    *  @param food - A list of food positions
    *  E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. 
    */
    public SnakeGame(int width, int height, int[][] food) {
        this.rows = height;
        this.cols = width;
        this.food = food;
   
        snake = new LinkedList<>();
        snake.add(new Position(0,0));
    }

     /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
    	//if(len>=food.length) return len;
    	
    	//current head
        Position cur = new Position(snake.get(0).x,snake.get(0).y);
        Position food = new Position(food[len][0],food[len][1]);
        
        switch(direction){
	        case "U": 
	            cur.x--;  break;
	        case "L": 
	            cur.y--; break;
	        case "R": 
	            cur.y++;   break;
	        case "D": 
	            cur.x++;   break;
        }
        
        // cross border or hit self
        if(cur.x < 0 || cur.x >= rows || cur.y < 0 || cur.y >= cols || snake.contains(cur)) return -1;

        snake.addFirst(cur);   

        // if head hit food, then change food to snake head, len++
        // or else, head hit empty, tail need to reduce by one.
        if(len < food.length && cur.equals(food)) len++;
        else while(snake.size()>len+1) snake.removeLast();

        return len;
    }

}
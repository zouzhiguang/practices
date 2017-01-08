// 设计井字棋， track行，列和对角线的个数。 玩家1是1， 玩家2是-1.
// 胜利的条件是最先有任何一行，一列或对角线等于n
// 注意接口的设计
public class TicTacToeGame {
	// 行和列的array，因为行和列可以有多个可能
	private int[] rows;
	private int[] cols;
	// 对角线和反对角线中棋子的个数
	private int diagonal;
	private int backDiagonal;

	/** Initialize your data structure here. */
	public TicTacToe(int n) {
	    rows = new int[n];
	    cols = new int[n];
	}

	/** Player {player} makes a move at ({row}, {col}).
	    @param row The row of the board.
	    @param col The column of the board.
	    @param player The player, can be either 1 or -1.
	    @return The current winning condition, can be either:
	            0: No one wins.
	            1: Player 1 wins.
	            -1: Player 2 wins. */
	public int move(int row, int col, int player) {
	    int toAdd = player == 1 ? 1 : -1;
	    
	    rows[row] += toAdd;
	    cols[col] += toAdd;

	    if (row == col) diagonal += toAdd;
	    if (col == (cols.length - row - 1)) backDiagonal += toAdd;
	       
	    int size = rows.length;
	    if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size  || Math.abs(backDiagonal) == size)
	    {
	        return player;
	    }
	    
	    return 0;
	}
}
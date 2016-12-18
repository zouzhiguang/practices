public class NQueens {
	public void solveNQueen(char[][] board){
		if(board == null || board.length <= 0 || board[0] <= 0 || board.length != board[0].length) return;

		solve(board, 0);
	}

	private boolean solve(char[][] board, int offset){
		if(offset >= board.length) return true;

		for(int i = 0; i < board.length; i++){
			board[offset][i] = 'Q';
			if(isValid(board) && solve(board, offset + 1)) return true;
			board[offset][i] = '.';
		}

		return false;
	}

	private boolean isValid(char[][] board, int row, int col){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == 'Q' && (row == i || col == j || row + j == col + j || row + col == i + j)){
					return false;
				}
			}
		}

		return true;
	}
}
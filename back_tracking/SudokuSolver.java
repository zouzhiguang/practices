public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length <= 0 || board[0] <= 0) return;
        solve(board)
    }

    public boolean solve(char[][] board){
    	for(int i = 0; i < 9; i++){
        	for(int j = 0; j < 9; j++){
        		if(board[i][j] != '.'){
                    continue;
                }
                for(int k = 1; k <= 9; k++){
                    board[i][j] = (char) (k + '0');
                    if (isValid(board, i, j) && solve(board)){
                        return true;
                    }
                    board[i][j] = '.';
                }
                return false;
        	}
        }

        return false;
    }


      public boolean isValid(char[][] board, int a, int b){
        boolean[] contained = new boolean[9];
        for(int j=0;j<9;j++){
        	if(board[a][j] == '.') continue; 
            if(contained[board[a][j] - '0']) return false;
           	contained[board[a][j] - '0'] = true;
        }

        contained = new boolean[9];
        for(int j=0;j<9;j++){
            if(board[j][b] == '.') continue; 
            if(contained[board[j][b] - '0']) return false;
           	contained[board[j][b] - '0'] = true;
        }

        contained = new boolean[9];
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++){
                int x = a / 3 * 3 + m, y = b / 3 * 3 + n;
                if(board[x][y] == '.') continue; 
            	if(contained[board[x][y] - '0']) return false;
           		contained[board[x][y] - '0'] = true;
            }
        }

        return true;
    }
} 
}
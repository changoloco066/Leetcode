package Hard.SudokuSolver;

public class sudokuSolver {
    
    // Main solution
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(board[row][col] == '.'){
                    for(char num = '1'; num <= '9'; num++){
                        if(isValid(board, row, col, num)){
                            board[row][col] = num;
                            
                            if(solve(board)){
                                return true;
                            }
                            
                            board[row][col] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char num) {
        // Check row
        for(int j = 0; j < 9; j++){
            if(board[row][j] == num){
                return false;
            }
        }
        
        // Check column
        for(int i = 0; i < 9; i++){
            if(board[i][col] == num){
                return false;
            }
        }
        
        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for(int i = boxRow; i < boxRow + 3; i++){
            for(int j = boxCol; j < boxCol + 3; j++){
                if(board[i][j] == num){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        sudokuSolver solver = new sudokuSolver();
        
        // Test case 1
        char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              SUDOKU SOLVER - BACKTRACKING                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Test Case 1:");
        System.out.println("\nBefore solving:");
        printBoard(board1);
        
        solver.solveSudoku(board1);
        
        System.out.println("\nAfter solving:");
        printBoard(board1);
        
        System.out.println("\n✅ Sudoku solved successfully!");
    }
    
    private static void printBoard(char[][] board) {
        for(int i = 0; i < 9; i++){
            if(i % 3 == 0 && i != 0) System.out.println("------+-------+------");
            
            for(int j = 0; j < 9; j++){
                if(j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
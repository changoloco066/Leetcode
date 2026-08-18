# 37. Sudoku Solver

##  Problem Statement

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy **all of the following rules:**

1. Each row must contain the digits `1-9` without repetition
2. Each column must contain the digits `1-9` without repetition
3. Each of the nine `3×3` sub-boxes of the grid must contain the digits `1-9` without repetition

The input board contains only digits `1-9` and the character `'.'` representing empty cells.

---

##  Solution: Backtracking Algorithm

### Algorithm Overview

**Backtracking** is a technique where you:
1. Try a solution
2. If it doesn't work, undo it and try another
3. Repeat until you find the solution

### Strategy

1. Find an empty cell('.')
2. Try numbers 1-9
3. For each number:

        a. Check if it's valid (no conflicts)
        b. Place the number
        c. Recursively try to solve the rest
        d. If it works → return true (solution found)
        e. If it doesn't work → remove number and try next

4. If no number works → return false (backtrack)
5. If no empty cells → return true (solved)



### Code (For LeetCode)

```java
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        // Look for an empty cell
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(board[row][col] == '.'){
                    // Try numbers 1-9
                    for(char num = '1'; num <= '9'; num++){
                        if(isValid(board, row, col, num)){
                            // Place the number
                            board[row][col] = num;
                            
                            // Recursively solve
                            if(solve(board)){
                                return true;
                            }
                            
                            // Backtrack: remove the number
                            board[row][col] = '.';
                        }
                    }
                    // If no number worked, this path is wrong
                    return false;
                }
            }
        }
        // No empty cells found → puzzle solved
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
        
        // Check 3×3 box
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
}
```

---


## Complexity Analysis

**Time Complexity:** O(9^(n×m))
- Worst case: explore all possibilities
- `n×m` = number of empty cells (up to 81 for a blank board)
- For each cell, try up to 9 numbers
- In practice, much faster due to constraints

**Space Complexity:** O(1) if not counting recursion stack
- Recursion depth: O(n×m) in worst case
- No extra data structures

---

##  Key Points

1. **Backtracking** is perfect for constraint satisfaction problems
2. **isValid()** checks all three Sudoku rules
3. **Recursion** naturally explores the search space
4. **Early termination** when a dead-end is found
5. **In-place modification** of the board (no need to return)

---

##  Why This Works

The algorithm works because:
- It tries all valid placements
- It avoids invalid paths early
- It explores only feasible solutions
- Sudoku has exactly one solution (guaranteed)

---

## Summary

This is a **Hard** problem that teaches:
- Backtracking algorithm
- Constraint satisfaction
- Recursive problem solving
- Efficient pruning of search space


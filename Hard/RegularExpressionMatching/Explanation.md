# 10. Regular Expression Matching

## Problem Statement

Given an input string `s` and a pattern `p`, implement regular expression matching with support for `'.'` and `'*'` where:

- **`'.'`** Matches any single character
- **`'*'`** Matches zero or more of the preceding element

Return a boolean indicating whether the entire input string matches the pattern (not partial).

### Constraints:
- `1 <= s.length <= 20`
- `1 <= p.length <= 20`
- `s` contains only lowercase English letters
- `p` contains only lowercase English letters, `'.'`, and `'*'`
- For each `'*'`, there is a previous valid character to match

### Examples:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa"

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'

Input: s = "ab", p = "."
Output: true
Explanation: "." means zero or more of any character

Input: s = "aab", p = "cab"
Output: true
Explanation: "c*" matches "", "a*" matches "aa", "b" matches "b"

## 🎯 Core Concept: Understanding the Pattern

Before diving into solutions, let's understand what patterns mean:

- "a*" can match:     "", "a", "aa", "aaa", ...    (zero or more 'a's) 
- "ab*" can match:    "a", "ab", "abb", "abbb", ... 
- "." can match:     "" "a", "ab", "abc", ...    (any sequence) 
- "ca*b" can match:  "b", "ab", "aab", "cab", "aabb", ...

The key insight: **When you see `*`, you have TWO choices:**
1. **Ignore** the `c*` (match zero 'c's)
2. **Use** the `c*` (match one or more 'c's)

---

## 💡 Solution 1: Dynamic Programming (DP) - RECOMMENDED

### Algorithm

DP is a **bottom-up approach** where you build a table to store results of subproblems.

**Key Idea:** `dp[i][j]` = Does `s[0...i-1]` match `p[0...j-1]`?

### Code (For LeetCode)

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        dp[0][0] = true;
        
        for(int j = 2; j <= n; j++){
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);
                
                if(pChar == '*'){
                    char prevChar = p.charAt(j - 2);
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && (sChar == prevChar || prevChar == '.'));
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && 
                              (sChar == pChar || pChar == '.');
                }
            }
        }
        
        return dp[m][n];
    }
}
```

### Step-by-Step Explanation

#### Step 1: Create the Table

```java
boolean[][] dp = new boolean[m + 1][n + 1];
```

Why `+ 1`? To include the empty string case (index 0).
Dimensions: (s.length() + 1) × (p.length() + 1)  


Example: s = "aa", p = "a*"
(3 rows) × (3 columns)

#### Step 2: Base Cases

**Case A: Empty string with empty pattern**
```java
dp[0][0] = true;
```
An empty string matches an empty pattern.

**Case B: Empty string with non-empty pattern**
```java
for(int j = 2; j <= n; j++){
    if(p.charAt(j - 1) == '*'){
        dp[0][j] = dp[0][j - 2];
    }
}
```

Why `j = 2`? Because patterns like `"a*"`, `"b*"` can match an empty string (by using 0 of that character).

The pattern must be like `"c*d*e*..."` to match an empty string.

### Example: "" with "ab"

- "a*" can be " " (zero 'a's)  
- "b*" can be " " (zero 'b's)  
- So " " matches "ab" ✓

```
dp[0][2] = dp[0][0] = true  (after "a*")  
dp[0][4] = dp[0][2] = true  (after "ab")
```

#### Step 3: Fill the Table

For each character in the string and pattern:

```java
for(int i = 1; i <= m; i++){
    for(int j = 1; j <= n; j++){
        char sChar = s.charAt(i - 1);
        char pChar = p.charAt(j - 1);
```

**When `pChar == '*'`:**

```java
if(pChar == '*'){
    char prevChar = p.charAt(j - 2);  // Character BEFORE the '*'
    
    // Option A: Ignore "c*" (use 0 times)
    dp[i][j] = dp[i][j - 2] || 
    
    // Option B: Use "c*" (use 1+ times)
    // BUT ONLY if the character matches
              (dp[i - 1][j] && (sChar == prevChar || prevChar == '.'));
}
```

**Why this works:**
- `dp[i][j - 2]`: Skip the `*` pattern completely
- `dp[i - 1][j]`: Use the `*` again, but only if character matches
- If **either** option is true, the entire pattern matches

**When NOT `*`:**
```java
dp[i][j] = dp[i - 1][j - 1] && 
          (sChar == pChar || pChar == '.');
```

Simple diagonal match: both characters must match (or `pChar` is `.`).

### Visual Example
```
**Input:** `s = "aab"`, `p = "c*a*b"`

""  "c"  "c*"  "a"  "a*"  "b"
""  T   F    T     F    T     F
"a" F   F    T     F    T     F
"aa"F   F    T     F    T     F
"aab"F   F    T     F    T     T  ← Answer!

```

### How it fills:  
dp[0][2] = dp[0][0] = T    ("c*" can be empty)   
dp[0][4] = dp[0][2] = T    ("ca" can be empty)  

dp[1][2] = dp[1][0] || (dp[0][2] && 'a'=='c')
= F || (T && F) = F  
dp[1][4] = dp[1][2] || (dp[0][4] && 'a'=='a')
= F || (T && T) = T  
dp[2][4] = dp[2][2] || (dp[1][4] && 'a'=='a')
= F || (T && T) = T  
dp[3][5] = dp[3][4] && ('b'=='b')
= T && T = T  ✓  

### Complexity

- **Time:** O(m × n) - Two nested loops
- **Space:** O(m × n) - DP table

---

## Alternative Solutions

### 2️⃣ Memoization (Recursion + HashMap)

**Approach:** Recursively try both options for `*`, cache results to avoid recalculation.

**LeetCode Code:**
```java
class Solution {
    private Map<String, Boolean> memo;
    
    public boolean isMatch(String s, String p) {
        memo = new HashMap<>();
        return match(s, p, 0, 0);
    }
    
    private boolean match(String s, String p, int i, int j) {
        String key = i + "," + j;
        if(memo.containsKey(key)) return memo.get(key);
        
        if(j == p.length()) return i == s.length();
        
        boolean hasNext = (j + 1 < p.length() && p.charAt(j + 1) == '*');
        boolean result = false;
        
        if(hasNext) {
            result = match(s, p, i, j + 2);
            if(!result && i < s.length() && 
               (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
                result = match(s, p, i + 1, j);
            }
        } else {
            if(i < s.length() && 
               (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
                result = match(s, p, i + 1, j + 1);
            }
        }
        
        memo.put(key, result);
        return result;
    }
}
```

**Complexity:**
- Time: O(m × n)
- Space: O(m × n) - HashMap + recursion stack

**When to use:** When you want to understand recursion better, or prefer top-down approach.

---

### 3️⃣ Brute Force (Recursion Pura)

**Approach:** Try both options for `*` without any caching. Very slow.

**LeetCode Code:**
```java
class Solution {
    public boolean isMatch(String s, String p) {
        return match(s, p, 0, 0);
    }
    
    private boolean match(String s, String p, int i, int j) {
        if(j == p.length()) return i == s.length();
        
        boolean hasNext = (j + 1 < p.length() && p.charAt(j + 1) == '*');
        
        if(hasNext) {
            if(match(s, p, i, j + 2)) return true;
            if(i < s.length() && 
               (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
                return match(s, p, i + 1, j);
            }
        } else {
            if(i < s.length() && 
               (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
                return match(s, p, i + 1, j + 1);
            }
        }
        
        return false;
    }
}
```

**Complexity:**
- Time: O(2^(m+n)) - Exponential, very slow
- Space: O(m+n) - Recursion stack

**When to use:** Only for understanding the logic, NOT practical.

---

##  Comparison

| Approach | Time | Space | Runtime | Use Case |
|----------|------|-------|---------|----------|
| **DP** | O(m×n) | O(m×n) | 1-3ms |  Production, Interview |
| **Memoization** | O(m×n) | O(m×n) | 2-5ms |  Learning recursion |
| **Brute Force** | O(2^(m+n)) | O(m+n) |  TLE |  Understanding only |

---

##  Key Learnings

1. **DP Table Dimensions:** `(m+1) × (n+1)` to include empty string
2. **Base Cases Matter:** Correctly handle empty string patterns
3. **The `*` Logic:** Always has TWO options - ignore or use
4. **Verification:** `||` for `*` (either option works), `&&` for normal chars
5. **Efficiency:** Always prefer DP over brute force for this problem

---

## Summary

This is a **Hard** problem that combines:
- String matching
- Dynamic Programming
- Logical reasoning about patterns

The key is understanding that `*` creates ambiguity - you must explore both possibilities and use DP/memoization to avoid redundant calculations.
# 5. Longest Palindromic Substring

## Problem
Given a string `s`, return the longest palindromic substring in `s`.

**Examples:**
- Input: `"babad"` → Output: `"bab"` (or `"aba"`)
- Input: `"cbbd"` → Output: `"bb"`
- Input: `"racecar"` → Output: `"racecar"`

**Note:** A palindrome is a string that reads the same backward as forward (e.g., "aba", "noon").

---

## Solution: Expand Around Center

### Algorithm

This solution uses the **expand around center** technique. The key insight is that every palindrome has a center, and we can expand outward from each possible center to find palindromes.

For each character in the string, we treat it as a potential center and expand in both directions while the characters match. We need to check two cases:
1. **Odd-length palindromes** (single character center): "aba", "racecar"
2. **Even-length palindromes** (two character center): "abba", "noon"

For each center, we expand outward and track the longest palindrome found. The `expand` helper function returns the length of the palindrome centered at the given position(s).

### Code
```java
class Solution {
    public String longestPalindrome(String s) {
        int maxLength = 0;
        int start = 0;

        for(int i = 0; i < s.length(); i++){
            int odd = expand(s, i, i);      // Odd-length palindromes
            int even = expand(s, i, i+1);   // Even-length palindromes

            if (odd > maxLength){
                maxLength = odd;
                start = i - (odd - 1) / 2;
            }

            if (even > maxLength){
                maxLength = even;
                start = i - (even - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLength);
    }

    private int expand(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--; 
            right++;
        }
        return right - left - 1;
    }
}
```

### Complexity
- **Time:** O(n²) - For each character (n), we potentially expand n times
- **Space:** O(1) - Only using a few variables

---

## Visual Example

**Input:** `s = "babad"`

```
Check center at index 0 ('b'):
Odd:  b → length 1
Even: ba → not palindrome, length 0
Check center at index 1 ('a'):
Odd:  bab → length 3 ✓
↓↑↓
bab (expands from 'a')
Even: ab → not palindrome, length 0
maxLength = 3, start = 0
Check center at index 2 ('b'):
Odd:  aba → length 3 (same as before)
↓↑↓
aba (expands from 'b')
Even: ba → not palindrome, length 0
maxLength = 3, start = 1
Check center at index 3 ('a'):
Odd:  a → length 1
Even: ad → not palindrome, length 0
Check center at index 4 ('d'):
Odd:  d → length 1
Even: (out of bounds)
Result: s.substring(1, 4) = "aba"

```

---

## How `expand` Works

The `expand` function takes a center (one or two characters) and expands outward:
```
Example: s = "racecar", center at index 3 ('e')
Step 1: left=3, right=3
r a c [e] c a r
↑
Match! Continue
Step 2: left=2, right=4
r a [c e c] a r
↑   ↑
Match! Continue
Step 3: left=1, right=5
r [a c e c a] r
↑       ↑
Match! Continue
Step 4: left=0, right=6
[r a c e c a r]
↑           ↑
Match! Continue
Step 5: left=-1, right=7
Out of bounds, stop!
Return: right - left - 1 = 7 - (-1) - 1 = 7
```
**Why `right - left - 1`?**
- When the loop exits, `left` and `right` are one position beyond the palindrome
- We need to subtract to get the actual length

---

## Key Insights

1. **Two cases matter:** Odd-length (single center) and even-length (double center)
2. **Expand greedily:** Keep expanding while characters match
3. **Track the best:** Update `start` and `maxLength` when we find a longer palindrome
4. **Calculate start position:** Use the formula `i - (length - 1) / 2` to find where the palindrome begins

---


## Complexity:
- **Time:** O( n² )
- **Space:** O( n² ) - uses 2D array

**Trade-off:** Expand Around Center uses O(1) space but same O(n²) time, making it more space-efficient.

---

## Summary

- **Approach:** Expand around each possible center
- **Key technique:** Check both odd and even length palindromes
- **Performance:** 13ms runtime, beats 95.99% on LeetCode
- **Space efficient:** O(1) extra space